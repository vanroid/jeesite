/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.vanroid.dachuang.common.csv.ImportCSV;
import com.vanroid.dachuang.modules.terminal.entity.TerTerminal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.vanroid.dachuang.modules.terminal.entity.TerBillMonth;
import com.vanroid.dachuang.modules.terminal.dao.TerBillMonthDao;
import org.springframework.web.multipart.MultipartFile;

/**
 * 月帐单Service
 *
 * @author CGZ
 * @version 2016-11-08
 */
@Service
@Transactional(readOnly = true)
public class TerBillMonthService extends CrudService<TerBillMonthDao, TerBillMonth> {

    public TerBillMonth get(String id) {
        return super.get(id);
    }

    public List<TerBillMonth> findList(TerBillMonth terBillMonth) {
        return super.findList(terBillMonth);
    }

    public Page<TerBillMonth> findPage(Page<TerBillMonth> page, TerBillMonth terBillMonth) {
        return super.findPage(page, terBillMonth);
    }

    @Transactional(readOnly = false)
    public void save(TerBillMonth terBillMonth) {
        super.save(terBillMonth);
    }

    @Transactional(readOnly = false)
    public void delete(TerBillMonth terBillMonth) {
        super.delete(terBillMonth);
    }

    @Transactional
    public Map<String, Object> importBillMonths(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        if (org.apache.commons.lang3.StringUtils.isBlank(fileName)) {
            throw new RuntimeException("导入文档为空!");
        } else if (fileName.toLowerCase().endsWith("csv")) {
        } else {
            throw new RuntimeException("文档格式不正确!");
        }
        InputStreamReader reader = new InputStreamReader(file.getInputStream(), Charset.forName("GBK"));

        List<TerBillMonth> datas = new ImportCSV(reader, 1).getDataList(TerBillMonth.class);

        String clrDate = DateUtils.formatDate(datas.get(0).getClearDate(), "yyyyMM");

        logger.debug("检测到{}月共{}条月帐单", clrDate, datas.size());
        // batch save
        dao.deleteByClearDate(datas.get(0).getClearDate());

        int rstCnt = batchSave(datas);
        Map resultMap = Maps.newHashMap();
        StringBuilder sb = new StringBuilder("成功导入");
        sb.append(rstCnt);
        sb.append("条记录");
        resultMap.put("message", sb.toString());
        return resultMap;
    }

    @Transactional(readOnly = false)
    public int batchSave(List<TerBillMonth> datas) {
        for (TerBillMonth terBillMonth : datas) {
            terBillMonth.preInsert();
        }
        return dao.batchSave(datas);
    }

    @Transactional
    public Page<TerBillMonth> findUnqualifiedPage(Page<TerBillMonth> page, TerBillMonth terBillMonth) {
        // 数据范围
        terBillMonth.getSqlMap().put("dsf", BaseService.dataScopeFilter(terBillMonth.getCurrentUser(), "o", ""));
        // 达标定义:月IC卡刷卡笔数(非接笔数)3笔或以上， ；<3
        // IC卡金额大于等于30; <30
        // 总刷卡金额大于等于200; <200
        // 总刷卡6笔或以上; <6
        String icTimes = DictUtils.getDictValue("ic_times", "ter_fail_def", "3");
        String icAmount = DictUtils.getDictValue("ic_amount", "ter_fail_def", "30");
        String totalAmount = DictUtils.getDictValue("total_amount", "ter_fail_def", "200");
        String totalTimes = DictUtils.getDictValue("total_times", "ter_fail_def", "6");

        StringBuilder failSql = new StringBuilder(" and a.ic_times < ");
        failSql.append(icTimes);
        failSql.append(" and a.ic_amount < ");
        failSql.append(icAmount);
        failSql.append(" and a.total_amount < ");
        failSql.append(totalAmount);
        failSql.append(" and a.total_times < ");
        failSql.append(totalTimes);
        //failSql.append(" and a.clear_date = '");
        //failSql.append(DateUtils.getFirstDayOnMonth());
        //failSql.append("'");

        terBillMonth.getSqlMap().put("fail", failSql.toString());

        return super.findPage(page, terBillMonth);
    }
}
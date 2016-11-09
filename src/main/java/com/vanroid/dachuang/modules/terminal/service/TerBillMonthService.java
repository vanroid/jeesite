/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.vanroid.dachuang.common.csv.ImportCSV;
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
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.vanroid.dachuang.common.ExcelUtils;
import com.vanroid.dachuang.common.StatusConstants;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.vanroid.dachuang.modules.terminal.entity.TerBillDay;
import com.vanroid.dachuang.modules.terminal.dao.TerBillDayDao;

/**
 * 消费日流水Service
 *
 * @author CGZ
 * @version 2016-10-30
 */
@Service
@Transactional(readOnly = true)
public class TerBillDayService extends CrudService<TerBillDayDao, TerBillDay> {


    public TerBillDay get(String id) {
        return super.get(id);
    }

    public List<TerBillDay> findList(TerBillDay terBillDay) {
        return super.findList(terBillDay);
    }

    public Page<TerBillDay> findPage(Page<TerBillDay> page, TerBillDay terBillDay) {
        return super.findPage(page, terBillDay);
    }

    @Transactional(readOnly = false)
    public void save(TerBillDay terBillDay) {
        super.save(terBillDay);
    }

    @Transactional(readOnly = false)
    public void delete(TerBillDay terBillDay) {
        super.delete(terBillDay);
    }

    /**
     * 通过excel导入帐单信息
     */
    @Transactional(readOnly = false)
    public int importBillDays(String fileName) {
        logger.debug("开始导入帐单");
        int billCnt = 0;

        try {
            // 第二个参数已无作用
            ImportExcel importExcel = new ImportExcel(fileName, 0);
            int rows = importExcel.getLastDataRowNum();

            List<TerBillDay> terBillDays = Lists.newArrayList();
            // 操作用户
            User curUser = UserUtils.getUser();

            logger.debug("正在操作操作的用户是:{}:{}", curUser.getId(), curUser.getName());
            for (int i = 1; i < rows; i++) {
                Row row = importExcel.getRow(i);

                // 没有[商户号][终端号]的视为无效记录,停止往下执行
                if (ExcelUtils.cellIsBank(row.getCell(8)) || ExcelUtils.cellIsBank(row.getCell(9))) {
                    logger.debug("导入信息时发现必需字段缺失，行数：{}", i);
                    break;
                }

                TerBillDay terBillDay = new TerBillDay();
                // 为字段赋值
                try {
                    excelRowToBillDay(terBillDay, row, curUser);
                } catch (Exception e) {
                    logger.error("字段中存在错误值，行数：{}", i);
                    throw new RuntimeException(e);
                }
                terBillDays.add(terBillDay);
            }

            logger.debug("检测到记录数量：{}", terBillDays.size());

            // todo batchinsert


            logger.debug("成功导入帐单记录数：" + billCnt);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            logger.error("导入帐单出错:{}", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("导入帐单出错:{}", e);
        }
        return billCnt;
    }

    private void excelRowToBillDay(TerBillDay terBillDay, Row row, User curUser) {
        // 清算日期0	交易代码1	 受理流水2	交易日期时间3 	卡号4 	交易金额5	 参考号6	 授权码7	终端号8	商户编号9	 商户名称10	商户借记手续费11	商户贷记手续费12
        // 清算日期0
        terBillDay.setClearDate(row.getCell(0).getDateCellValue());
        // 交易代码1
        terBillDay.setTranCode(ExcelUtils.getStringCellValue(row, 1));
        // 受理流水2
        terBillDay.setHandleCode(ExcelUtils.getStringCellValue(row, 2));
        // 交易日期时间3
        terBillDay.setTranDateTime(ExcelUtils.getStringCellValue(row, 3));
        // 卡号4
        terBillDay.setCardNo(ExcelUtils.getStringCellValue(row, 4));
        // 交易金额5
        terBillDay.setTranAmount(ExcelUtils.getDoubleCellValue(row, 5));
        // 参考号6
        terBillDay.setReferCode(ExcelUtils.getStringCellValue(row, 6));
        // 授权码7
        terBillDay.setGrantCode(ExcelUtils.getStringCellValue(row, 7));
        // 终端号8
        terBillDay.setTerminalNum(ExcelUtils.getStringCellValue(row, 8));
        // 商户编号9
        terBillDay.setMerchantNum(ExcelUtils.getStringCellValue(row, 9));
        // 商户名称10
        terBillDay.setMerchantName(ExcelUtils.getStringCellValue(row, 10));
        // 商户借记手续费11
        terBillDay.setDebitFee(ExcelUtils.getStringCellValue(row, 11));
        // 商户贷记手续费12
        terBillDay.setCreditFee(ExcelUtils.getStringCellValue(row, 12));

    }
}
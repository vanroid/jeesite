package com.thinkgem.jeesite.excel;

import com.google.common.io.Files;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.vanroid.dachuang.common.csv.ImportCSV;
import com.vanroid.dachuang.modules.terminal.entity.TerBillMonth;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by cgz on 16-10-25.
 */
public class ExcelTest {

    @Test
    public void testImport() {
        try {
            ImportExcel importExcel = new ImportExcel("/home/cgz/win7vm/大创电子---商户详情表.xlsx", 100);
            Cell c = importExcel.getRow(1).getCell(0);
            System.out.println(c.getDateCellValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testImportDayReport() {
        try {
            ImportExcel importExcel = new ImportExcel("/home/cgz/win7vm/LYGZ_20160613.xls", 100);
            Cell c = importExcel.getRow(1).getCell(0);

            System.out.println(c.getStringCellValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCsv() {
        try {
            Reader reader = Files.newReader(new File("/home/cgz/win7vm/终端交易统计_143_大创电子_201609.csv"), Charset.forName("GBK"));
            List<TerBillMonth> ls = new ImportCSV(reader, 1).getDataList(TerBillMonth.class);
            for (TerBillMonth bill : ls) {
                System.out.println(bill.getClearDate());
                System.out.println(bill.getTotalAmount());
                System.out.println(bill.getMaintenanceCompanyNick());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test(){
    }
}

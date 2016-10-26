package com.thinkgem.jeesite.excel;

import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by cgz on 16-10-25.
 */
public class ExcelTest {

    @Test
    public void testImport() {
        try {
            ImportExcel importExcel = new ImportExcel("/home/cgz/win7vm/大创电子---商户详情表.xlsx", 27);
            Date date = importExcel.getRow(1).getCell(0).getDateCellValue();
            System.out.print(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

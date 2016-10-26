package com.thinkgem.jeesite.excel;

import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import com.vanroid.dachuang.modules.terminal.service.PosTerminalService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by cgz on 16-10-25.
 */
public class ExcelTest {

    @Test
    public void testImport() {
        //PosTerminalService service = new PosTerminalService();
        //service.importTerminals("");

        try{
            ImportExcel importExcel = new ImportExcel("/home/cgz/win7vm/大创电子---商户详情表.xlsx", 100);
            Cell c = importExcel.getRow(1).getCell(0);
            System.out.println(c.getDateCellValue());
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}

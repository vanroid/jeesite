package com.thinkgem.jeesite.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.tools.corba.se.idl.constExpr.Terminal;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.terminal.entity.PosTerminal;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cgz on 16-10-25.
 */
public class ExcelTest {

    @Autowired
    SystemService systemService;

    @Autowired
    OfficeService officeService;

    @Test
    public void testImport() {
        List<User> users = Lists.newArrayList();

        Map<String, List<PosTerminal>> userMap = Maps.newHashMap();

        try {
            ImportExcel importExcel = new ImportExcel("/home/cgz/win7vm/大创电子---商户详情表.xlsx", 27);

            int rows = importExcel.getLastDataRowNum();
            // 遍历每一行,收集 以登录名为key,其他数据为value的map
            for (int i = 1; i < rows; i++) {
                Row row = importExcel.getRow(i);
                String loginName = row.getCell(24).getStringCellValue();
                // 获取此用户下的所有终端
                List<PosTerminal> userPosTerminals = userMap.get(loginName);
                if (userPosTerminals == null) {
                    userPosTerminals = Lists.newArrayList();
                }
                PosTerminal posTerminal = new PosTerminal();
                posTerminal.setTerminalId(row.getCell(7).getStringCellValue());
                // todo other info

                userPosTerminals.add(posTerminal);

                userMap.put(loginName, userPosTerminals);
            }

            // 插入机构\部门\用户
            Set<String> userLoginNames = userMap.keySet();
            // todo 获取根机构
            Office rootOffice = new Office();
            rootOffice.setId(Global.getRootOfficeId());

            for (String loginName : userLoginNames) {
                // 机构
                Office company = new Office();
                company.setName(loginName);
                officeService.save(company);
                // todo 所有上传的数据都是在[大创电子总公司之下]
                company.setParent(rootOffice);

                // 部门
                Office office = new Office();
                office.setName(loginName);
                office.setParent(company);
                officeService.save(office);

                // 用户
                User user = new User();
                user.setLoginName(loginName);
                user.setCompany(company);
                user.setOffice(office);
                systemService.saveUser(user);

                // 插入终端
                List<PosTerminal> terminals =  userMap.get(loginName);
                for(PosTerminal posTerminal : terminals){
                    posTerminal.setUser(user);
                }
                // 批量插入
                terminalService.save(terminals);
            }




        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

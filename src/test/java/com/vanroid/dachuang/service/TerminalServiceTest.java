package com.vanroid.dachuang.service;

import com.thinkgem.jeesite.BaseTest;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.vanroid.dachuang.modules.terminal.service.PosTerminalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cgz on 16-10-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-context.xml")
public class TerminalServiceTest {

    @Autowired
    private PosTerminalService terminalService;


    @Test
    public void testImport() {
        terminalService.importTerminals("/home/cgz/win7vm/大创电子---商户详情表.xlsx");
    }

}

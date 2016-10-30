package com.vanroid.dachuang.service;

import com.thinkgem.jeesite.BaseTest;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import com.vanroid.dachuang.modules.terminal.service.PosTerminalService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by cgz on 16-10-26.
 */
public class TerminalServiceTest extends BaseTest {

    @Autowired
    private PosTerminalService terminalService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private SystemService systemService;

    @Test
    public void testFindIdsByUser() {
        terminalService.findIdsByUser();
    }

    @Test
    public void testFindPageByUser() {
        Page<PosTerminal> page = new Page<PosTerminal>(1,30);
        PosTerminal posTerminal = new PosTerminal();
        page = terminalService.findPageByUser(page, posTerminal);
        System.out.print(page);
    }

    @Test
    public void testImport() {
        clearTest();
        terminalService.importTerminals("/home/cgz/win7vm/大创电子---商户详情表.xlsx");
    }

    // 清除上一次测试数据，使测试可以回归
    @Test
    public void clearTest() {
        SqlSessionFactory sqlSessionFactory = SpringContextHolder.getBean(SqlSessionFactory.class);
        String sql = "delete from sys_office where address = 'test'";
        try {
            Connection connection = sqlSessionFactory.openSession().getConnection();
            connection.createStatement().execute(sql);
            sql = "delete from sys_user_role where user_id in (select id from sys_user where remarks ='test')";
            connection.createStatement().execute(sql);
            sql = "delete from sys_user where remarks = 'test'";
            connection.createStatement().execute(sql);
            sql = "delete from ter_pos_terminal where remarks = 'test'";
            connection.createStatement().execute(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

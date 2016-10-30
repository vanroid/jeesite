package com.vanroid.dachuang.service;

import com.thinkgem.jeesite.BaseTest;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import com.vanroid.dachuang.modules.terminal.service.PosTerminalService;
import com.vanroid.dachuang.modules.terminal.service.TerBillDayService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by cgz on 16-10-26.
 */
public class TerBillDayServiceTest extends BaseTest {

    @Autowired
    private TerBillDayService terBillDayService;

    @Test
    public void testImport() {
        terBillDayService.importBillDays("/home/cgz/win7vm/LYGZ_20160613.xls");
    }


    // 清除上一次测试数据，使测试可以回归
    @Test
    public void clearTest() {
        //    SqlSessionFactory sqlSessionFactory = SpringContextHolder.getBean(SqlSessionFactory.class);
    }

}

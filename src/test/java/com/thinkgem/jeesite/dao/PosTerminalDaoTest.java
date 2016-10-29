package com.thinkgem.jeesite.dao;

import com.thinkgem.jeesite.BaseTest;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.vanroid.dachuang.modules.terminal.dao.PosTerminalDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cgz on 16-10-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-context*.xml")
public class PosTerminalDaoTest {
    @Autowired
    private PosTerminalDao posTerminalDao;

    @Autowired
    private OfficeDao officeDao;

    @Test
    public void testFindIds(){
        Office office = officeDao.get("16ccb233f92b4746a73d632c136ab2ff");
        posTerminalDao.findIdsByOffice(office);
    }
}

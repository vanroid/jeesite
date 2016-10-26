package com.thinkgem.jeesite.dao;

import com.thinkgem.jeesite.BaseTest;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cgz on 16-10-22.
 */
public class OfficeDaoTest extends BaseTest {

    @Autowired
    OfficeDao officeDao;

    @Autowired
    OfficeService officeService;

    @Test
    public void findByParentId(){

        Office o = new Office();
        o.setId("1");
        List<Office> list = officeService.findByParentId(o);
        //officeService.findList(o);
        //List<Office> list = officeDao.findByParentIdsLike(o);
        System.out.print(list.size());
    }
}

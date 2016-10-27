package com.thinkgem.jeesite.dao;

import com.thinkgem.jeesite.BaseTest;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.vanroid.dachuang.common.StatusConstants;
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

    @Test
    public void testSaveOffice(){
        Office rootOffice = new Office();
        rootOffice.setId(Global.getRootOfficeId());
        Area rootArea = new Area();
        rootArea.setId(Global.getRootAreaId());

        User curUser = UserUtils.getUser();
        if (curUser == null || StringUtils.isBlank(curUser.getId())) { // 使用默认导入操作
            curUser = UserUtils.get(Global.getDefaultUserId());
        }

        Office company = new Office();
        company.setName("test");
        company.setType(StatusConstants.OFFICE_TYPE_COMPANY);
        company.setArea(rootArea);
        company.setCreateBy(curUser);
        company.setUpdateBy(curUser);
        company.setAddress("test");

        // 所有机构都是在[大创电子总公司之下]
        company.setParent(rootOffice);

        officeService.save(company);
    }
}

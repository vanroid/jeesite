package com.thinkgem.jeesite.dao;

import com.thinkgem.jeesite.BaseTest;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.vanroid.dachuang.common.StatusConstants;
import com.vanroid.dachuang.modules.terminal.dao.TerBillDayDao;
import com.vanroid.dachuang.modules.terminal.entity.TerBillDay;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cgz on 16-10-22.
 */
public class TerBillDayDaoTest extends BaseTest {

    @Autowired
    private TerBillDayDao terBillDayDao;

    @Test
    public void testFindList() {
        TerBillDay entity = new TerBillDay();
        entity.setCurrentUser(UserUtils.get("b9cdd0e5e6154b04b42570c27416b284"));
        entity.getSqlMap().put("dsf", BaseService.dataScopeFilter(entity.getCurrentUser(), "o", ""));
        //System.out.print(entity.getSqlMap().get("dsf"));
        terBillDayDao.findList(entity);
    }
}

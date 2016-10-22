package com.vanroid.dachuang.common;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

/**
 * Created by cgz on 16-10-22.
 */
public class DaChuangUtils {

    public static final Logger logger = Logger.getLogger(DaChuangUtils.class);

    public static final String BASE_OFFICE = "总部";

    public static void transformUser(User user) {
        // 设置用户类型为2：部门经理
        user.setUserType("2");
    }

    public static void transformRoleList(List<Role> roleList) {
        for (Iterator<Role> iter = roleList.iterator(); iter.hasNext(); ) {
            if (iter.next().getId().equals("1")) {
                iter.remove();
            }
        }
    }

    public static Office transformChildOffices(List<Office> childOffices) {
        for (Office o : childOffices) {
            if (o.getName().equals(BASE_OFFICE))
                return o;
        }
        logger.error("没有找到[总部]部门");
        return null;
    }
}

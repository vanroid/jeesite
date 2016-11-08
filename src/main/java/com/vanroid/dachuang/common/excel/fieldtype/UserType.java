package com.vanroid.dachuang.common.excel.fieldtype;

import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * Created by cgz on 16-11-8.
 */
public class UserType {
    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null) {
            @SuppressWarnings("unchecked")
            User user = (User) val;
            return user.getName();
        }
        return "";
    }
}

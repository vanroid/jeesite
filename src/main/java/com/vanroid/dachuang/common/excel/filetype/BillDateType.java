package com.vanroid.dachuang.common.excel.filetype;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * Created by cgz on 16-11-8.
 * excel中文本20160910 <-> java.util.Date
 */
public class BillDateType {

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null) {
            if (val instanceof Date) {
                Date date = (Date) val;
                return DateUtils.formatDate(date, "yyyyMMdd");
            } else {
                throw new RuntimeException("BillDateType只能声明Date格式");
            }
        }
        return "";
    }

    /**
     * 设置对象值（导入）
     * 传入形式 yyyyMM
     */
    public static Date getValue(String val) {
        System.out.println("!!!!!" + val);
        if (val != null) {
            if (val.length() < "20160910".length())
                return DateUtils.parseDate(val.toString() + "01");
            else
                return DateUtils.parseDate(val.toString());
        }
        return null;
    }
}

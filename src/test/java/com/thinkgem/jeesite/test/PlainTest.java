package com.thinkgem.jeesite.test;

import com.thinkgem.jeesite.common.utils.DateUtils;
import org.junit.Test;

public class PlainTest {

    @Test
    public void testDateUtils() {
        String s = DateUtils.getFirstDayOnMonth();
        System.out.println(s);
    }
}

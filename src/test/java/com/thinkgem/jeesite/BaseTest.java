package com.thinkgem.jeesite;

import com.thinkgem.jeesite.common.config.Global;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cgz on 16-10-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-context*.xml")
public class BaseTest {
    public BaseTest(){
        Global.ENV_JUNIT_TEST = true;
    }
}

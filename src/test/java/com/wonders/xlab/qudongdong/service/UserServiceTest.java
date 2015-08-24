package com.wonders.xlab.qudongdong.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.wonders.xlab.qudongdong.test.AbstractSpringTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mars on 15/8/19.
 */
@DatabaseSetup("UserServiceTest-DataSet.xml")
public class UserServiceTest extends AbstractSpringTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testCount() throws Exception {
        Assert.assertEquals(5, userService.count());
    }

    @Test
    @ExpectedDatabase(value = "UserServiceTest-testDeleteAll-result.xml",
            table = "qdd_user", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteAll() throws Exception {
        userService.deleteAll();
    }
}
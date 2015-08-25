package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mars on 15/8/18.
 */
@RestController
@RequestMapping("user")
public class UserController extends AbstractBaseController<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected MyRepository<User, Long> getRepository() {
        return userRepository;
    }

    @RequestMapping(value = "retrieveInfo/{openId}", method = RequestMethod.GET)
    public Object retrieveInfo(@PathVariable String openId) {

        User user = userRepository.findByOpenId(openId);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(user)
                .setMessage("成功");
    }


}

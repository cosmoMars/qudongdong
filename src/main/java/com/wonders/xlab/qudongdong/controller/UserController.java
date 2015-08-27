package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.UserDto;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "retrieveInfo/{userId}", method = RequestMethod.GET)
    public Object retrieveInfo(@PathVariable long userId) {

        User user = userRepository.findOne(userId);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(user)
                .setMessage("成功");
    }

    /**
     * @param userId
     * @param info
     * @param type   0(tel):1(sex):2(age):3(height):4():5(weChat)
     * @return
     */
    @RequestMapping(value = "editInfo/{userId}/{info}/{type}", method = RequestMethod.POST)
    public Object editInfo(@PathVariable long userId, @PathVariable String info, @PathVariable int type) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            ControllerResult result = new ControllerResult<>()
                    .setRet_code(0)
                    .setRet_values("修改成功！")
                    .setMessage("成功");
            switch (type) {
                case 0:
                    if (!info.matches("^1((3|5|8){1}\\d{1}|70|77)\\d{8}$")) {
                        return new ControllerResult<>()
                                .setRet_code(-1)
                                .setRet_values("骚年，手机格式不正确")
                                .setMessage("失败");
                    }
                    user.setTel(info);
                    userRepository.save(user);
                    return result;
                case 1:
                    if (info.equals("Female")) {
                        user.setSex(User.Sex.Female);
                        userRepository.save(user);
                        return result;
                    } else if (info.equals("Male")) {
                        user.setSex(User.Sex.Male);
                        userRepository.save(user);
                        return result;
                    }
                case 2:
                    user.setAge(Integer.parseInt(info));
                    userRepository.save(user);
                    return result;
                case 3:
                    user.setHeight(Double.parseDouble(info));
                    userRepository.save(user);
                    return result;
                case 4:
                    user.setWeight(Double.parseDouble(info));
                    userRepository.save(user);
                    return result;
                case 5:
                    user.setWeChat(info);
                    userRepository.save(user);
                    return result;
                default:
                    return new ControllerResult<>()
                            .setRet_code(-1)
                            .setRet_values("请求失误！")
                            .setMessage("失败");
            }
        }
        return new ControllerResult<>()
                .setRet_code(-1)
                .setRet_values("该用户不存在！")
                .setMessage("失败");
    }


    /**
     * 修改用户
     * @param userId
     * @param dto
     * @return
     */
    @RequestMapping(value = "updateUser/{userId}", method = RequestMethod.POST)
    public Object updateUser(@PathVariable long userId, @RequestBody UserDto dto) {

        User user = userRepository.findOne(userId);

        User resultUser = dto.updateUser(user);

        userRepository.save(resultUser);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("修改成功")
                .setMessage("成功");

    }

}

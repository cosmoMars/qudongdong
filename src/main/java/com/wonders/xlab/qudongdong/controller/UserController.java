package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.UserDto;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.Sport;
import com.wonders.xlab.qudongdong.entity.SportOrder;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.repository.SportOrderRepository;
import com.wonders.xlab.qudongdong.repository.SportRepository;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * Created by mars on 15/8/18.
 */
@RestController
@RequestMapping("user")
public class UserController extends AbstractBaseController<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportOrderRepository sportOrderRepository;

    @Autowired
    private SportRepository sportRepository;

    @Override
    protected MyRepository<User, Long> getRepository() {
        return userRepository;
    }

    @RequestMapping(value = "retrieveInfo/{userId}", method = RequestMethod.GET)
    public Object retrieveInfo(@PathVariable long userId) {

        User user = userRepository.findOne(userId);
        Set<Sport> sports = new HashSet<>(user.getSports());
        user.setSports(sports);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(user)
                .setMessage("成功");
    }

    /**
     * @param userId
     * @param info
     * @param type   0(tel):1(sex):2(age):3(height):4():5(weChat):6(sports)
     * @return
     */
    @RequestMapping(value = "editInfo/{userId}/{info}/{type}", method = RequestMethod.POST)
    public Object editInfo(@PathVariable long userId, @PathVariable String info, @PathVariable int type) {
        User user = userRepository.findOne(userId);

        if (user == null) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("该用户不存在！")
                    .setMessage("失败");
        }

        switch (type) {
            case 0:
                if (!info.matches("^1((3|5|8){1}\\d{1}|70|77)\\d{8}$")) {
                    return new ControllerResult<>()
                            .setRet_code(-1)
                            .setRet_values("骚年，手机格式不正确")
                            .setMessage("失败");
                }
                user.setTel(info);

            case 1:
                if (info.equals("Female")) {
                    user.setSex(User.Sex.Female);
                } else if (info.equals("Male")) {
                    user.setSex(User.Sex.Male);
                }
            case 2:
                user.setAge(Integer.parseInt(info));
            case 3:
                user.setHeight(Double.parseDouble(info));
            case 4:
                user.setWeight(Double.parseDouble(info));
            case 5:
                user.setWeChat(info);
            case 6:
                String[] str = StringUtils.split(info, ",");
                List<Long> ids = new ArrayList<>();
                for (String s : str) {
                    ids.add(NumberUtils.toLong(s));
                }
                List<Sport> sports = sportRepository.findAll(ids);
                user.setSports(new HashSet<>(sports));
                for (int i = 0; i < str.length; i++) {
                    System.out.println(str[i]);
                }
        }
        userRepository.save(user);
        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("修改成功！")
                .setMessage("成功");

    }


    /**
     * 修改用户
     *
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

    /**
     * 校验用户手机和微信号
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "validateUser/{userId}", method = RequestMethod.GET)
    public Object validateUser(@PathVariable long userId) {
        User user = userRepository.findOne(userId);

        if (StringUtils.isEmpty(user.getTel()) || StringUtils.isEmpty(user.getWeChat())) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("骚年，请先去完善个人信息")
                    .setMessage("失败");
        }
        SportOrder existOrder = sportOrderRepository.findTopByUserIdOrderByCreatedDateDesc(userId);
        if (existOrder != null && DateUtils.isSameDay(new Date(), existOrder.getCreatedDate())) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("骚年，你今天已经躁动过咯～")
                    .setMessage("失败");
        }
        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("校验通过")
                .setMessage("成功");
    }

}

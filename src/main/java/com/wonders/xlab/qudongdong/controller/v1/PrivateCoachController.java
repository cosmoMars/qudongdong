package com.wonders.xlab.qudongdong.controller.v1;

import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.v1.PrivateCoach;
import com.wonders.xlab.qudongdong.repository.v1.PrivateCoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangJie on 16/4/21.
 */
@RestController
@RequestMapping("v1/privateCoach")
public class PrivateCoachController {

    @Autowired
    private PrivateCoachRepository privateCoachRepository;

    /**
     * 保存我要私教信息
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ControllerResult savePrivateCoachInfor(@RequestBody PrivateCoach privateCoach) {
        PrivateCoach p = privateCoachRepository.save(privateCoach);
        return p != null ? new ControllerResult().setRet_code(0).setMessage("申请成功") : new ControllerResult().setRet_code(1).setMessage("申请失败");
    }


}

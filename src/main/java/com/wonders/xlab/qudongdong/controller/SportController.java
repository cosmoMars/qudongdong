package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.Sport;
import com.wonders.xlab.qudongdong.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mars on 15/8/27.
 */
@RestController
@RequestMapping("sport")
public class SportController extends AbstractBaseController<Sport, Long> {

    @Autowired
    private SportRepository sportRepository;

    @Override
    protected MyRepository<Sport, Long> getRepository() {
        return sportRepository;
    }


    @RequestMapping(value = "listSport", method = RequestMethod.GET)
    public Object listSport() {

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(sportRepository.findByEnabledTrue())
                .setMessage("成功");
    }
}

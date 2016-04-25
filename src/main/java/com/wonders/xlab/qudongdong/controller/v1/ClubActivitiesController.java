package com.wonders.xlab.qudongdong.controller.v1;

import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.v1.ClubActivities;
import com.wonders.xlab.qudongdong.repository.v1.ClubActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ZhangJie on 16/4/22.
 */
@RestController
@RequestMapping("v1/clubActivities")
public class ClubActivitiesController {

    @Autowired
    private ClubActivitiesRepository clubActivitiesRepository;

    @RequestMapping(value = "publishClubActivities", method = RequestMethod.POST)
    public ControllerResult publishClubActivities(@RequestBody ClubActivities clubActivities) {
        ClubActivities c = clubActivitiesRepository.save(clubActivities);
        return c != null ? new ControllerResult().setRet_code(0).setMessage("发布成功") : new ControllerResult().setRet_code(1).setMessage("发布失败");
    }

    @RequestMapping(value = "findAllClubActivities/{pageNum}", method = RequestMethod.GET)
    public ControllerResult findAllClubActivities(@PathVariable Long pageNum) {
        List<ClubActivities> clubActivities = clubActivitiesRepository.findAll();
        return new ControllerResult().setRet_code(0).setRet_values(clubActivities);
    }
}

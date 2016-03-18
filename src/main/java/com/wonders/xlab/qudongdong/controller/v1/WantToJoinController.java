package com.wonders.xlab.qudongdong.controller.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wonders.xlab.qudongdong.dto.v1.WantToJoinDto;
import com.wonders.xlab.qudongdong.entity.v1.ServiceProject;
import com.wonders.xlab.qudongdong.repository.v1.ServiceProjectRepository;
import com.wonders.xlab.qudongdong.repository.v1.WantToJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 01054155 on 2016/3/18.
 */
@RestController
@RequestMapping("wantToJoin")
public class WantToJoinController {

    @Autowired
    private ServiceProjectRepository serviceProjectRepository;

    @Autowired
    private WantToJoinRepository wantToJoinRepository;

    @RequestMapping(value = "getWantToJoin", method = RequestMethod.GET)
    public Object getWantToJoin() {

        WantToJoinDto wantToJoinDto=new WantToJoinDto();

        wantToJoinDto.setWantToJoin(wantToJoinRepository.findAll());
        Map<String,List<ServiceProject>> stringListMap=new HashMap<>();
//        String second="次卡";
//        String month="月卡";
//        String year="年卡";
//        stringListMap.put(second,serviceProjectRepository.findByServiceType(second));
//        stringListMap.put(month,serviceProjectRepository.findByServiceType(month));
//        stringListMap.put(year,serviceProjectRepository.findByServiceType(year));
//        wantToJoinDto.setServiceProject(stringListMap);
        List<ServiceProject> secondList = new ArrayList<>();
        List<ServiceProject> monthList = new ArrayList<>();
        List<ServiceProject> yearList = new ArrayList<>();
        List<ServiceProject> serviceProjectList = serviceProjectRepository.findAll();
        for (ServiceProject project : serviceProjectList) {
            if("次卡".equals(project.getServiceType())){
                secondList.add(project);
            }else if("月卡".equals(project.getServiceType())){
                monthList.add(project);
            }else {
                yearList.add(project);
            }
        }
        stringListMap.put("次卡",secondList);
        stringListMap.put("月卡",monthList);
        stringListMap.put("年卡",yearList);
        wantToJoinDto.setServiceProject(stringListMap);
        return wantToJoinDto;
    }

}

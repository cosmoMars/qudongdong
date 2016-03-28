package com.wonders.xlab.qudongdong.controller.v1;

import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.dto.v1.ProjectDto;
import com.wonders.xlab.qudongdong.dto.v1.ProjectResultDto;
import com.wonders.xlab.qudongdong.entity.v1.BannerPic;
import com.wonders.xlab.qudongdong.entity.v1.ServiceProject;
import com.wonders.xlab.qudongdong.entity.v1.util.StaticContent;
import com.wonders.xlab.qudongdong.repository.v1.BannerPicRepository;
import com.wonders.xlab.qudongdong.repository.v1.ServiceProjectRepository;
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
@RequestMapping("v1/wantToJoin")
public class WantToJoinController {

    @Autowired
    private BannerPicRepository bannerPicRepository;

    @Autowired
    private ServiceProjectRepository serviceProjectRepository;

    @RequestMapping(value = "getClubInfo", method = RequestMethod.GET)
    public Object getClubInfo() {

        List<BannerPic> picList = bannerPicRepository.findByEnableTrueOrderByIdAsc();

        String content = StaticContent.content;

        List<ServiceProject> projects = serviceProjectRepository.findByEnableTrueOrderByIdAsc();

        Map<String, List<ProjectDto>> map = new HashMap<>();

        for (ServiceProject project : projects) {
            List<ProjectDto> serviceProjects = map.get(project.getServiceType().getName());
            if (serviceProjects != null) {
                ProjectDto dto = new ProjectDto();
                dto.setId(project.getId());
                dto.setServiceInfo(project.getService());

                serviceProjects.add(dto);
            } else {
                List<ProjectDto> projectList = new ArrayList<>();
                ProjectDto dto = new ProjectDto();
                dto.setId(project.getId());
                dto.setServiceInfo(project.getService());

                projectList.add(dto);
                map.put(project.getServiceType().getName(), projectList);
            }
        }

        List<ProjectResultDto> resultDtos = new ArrayList<>();
        for (String s : map.keySet()) {
            ProjectResultDto dto = new ProjectResultDto();
            dto.setName(s);
            dto.setProjects(map.get(s));
            resultDtos.add(dto);
        }
        Map<String, Object> result = new HashMap<>();

        result.put("picList", picList);
        result.put("content", content);
        result.put("result", resultDtos);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(result);
    }

}

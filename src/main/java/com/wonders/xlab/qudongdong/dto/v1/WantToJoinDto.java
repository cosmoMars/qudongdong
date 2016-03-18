package com.wonders.xlab.qudongdong.dto.v1;

import com.sun.javafx.collections.MappingChange;
import com.wonders.xlab.qudongdong.entity.v1.ServiceProject;
import com.wonders.xlab.qudongdong.entity.v1.WantToJoin;

import java.util.List;
import java.util.Map;

/**
 * Created by 01054155 on 2016/3/18.
 */
public class WantToJoinDto {

    private List<WantToJoin> wantToJoin;

    private Map<String,List<ServiceProject>> serviceProject;

    public List<WantToJoin> getWantToJoin() {
        return wantToJoin;
    }

    public void setWantToJoin(List<WantToJoin> wantToJoin) {
        this.wantToJoin = wantToJoin;
    }

    public Map<String, List<ServiceProject>> getServiceProject() {
        return serviceProject;
    }

    public void setServiceProject(Map<String, List<ServiceProject>> serviceProject) {
        this.serviceProject = serviceProject;
    }
}

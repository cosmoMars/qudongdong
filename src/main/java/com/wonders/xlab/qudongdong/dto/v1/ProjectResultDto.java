package com.wonders.xlab.qudongdong.dto.v1;

import java.util.List;

/**
 * Created by wanda2 on 16/3/28.
 */
public class ProjectResultDto {

    private String name;

    private List<ProjectDto> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}

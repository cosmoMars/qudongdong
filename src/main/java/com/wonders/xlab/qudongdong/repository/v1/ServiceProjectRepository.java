package com.wonders.xlab.qudongdong.repository.v1;

import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.v1.ServiceProject;
import com.wonders.xlab.qudongdong.entity.v1.ServiceType;

import java.util.List;

/**
 * Created by 01054155 on 2016/3/18.
 */
public interface ServiceProjectRepository extends MyRepository<ServiceProject, Long> {

    List<ServiceProject> findByEnableTrueOrderByIdAsc();

    List<ServiceProject> findByServiceTypeAndEnableTrueOrderByNumberAsc(ServiceType type);
}

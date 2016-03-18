package com.wonders.xlab.qudongdong.entity.v1;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wanda2 on 16/3/18.
 * 服务项目
 */
@Entity
@Table(name = "qdd_v1_service_project")
public class ServiceProject extends AbstractBaseEntity<Long>{

    private String serviceType;

    private String service;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}

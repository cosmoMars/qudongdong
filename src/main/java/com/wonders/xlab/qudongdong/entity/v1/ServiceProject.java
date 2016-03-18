package com.wonders.xlab.qudongdong.entity.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wanda2 on 16/3/18.
 * 服务项目
 */
@Entity
@Table(name = "qdd_v1_service_project")
@JsonIgnoreProperties("new")
public class ServiceProject extends AbstractBaseEntity<Long>{
    /**
     * 服务类型
     * 记次卡 记月卡 记年卡
     */
    private String serviceType;
    /**
     * 服务内容
     */
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

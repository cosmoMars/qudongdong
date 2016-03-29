package com.wonders.xlab.qudongdong.entity.v1;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by wanda2 on 16/3/18.
 * 服务项目
 */
@Entity
@Table(name = "qdd_service_project")
public class ServiceProject extends AbstractBaseEntity<Long>{
    /**
     * 服务类型
     * 记次卡 记月卡 记年卡
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceType serviceType;
    /**
     * 服务内容
     */
    private String service;

    private boolean enable;

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

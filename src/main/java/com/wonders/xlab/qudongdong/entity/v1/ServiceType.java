package com.wonders.xlab.qudongdong.entity.v1;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wanda2 on 16/3/28.
 */
@Entity
@Table(name = "qdd_service_type")
public class ServiceType extends AbstractBaseEntity<Long>{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

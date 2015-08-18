package com.wonders.xlab.qudongdong.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by mars on 15/8/17.
 */
@Entity
@Table(name = "QDD_SPORT")
public class Sport extends AbstractPersistable<Long> {

    /**
     * 名称
     */
    private String name;

    /**
     * 启用
     */
    private boolean enabled;

    /**
     * 图标地址
     */
    private String iconUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}

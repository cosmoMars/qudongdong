package com.wonders.xlab.qudongdong.entity;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 场馆
 * Created by yk on 15/11/12.
 */
@Entity
@Table(name = "qdd_venue")
public class Venue extends AbstractBaseEntity<Long> {


    //场馆名称
    private String VenueName;
    //场馆地址
    private String VenueAdress;
    //场馆图片url
    private String picUrl;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;


    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getVenueName() {
        return VenueName;
    }

    public void setVenueName(String venueName) {
        VenueName = venueName;
    }

    public String getVenueAdress() {
        return VenueAdress;
    }

    public void setVenueAdress(String venueAdress) {
        VenueAdress = venueAdress;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

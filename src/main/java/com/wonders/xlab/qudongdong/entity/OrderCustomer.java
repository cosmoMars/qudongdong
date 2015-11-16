package com.wonders.xlab.qudongdong.entity;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by mars on 15/8/18.
 */
@Entity
@Table(name = "QDD_ORDER_CUSTOMER")
public class OrderCustomer extends AbstractBaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private SportOrder sportOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private SportOrderOfficial sportOrderOfficial;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User customer;

    private Boolean userAgree;

    //是否官方
    private boolean official;


    public SportOrder getSportOrder() {
        return sportOrder;
    }

    public void setSportOrder(SportOrder sportOrder) {
        this.sportOrder = sportOrder;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Boolean getUserAgree() {
        return userAgree;
    }

    public void setUserAgree(Boolean userAgree) {
        this.userAgree = userAgree;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public SportOrderOfficial getSportOrderOfficial() {
        return sportOrderOfficial;
    }

    public void setSportOrderOfficial(SportOrderOfficial sportOrderOfficial) {
        this.sportOrderOfficial = sportOrderOfficial;
    }
}

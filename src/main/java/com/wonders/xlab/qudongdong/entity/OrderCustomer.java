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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SportOrder sportOrder;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User customer;

    private Boolean userAgree;

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
}

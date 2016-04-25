package com.wonders.xlab.qudongdong.entity.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wonders.xlab.qudongdong.AbstractBaseEntity;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ZhangJie on 16/4/21.
 */
//私教表实体类
@Entity
@JsonIgnoreProperties("new")
@Table(name = "qdd_private_coach")
public class PrivateCoach extends AbstractPersistable<Long> {

    //真实姓名
    @Column(nullable = false)
    private String name;

    //联系电话
    @Column(nullable = false)
    private String phoneNumber;

    //教练资质
    @Column(nullable = false)
    private Qualifications coachQualifications;

    //预约地点
    @Column(length = 2000)
    private String orderAddress;

    //预约日期
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date orderDate;

    //预约时间
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date orderTime;

    private enum Qualifications {
        primary, intermediate, senior
    }


    public Qualifications getCoachQualifications() {
        return coachQualifications;
    }

    public void setCoachQualifications(Qualifications coachQualifications) {
        this.coachQualifications = coachQualifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}

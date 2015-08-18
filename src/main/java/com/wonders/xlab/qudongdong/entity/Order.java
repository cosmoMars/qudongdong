package com.wonders.xlab.qudongdong.entity;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mars on 15/8/18.
 */
@Entity
@Table(name = "QDD_ORDER")
public class Order extends AbstractBaseEntity<Long> {

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    /**
     * 订单用户
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<OrderCustomer> orderCustomers;

    /**
     * 运动
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Sport sport;

    /**
     * 地点
     */
    private String location;

    /**
     * 性别
     */
    @Enumerated
    private Sex sex;

    public enum Sex {
        Male, Female, All
    }

    /**
     * 年龄范围
     */
    @Enumerated
    private AgeRange ageRange;

    public enum AgeRange {
        From18To24, From25To30, All
    }

    /**
     * 付款方式
     */
    @Enumerated
    private PayMethod payMethod;

    public enum PayMethod {
        AA, Pay
    }

    /**
     * 是否接送
     */
    private boolean transfer;

    /**
     * 可携带一名伙伴
     */
    private boolean carryOne;

    /**
     * 参与人数
     */
    private int peopleCount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderCustomer> getOrderCustomers() {
        return orderCustomers;
    }

    public void setOrderCustomers(Set<OrderCustomer> orderCustomers) {
        this.orderCustomers = orderCustomers;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public boolean isCarryOne() {
        return carryOne;
    }

    public void setCarryOne(boolean carryOne) {
        this.carryOne = carryOne;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
}

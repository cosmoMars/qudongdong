package com.wonders.xlab.qudongdong.entity;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

/**
 * Created by yk on 15/11/12.
 */
@Entity
@Table(name = "QDD_SPORT_ORDER_OFFICIAL")
public class SportOrderOfficial extends AbstractBaseEntity<Long> {

        /**
         * 用户
         */
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        private User user;

        /**
         * 订单用户
         */
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "sportOrder", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
        private Set<OrderCustomer> orderCustomers;

        /**
         * 运动, optional = false
         */
        @ManyToOne(fetch = FetchType.LAZY)
        private Sport sport;

        /**
         * 地点
         */
        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        private AreaCode areaCode;

        //场馆
        @ManyToOne(fetch = FetchType.EAGER,optional = true)
        private Venue venue;

        /**
         * 地点
         */
        private String location;

        /**
         * 性别
         */
        @Enumerated
        private User.Sex sex;

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
         * 当前人数
         */
        private int currentCount;

        /**
         * 参与人数
         */
        private int peopleCount;

        /**
         * 订单状态
         */
        @Enumerated
        private Status status;

        public enum Status {
            Todo, Doing, End
        }

        /**
         * 简介
         */
        @Pattern(regexp = "^.{0,12}$", message = "简介不能超过12个字")
        private String content;

        /**
         * 运动开始时间
         */
        @Temporal(TemporalType.TIMESTAMP)
        private Date startTime;

        /**
         * 运动结束时间
         */
        @Temporal(TemporalType.TIMESTAMP)
        private Date endTime;

        /**
         * 是否官方
         */
        private boolean official;

        /**
         * 富文本页面
         */
        @Lob
        private String htmlInfo;

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

        public User.Sex getSex() {
            return sex;
        }

        public void setSex(User.Sex sex) {
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

        public int getCurrentCount() {
            return currentCount;
        }

        public void setCurrentCount(int currentCount) {
            this.currentCount = currentCount;
        }

        public int getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(int peopleCount) {
            this.peopleCount = peopleCount;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public boolean isOfficial() {
            return official;
        }

        public void setOfficial(boolean official) {
            this.official = official;
        }

        public String getHtmlInfo() {
            return htmlInfo;
        }

        public void setHtmlInfo(String htmlInfo) {
            this.htmlInfo = htmlInfo;
        }

        public AreaCode getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(AreaCode areaCode) {
            this.areaCode = areaCode;
        }

        public Venue getVenue() {
            return venue;
        }

        public void setVenue(Venue venue) {
            this.venue = venue;
        }
}

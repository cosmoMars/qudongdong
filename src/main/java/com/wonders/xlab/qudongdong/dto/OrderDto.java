package com.wonders.xlab.qudongdong.dto;

import com.wonders.xlab.qudongdong.entity.Sport;
import com.wonders.xlab.qudongdong.entity.Venue;

import java.util.List;

/**
 * Created by mars on 15/8/21.
 */
public class OrderDto {

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 订单地点
     */
    private String location;

    /**
     * 总人数
     */
    private int peopleCount;

    /**
     * 现在人数
     */
    private int currentPeople;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private int sex;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 运动名称
     */
    private String sportName;

    /**
     * 用户所有运动
     */
    private List<Sport> sports;

    /**
     * 订单简介
     */
    private String content;

    /**
     * 是否可参加
     */
    private boolean enabled;

    /**
     * 完成人数百分比
     */
    private int percent;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 过去时间
     */
    private String diffTime;


    /**
     * 年龄
     */
    private int age;

    //加入的用户头像list
    private List picUrls;

    private Venue venue;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public int getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(int currentPeople) {
        this.currentPeople = currentPeople;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(String diffTime) {
        this.diffTime = diffTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List picUrls) {
        this.picUrls = picUrls;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}

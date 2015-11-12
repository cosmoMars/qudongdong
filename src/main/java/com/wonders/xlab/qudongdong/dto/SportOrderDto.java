package com.wonders.xlab.qudongdong.dto;

import com.wonders.xlab.qudongdong.entity.SportOrder;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.entity.Venue;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;

/**
 * Created by mars on 15/8/18.
 */
public class SportOrderDto {

    /**
     * 地点
     */
    @NotNull(message = "地点不能为空")
    private String location;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 年龄范围
     */
    @NotNull(message = "年龄不能为空")
    private Integer ageRange;

    /**
     * 付款方式
     */
    @NotNull(message = "付款方式不能为空")
    private Integer payMethod;

    /**
     * 是否接送
     */
    @NotNull(message = "是否接送不能为空")
    private Boolean transfer;

    /**
     * 可携带一名伙伴
     */
    @NotNull(message = "是否可携带一名伙伴不能为空")
    private Boolean carryOne;

    /**
     * 参与人数
     */
    private int peopleCount;

    /**
     * 简介
     */
    @Pattern(regexp = "^.{0,12}$", message = "简介不能超过12个字")
    private String content;

    /**
     * 运动开始时间
     */
    @NotNull(message = "运动开始时间不能为空")
    private String startTime;

    /**
     * 运动开始时间
     */
    @NotNull(message = "运动结束时间不能为空")
    private String endTime;

    private Boolean official;

    /**
     * 富文本
     */
    private String htmlInfo;

    /**
     * 地区id
     */
    private Long areaCodeId;

    //场地id
    private long venueId;

    public SportOrder toNewOrder() {
        SportOrder order = new SportOrder();
        order.setLocation(location);
        order.setSex(User.Sex.values()[sex]);
        order.setAgeRange(SportOrder.AgeRange.values()[ageRange]);
        order.setPayMethod(SportOrder.PayMethod.values()[payMethod]);
        order.setTransfer(transfer);
        order.setCarryOne(carryOne);
        order.setContent(content);
        order.setStatus(SportOrder.Status.Todo);
        try {
            order.setStartTime(DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
            order.setEndTime(DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return order;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(Integer ageRange) {
        this.ageRange = ageRange;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Boolean getTransfer() {
        return transfer;
    }

    public void setTransfer(Boolean transfer) {
        this.transfer = transfer;
    }

    public Boolean getCarryOne() {
        return carryOne;
    }

    public void setCarryOne(Boolean carryOne) {
        this.carryOne = carryOne;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    public String getHtmlInfo() {
        return htmlInfo;
    }

    public void setHtmlInfo(String htmlInfo) {
        this.htmlInfo = htmlInfo;
    }

    public Long getAreaCodeId() {
        return areaCodeId;
    }

    public void setAreaCodeId(Long areaCodeId) {
        this.areaCodeId = areaCodeId;
    }

    public long getVenueId() {
        return venueId;
    }

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }
}

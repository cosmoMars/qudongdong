package com.wonders.xlab.qudongdong.dto;

import com.wonders.xlab.qudongdong.entity.User;

import java.util.List;

/**
 * Created by mars on 15/8/26.
 */
public class UserDto {

//    @NotNull(message = "手机不能为空")
    private String tel;

    private String weChat;

    private Integer sex;

    private Integer age;

    private Double height;

    private Double weight;

    private List<Long> sportIds;


    public User updateUser(User user) {
        user.setTel(tel);
        user.setWeChat(weChat);
        user.setSex(User.Sex.values()[sex]);
        user.setAge(age);
        user.setHeight(height);
        user.setWeight(weight);
        return user;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public List<Long> getSportIds() {
        return sportIds;
    }

    public void setSportIds(List<Long> sportIds) {
        this.sportIds = sportIds;
    }
}

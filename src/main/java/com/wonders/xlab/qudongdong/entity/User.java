package com.wonders.xlab.qudongdong.entity;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mars on 15/8/17.
 */
@Entity
@Table(name = "QDD_USER")
public class User extends AbstractBaseEntity<Long> {

    /**
     * 手机
     */
    private String tel;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 身高
     */
    private double height;

    /**
     * 体重
     */
    private double weight;

    /**
     * 年龄
     */
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "qdd_user_sport_relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    @OrderBy("id asc")
    private Set<Sport> sports;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }
}

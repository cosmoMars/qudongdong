package com.wonders.xlab.qudongdong.entity.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wonders.xlab.qudongdong.AbstractBaseEntity;
import org.junit.Ignore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ZhangJie on 16/4/22.
 */
//聚乐部活动表
@Entity
@JsonIgnoreProperties("new")
@Table(name = "qdd_club_activities")
public class ClubActivities extends AbstractPersistable<Long> {

    //聚乐部名称
    @Column(unique = true, nullable = false, length = 50)
    private String clubName;
    //聚乐部LOGO
    @Column(length = 1000)
    private String clubLogo;
    //聚乐部主题
    private String clubSubject;
    //场馆名称
    private String venuesName;
    //场馆地址
    private String venuesAddress;
    //开始时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    //结束时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    //人数
    private Long number;
    //说明
    @Column(length = 60)
    private String intro;

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubSubject() {
        return clubSubject;
    }

    public void setClubSubject(String clubSubject) {
        this.clubSubject = clubSubject;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getVenuesAddress() {
        return venuesAddress;
    }

    public void setVenuesAddress(String venuesAddress) {
        this.venuesAddress = venuesAddress;
    }

    public String getVenuesName() {
        return venuesName;
    }

    public void setVenuesName(String venuesName) {
        this.venuesName = venuesName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
package com.wonders.xlab.qudongdong.entity.v1;

import com.wonders.xlab.qudongdong.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wanda2 on 16/3/18.
 * 我要入会
 */
@Entity
@Table(name = "qdd_v1_wtj")
public class WantToJoin extends AbstractBaseEntity<Long> {

    /**
     * 图片
     */
    private String[] pictures;

    /**
     * 入会须知
     */
    private String admissionNotice;

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public String getAdmissionNotice() {
        return admissionNotice;
    }

    public void setAdmissionNotice(String admissionNotice) {
        this.admissionNotice = admissionNotice;
    }
}

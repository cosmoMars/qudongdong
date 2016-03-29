package com.wonders.xlab.qudongdong.dto.v1;

/**
 * Created by wanda2 on 16/3/28.
 */
public class ProjectDto {

    private long id;

    private String serviceInfo;

    private String number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

package com.cdboo.business.model;

import com.cdboo.business.entity.PlanModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户rest实体
 * Created by mmzz on 2016/12/28.
 */
public class RestModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;//用户姓名
    private String shopOwnerName;//店长姓名

    private Date businessHoursBegin;//营业时间开始
    private Date businessHoursEnd;//营业时间结束

    private String address;//门店地址
    private String photo;//用户头像

    private Date serviceTimeBegin;//用户服务时间开始时间，就是用户买了多久的会员就能用多久
    private Date serviceTimeEnd;//用户服务时间结束时间

    private List<PlanModel> planModelList; // 播放计划

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShopOwnerName() {
        return shopOwnerName;
    }

    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName;
    }

    public Date getBusinessHoursBegin() {
        return businessHoursBegin;
    }

    public void setBusinessHoursBegin(Date businessHoursBegin) {
        this.businessHoursBegin = businessHoursBegin;
    }

    public Date getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    public void setBusinessHoursEnd(Date businessHoursEnd) {
        this.businessHoursEnd = businessHoursEnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<PlanModel> getPlanModelList() {
        return planModelList;
    }

    public void setPlanModelList(List<PlanModel> planModelList) {
        this.planModelList = planModelList;
    }

    public Date getServiceTimeBegin() {
        return serviceTimeBegin;
    }

    public void setServiceTimeBegin(Date serviceTimeBegin) {
        this.serviceTimeBegin = serviceTimeBegin;
    }

    public Date getServiceTimeEnd() {
        return serviceTimeEnd;
    }

    public void setServiceTimeEnd(Date serviceTimeEnd) {
        this.serviceTimeEnd = serviceTimeEnd;
    }

}

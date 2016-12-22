package com.cdboo.business.model;

import java.io.Serializable;

/**
 * 店铺信息
 * Created by houyi on 2016/12/16 0016.
 */
public class Shop implements Serializable{
    private String address; // 店铺地址
    private String bussnessHours; // 营业时间
    private String owner; // 店长
    private String telephone; // 联系电话

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBussnessHours() {
        return bussnessHours;
    }

    public void setBussnessHours(String bussnessHours) {
        this.bussnessHours = bussnessHours;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

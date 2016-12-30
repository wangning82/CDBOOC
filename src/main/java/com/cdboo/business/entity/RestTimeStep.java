package com.cdboo.business.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户时段信息
 */
@Entity
@Table(name = "cdboo_period")
public class RestTimeStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "period_NO")
    private String timestepNo; // 时段编号

    @Column(name = "period_name")
    private String timestepName;      // 时段名称

    @Column(name = "starttime")
    private String starttime;        // 开始时间

    @Column(name = "endtime")
    private String endtime;        // 结束时间

    public String getTimestepNo() {
        return timestepNo;
    }

    public void setTimestepNo(String timestepNo) {
        this.timestepNo = timestepNo;
    }

    public String getTimestepName() {
        return timestepName;
    }

    public void setTimestepName(String timestepName) {
        this.timestepName = timestepName;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

}

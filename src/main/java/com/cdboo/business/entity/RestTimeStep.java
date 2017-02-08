package com.cdboo.business.entity;

import javax.persistence.*;

/**
 * 用户时段信息
 */
@Entity
@Table(name = "cdboo_period")
public class RestTimeStep extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "period_id")
    private long id;

    @Column(name = "period_NO")
    private String timestepNo; // 时段编号

    @Column(name = "period_name")
    private String timestepName; // 时段名称

    @Column(name = "starttime")
    private String starttime; // 开始时间

    @Column(name = "endtime")
    private String endtime; // 结束时间

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestTimeStep)) return false;

        RestTimeStep that = (RestTimeStep) o;

        return timestepName.equals(that.timestepName);

    }

    @Override
    public int hashCode() {
        return timestepName.hashCode();
    }
}

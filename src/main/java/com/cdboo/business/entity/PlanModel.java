package com.cdboo.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 播放计划
 */
@Entity
@Table(name = "cdboo_plan")
public class PlanModel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "plan_id")
    private long id;

    @Column(name = "plan_NO")
    private String planNo; // 计划编号

    @Column(name = "plan_name")
    private String playName; // 计划名称

    @Column(name = "style")
    private String musicStyle; // 风格（1插播,2节日,3主题,4风格）

    @Column(name = "week")
    private String week; // 星期（数字，逗号分隔）

    @Column(name = "startDate")
    private Date startDate; // 开始日期（节日才有用）

    @Column(name = "endDate")
    private Date endDate; // 结束日期（节日才有用）

    @Column(name = "status")
    private String status; // 状态位显示（暂时没用）

    @Column(name = "cycleTimes")
    private int cycleTimes; //循环次数，只有插播才有效

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="period_id", unique=true, nullable=false, updatable=false)
    private RestTimeStep timestep; // 用户时段信息

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="channel_id", unique=true, nullable=false, updatable=false)
    private RestChannel channel; // 频道

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestTimeStep getTimestep() {
        return timestep;
    }

    public void setTimestep(RestTimeStep timestep) {
        this.timestep = timestep;
    }

    public RestChannel getChannel() {
        return channel;
    }

    public void setChannel(RestChannel channel) {
        this.channel = channel;
    }

    public int getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(int cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

package com.cdboo.business.entity;

import com.cdboo.business.common.Constants;

import javax.persistence.*;

/**
 * 用户音乐信息
 */
@Entity
@Table(name = "cdboo_music")
public class RestMusic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private long id;

    @Column(name = "music_NO")
    private String musicNo; // 音乐编号

    @Column(name = "music_owner")
    private String musicOwner; // 音乐拥有者类型

    @Column(name = "music_name")
    private String musicName; // 音乐名称

    @Column(name = "music_duration")
    private String duration; // 音乐时长

    @Column(name = "music_length")
    private Long length; // 音乐文件大小

    @Column(name = "actor")
    private String actor; // 艺人

    @Column(name = "special")
    private String special; // 专辑

    @Column(name = "volume")
    private String volume; // 音量

    @Column(name = "status")
    private String status; // 状态（暂时没用）

    @Column(name = "path")
    private String path; // 本地路径

    @Column(name = "source")
    private String source; // 服务器路径

    @Column(name = "favorite")
    private String favorite = Constants.FAVORITE_DEFAULT; // 收藏标志

    @Transient
    private String voice; // 人声

    public String getMusicNo() {
        return musicNo;
    }

    public void setMusicNo(String musicNo) {
        this.musicNo = musicNo;
    }

    public String getMusicOwner() {
        return musicOwner;
    }

    public void setMusicOwner(String musicOwner) {
        this.musicOwner = musicOwner;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestMusic)) return false;

        RestMusic music = (RestMusic) o;

        return path.equals(music.path);

    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}

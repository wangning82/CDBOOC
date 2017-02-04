package com.cdboo.business.model;

import java.io.Serializable;

/**
 * Created by houyi on 2017/2/4.
 */
public class MusicModel implements Serializable {
    private String path;
    private Long length;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicModel)) return false;

        MusicModel that = (MusicModel) o;

        return path.equals(that.path);

    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}

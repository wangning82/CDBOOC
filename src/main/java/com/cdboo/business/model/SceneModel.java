package com.cdboo.business.model;

import java.io.Serializable;

/**
 * Created by houyi on 2017/2/3 0003.
 * 客户端首页查询业态列表时使用
 */
public class SceneModel implements Serializable {
    private String icon;
    private String name;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SceneModel)) return false;

        SceneModel that = (SceneModel) o;

        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

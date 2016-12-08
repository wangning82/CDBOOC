package com.cdboo.business.ui.shared.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/7.
 */
public abstract class AbstractJPanel extends JPanel{

    public AbstractJPanel(){
        super();
    }

    public AbstractJPanel(LayoutManager layout) {
        super(layout);
        setFrameUp();
        initComponents();
    }

    /**
     * 组件样式
     */
    protected abstract void setFrameUp();

    /**
     * 组件位置
     */
    protected abstract void initComponents();

}

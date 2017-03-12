package com.cdboo.business.ui.shared.controller;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public abstract class AbstractFrameController {

    public abstract void prepareAndOpenFrame();

    /**
     * 按钮事件
     *
     * @param button
     * @param listener
     */
    protected void registerAction(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }

    /**
     * 复选框事件
     *
     * @param checkBox
     * @param itemListener
     */
    protected void registerItem(JCheckBox checkBox, ItemListener itemListener) {
        checkBox.addItemListener(itemListener);
    }

}

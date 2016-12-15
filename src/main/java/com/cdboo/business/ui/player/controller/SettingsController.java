package com.cdboo.business.ui.player.controller;

import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by houyi on 2016/12/15 0015.
 */
@Controller
public class SettingsController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Override
    public void prepareAndOpenFrame() {
        registerItem(mainFrame.getSettingsDialog().getAlwaysTop(), (ItemEvent e) -> doAlwaysTop(e));

        mainFrame.getSettingsDialog().getLogout().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO
            }
        });

    }

    private void doAlwaysTop(ItemEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getItem();
        if (checkBox.isSelected()) {
            // TODO
        }
    }
}

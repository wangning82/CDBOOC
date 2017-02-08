package com.cdboo.business.ui.player.controller;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by houyi on 2016/12/15 0015.
 */
@Controller
public class SettingsController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Override
    public void prepareAndOpenFrame() {
        registerItem(mainFrame.getSettingsDialog().getAlwaysTop(), (ItemEvent e) -> doAlwaysOnTop(e));

        // 退出登录
        mainFrame.getSettingsDialog().getLogout().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Config.getConfigInstance().setUserName(null);
                mainFrame.getTitleBarPanel().resetUserInfo();
                // TODO 时段信息
                Platform.runLater(() -> {
                    mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.blank"));
                });
            }
        });

    }

    // 窗口总在最前
    private void doAlwaysOnTop(ItemEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getItem();
        if (checkBox.isSelected()) {
            mainFrame.setAlwaysOnTop(true);
            mainFrame.addWindowListener(new WindowAdapter() {

                @Override
                public void windowLostFocus(WindowEvent e) {
                    mainFrame.requestFocus();
                    mainFrame.setLocation(0,0);
                }

                @Override
                public void windowIconified(WindowEvent e) {
                    mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            });
            Config.getConfigInstance().setAlwaysOnTop(true);
        }else{
            mainFrame.setAlwaysOnTop(false);
            Config.getConfigInstance().setAlwaysOnTop(false);
        }
    }
}

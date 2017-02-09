package com.cdboo.business.ui.player.controller;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.JComponentStyle;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.service.UserService;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by houyi on 2016/12/15 0015.
 */
@Controller
public class SettingsController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Autowired
    private UserService userService;

    @Autowired
    private TitleBarController titleBarController;

    @Override
    public void prepareAndOpenFrame() {
        registerItem(mainFrame.getSettingsDialog().getAlwaysTop(), (ItemEvent e) -> doAlwaysOnTop(e));

        // 退出登录
        mainFrame.getSettingsDialog().getLogout().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Config.getConfigInstance().setUserName(null);
                Config.getConfigInstance().setNickName(null);
                mainFrame.getTitleBarPanel().resetUserInfo();
                mainFrame.getPeriodDialog().setPeriodList(null);
                Platform.runLater(() -> {
                    mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.blank"));
                });
            }
        });

        // 恢复频道
        mainFrame.getSettingsDialog().getRestore().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!StringUtils.isEmpty(Config.getConfigInstance().getUserName())) {
                    userService.saveUserData(userService.getUserData(Config.getConfigInstance().getUserName()));
                    mainFrame.getTitleBarPanel().loadUserInfo();
                    Platform.runLater(() -> {
                        mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.index"));
                    });
                } else {
                    titleBarController.showLoginWindow();
                }
            }
        });

        // 检查更新
        mainFrame.getSettingsDialog().getCheckUpdate().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.shutdownAll();
                int width = 200, height = 140;
                JDialog dialog = new JDialog(new JFrame(), "提示信息", true);
                JPanel panel = new JPanel();
                JLabel label = new JLabel("功能开发中，敬请期待……");
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setPreferredSize(new Dimension(width, height));
                panel.add(label);
                dialog.add(panel);
                Point p = mainFrame.getLocationOnScreen();
                double x = p.getX() + mainFrame.getWidth() / 2 - width / 2;
                double y = p.getY() + mainFrame.getHeight() / 2 - height / 2;
                Point showPossition = new Point(new Double(x).intValue(), new Double(y).intValue());
                if (showPossition == null || (showPossition.x < 0 && showPossition.y < 0))
                    dialog.setLocationRelativeTo(null);
                else
                    dialog.setLocation(new Point(showPossition.x < 0 ? 0 : showPossition.x, showPossition.y < 0 ? 0 : showPossition.y));
                dialog.pack();
                dialog.setVisible(true);
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
                    mainFrame.setLocation(0, 0);
                }

                @Override
                public void windowIconified(WindowEvent e) {
                    mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            });
            Config.getConfigInstance().setAlwaysOnTop(true);
        } else {
            mainFrame.setAlwaysOnTop(false);
            Config.getConfigInstance().setAlwaysOnTop(false);
        }
    }
}

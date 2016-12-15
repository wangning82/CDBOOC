package com.cdboo.business.ui.player.controller;

import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.player.view.TitleBarPanel;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by houyi on 2016/11/30.
 */
@Controller
public class MainController extends AbstractFrameController {

    @Autowired
    private MainFrame mainFrame;

    @Autowired
    private TitleBarController titleBarController;

    @Autowired
    private LoginController loginController;

    @Autowired
    private SettingsController settingsController;

    private Point origin = new Point(); // 初始位置

    @Override
    public void prepareAndOpenFrame() {
        titleBarController.prepareAndOpenFrame();
        loginController.prepareAndOpenFrame();
        settingsController.prepareAndOpenFrame();
        mainFrame.setVisible(true);

        TitleBarPanel titleBarPanel = mainFrame.getTitleBarPanel();
        titleBarPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
                mainFrame.shutdownAll();
            }
        });
        titleBarPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point current = mainFrame.getLocation();
                mainFrame.setLocation(current.x + e.getX() - origin.x, current.y + e.getY() - origin.y);
            }
        });

        mainFrame.getWebBrowser().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainFrame.shutdownAll();
            }
        });
    }

}

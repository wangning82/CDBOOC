package com.cdboo.business.ui.player.controller;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.service.MusicService;
import com.cdboo.business.service.UserService;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.player.view.TitleBarPanel;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import com.cdboo.system.spring.PropsConfig;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.*;

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

    @Autowired
    private MusicService musicService;

    @Autowired
    private UserService userService;

    @Autowired
    private PropsConfig propsConfig;

    private Point origin = new Point(); // 初始位置

    @Override
    public void prepareAndOpenFrame() {
        titleBarController.prepareAndOpenFrame();
        loginController.prepareAndOpenFrame();
        settingsController.prepareAndOpenFrame();
        mainFrame.setVisible(false);

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

        // 最后加载页面
        Platform.runLater(() -> {
            if(Config.getConfigInstance().isAutoLogin() && !StringUtils.isEmpty(Config.getConfigInstance().getUserName())){
                mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.index"));

                java.util.List<RestMusic> musicList = (java.util.List<RestMusic>)musicService.findAll();
                File folder = new File(propsConfig.getMusic());
                File[] allmusic = folder.listFiles((File dir, String name) -> name.toLowerCase().endsWith(".mp3"));
                File[] download = folder.listFiles((File dir, String name) -> name.endsWith(".cdboo"));
                if(allmusic.length - download.length != musicList.size()){
                    userService.downloadMusic(musicList);
                }

            }else{
                mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.blank"));
            }
        });



    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

}

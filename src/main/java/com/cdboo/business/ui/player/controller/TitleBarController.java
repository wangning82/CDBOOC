package com.cdboo.business.ui.player.controller;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.JComponentStyle;
import com.cdboo.business.common.JComponentUtils;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.entity.RestTimeStep;
import com.cdboo.business.service.PeriodService;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by houyi on 2016/12/6.
 */
@Controller
public class TitleBarController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private PeriodController periodController;

    @Override
    public void prepareAndOpenFrame() {
        registerAction(mainFrame.getTitleBarPanel().getCloseButton(), (e) -> closeClientsWindow());
        registerAction(mainFrame.getTitleBarPanel().getMinButton(), (e) -> minClientsWindow());
        registerAction(mainFrame.getTitleBarPanel().getMaxButton(), (e) -> maxClientsWindow());
        registerAction(mainFrame.getTitleBarPanel().getSettingsButton(), (e) -> showSettings());
        registerAction(mainFrame.getTitleBarPanel().getLoginButton(), (e) -> showLoginWindow());
        registerAction(mainFrame.getTitleBarPanel().getQueryButton(), (e) -> queryMusic());

        mainFrame.getTitleBarPanel().getPeriodLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.shutdownAll();
                Point p = mainFrame.getTitleBarPanel().getPeriodLabel().getLocationOnScreen();
                mainFrame.getPeriodDialog().setShowPossition(new Point(new Double(p.getX()).intValue(), new Double(p.getY() + 30).intValue()));
                mainFrame.getPeriodDialog().setPeriodList(periodService.findAll());
                mainFrame.getPeriodDialog().showItNow();
                periodController.prepareAndOpenFrame();
            }
        });

    }

    /**
     * 显示配置窗口
     */
    private void showSettings(){
        mainFrame.shutdownAll();
        Point p = mainFrame.getTitleBarPanel().getSettingsButton().getLocationOnScreen();
        mainFrame.getSettingsDialog().setShowPossition(new Point(new Double(p.getX()).intValue(), new Double(p.getY() + 30).intValue()));
        mainFrame.getSettingsDialog().showItNow();
    }

    /**
     * 显示登录窗口
     */
    public void showLoginWindow(){
        Platform.runLater(() -> {
            mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.blur"));
        });
        mainFrame.shutdownAll();
        Point p = mainFrame.getLocationOnScreen();
        double x = p.getX() + mainFrame.getWidth() / 2 - JComponentStyle.LOGIN_WIDTH / 2;
        double y = p.getY() + mainFrame.getHeight() / 2 - JComponentStyle.LOGIN_HEIGHT / 2;
        mainFrame.getLoginDialog().setShowPossition(new Point(new Double(x).intValue(), new Double(y).intValue()));
        mainFrame.getLoginDialog().showItNow();
        mainFrame.getLoginDialog().reset();
    }

    /**
     * 关闭窗口
     */
    private void closeClientsWindow(){
        Config.getConfigInstance().saveUserData();
        System.exit(0);
    }

    /**
     * 最小化
     */
    private void minClientsWindow(){
        mainFrame.shutdownAll();
        mainFrame.setVisible(false);

        if(SystemTray.isSupported()){
            SystemTray systemTray = SystemTray.getSystemTray();
            Image image = mainFrame.getToolkit().getImage(getClass().getResource("/images/logo.png"));

            PopupMenu pop = new PopupMenu();
            MenuItem show = new MenuItem("还原");
            MenuItem exit = new MenuItem("退出");
            pop.add(show);
            pop.add(exit);

            TrayIcon trayIcon = new TrayIcon(image, "悦我音乐", pop);
            trayIcon.setImageAutoSize(true);

            show.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(true);
                    mainFrame.setExtendedState(JFrame.NORMAL);
                    mainFrame.toFront();
                    systemTray.remove(trayIcon);
                }

            });

            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    systemTray.remove(trayIcon);
                    Config.getConfigInstance().saveUserData();
                    System.exit(0);
                }
            });

            trayIcon.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        mainFrame.setVisible(true);
                        mainFrame.setExtendedState(JFrame.NORMAL);
                        mainFrame.toFront();
                        systemTray.remove(trayIcon);
                    }
                }
            });

            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 最大化窗口
     */
    private void maxClientsWindow(){
        mainFrame.shutdownAll();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        if(mainFrame.getWidth() == screenSize.width && mainFrame.getHeight() == screenSize.height){
            mainFrame.setSize(JComponentStyle.MAIN_WIDTH, JComponentStyle.MAIN_HEIGHT);
            JComponentUtils.setCenter(mainFrame);
            mainFrame.getTitleBarPanel().getMaxButton().setToolTipText("最大化");
        }else{
            mainFrame.setSize(screenSize.width, screenSize.height);
            mainFrame.setLocation(0, 0);
            mainFrame.getTitleBarPanel().getMaxButton().setToolTipText("恢复");
        }
    }

    /**
     * 查询音乐
     */
    private void queryMusic(){
        if(Config.getConfigInstance().getHistory().size() > 20){
            Config.getConfigInstance().getHistory().removeLast();
        }
        Config.getConfigInstance().getHistory().addFirst(mainFrame.getTitleBarPanel().getQueryText().getText());
        mainFrame.getTitleBarPanel().getQueryText().setHistory(Config.getConfigInstance().getHistory());
        Platform.runLater(() -> {
            mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.index") + "?keyword=" + mainFrame.getTitleBarPanel().getQueryText().getText());
        });
    }

}

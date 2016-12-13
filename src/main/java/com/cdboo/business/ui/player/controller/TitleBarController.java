package com.cdboo.business.ui.player.controller;

import com.cdboo.business.service.PlayPlanService;
import com.cdboo.business.ui.player.view.LoginDialog;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.player.view.SettingsDialog;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import com.cdboo.business.util.Style;
import com.cdboo.business.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by houyi on 2016/12/6.
 */
@Controller
public class TitleBarController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Autowired
    private PlayPlanService playPlanService;

    @Override
    public void prepareAndOpenFrame() {
        registerAction(mainFrame.getTitleBarPanel().getCloseButton(), (e) -> closeClientsWindow());
        registerAction(mainFrame.getTitleBarPanel().getMinButton(), (e) -> minClientsWindow());
        registerAction(mainFrame.getTitleBarPanel().getMaxButton(), (e) -> maxClientsWindow());
        registerAction(mainFrame.getTitleBarPanel().getSettingsButton(), (e) -> showSettings());
        registerAction(mainFrame.getTitleBarPanel().getLoginButton(), (e) -> showLoginWindow());

        String[] defaultTime = new String[]{};
        for(int i = 0 ; i < playPlanService.findAll().size(); i ++){
            defaultTime[i] = playPlanService.findAll().get(i).getName();
        }
        mainFrame.getTitleBarPanel().getTimeCB().setModel(new DefaultComboBoxModel<String>(defaultTime));
        //mainFrame.getTitleBarPanel().getTimeCB().setSelectedItem(defaultTime[0]);
    }

    /**
     * 显示配置窗口
     */
    private void showSettings(){
        mainFrame.shutdownAll();
        Point p = mainFrame.getTitleBarPanel().getSettingsButton().getLocationOnScreen();
        SettingsDialog settingsDialog = new SettingsDialog(new Point(new Double(p.getX()).intValue(), new Double(p.getY() + 30).intValue()));
        mainFrame.setSettingsDialog(settingsDialog);
        settingsDialog.showItNow();
    }

    /**
     * 显示登录窗口
     */
    private void showLoginWindow(){
        mainFrame.shutdownAll();
        Point p = mainFrame.getLocationOnScreen();
        // TODO 计算窗口位置
        LoginDialog loginDialog = new LoginDialog(new Point(new Double(p.getX()).intValue(), new Double(p.getY()).intValue()));
        mainFrame.setLoginDialog(loginDialog);
        loginDialog.showItNow();
    }

    /**
     * 关闭窗口
     */
    private void closeClientsWindow(){
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

    private void maxClientsWindow(){
        mainFrame.shutdownAll();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        if(mainFrame.getWidth() == screenSize.width && mainFrame.getHeight() == screenSize.height){
            mainFrame.setSize(Style.WIDTH, Style.HEIGHT);
            Utils.setCenter(mainFrame);
            mainFrame.getTitleBarPanel().getMaxButton().setToolTipText("最大化");
        }else{
            mainFrame.setSize(screenSize.width, screenSize.height);
            mainFrame.setLocation(0, 0);
            mainFrame.getTitleBarPanel().getMaxButton().setToolTipText("恢复");
        }
    }

}

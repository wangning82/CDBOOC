package com.cdboo.system;

import com.cdboo.business.ui.player.controller.MainController;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.player.view.SplashWindow;
import com.cdboo.system.spring.PropsConfig;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.utils.Platform;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({PropsConfig.class})
public class Application {

    private static void initUserInterface() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Draw 9-patch");

        try {
            if (Platform.isWindows()) {
                UIManager.put("RootPane.setupButtonVisible", false); // 系统设置关闭
                BeautyEyeLNFHelper.launchBeautyEyeLNF();
            } else
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initUserInterface();
        SplashWindow splashWindow = new SplashWindow();
        SwingUtilities.invokeLater(() -> {
            ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false).run(args);
            MainController mainController = context.getBean(MainController.class);
            mainController.prepareAndOpenFrame();
            MainFrame mainFrame = mainController.getMainFrame();
            splashWindow.dispose();
            mainFrame.setVisible(true);
        });
    }

}

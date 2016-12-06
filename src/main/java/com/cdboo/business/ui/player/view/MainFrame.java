package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.Style;
import com.sun.awt.AWTUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/11/30.
 */
@Component
public class MainFrame extends JFrame {

    private TitleBarPanel titleBarPanel = new TitleBarPanel();

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.add(titleBarPanel, BorderLayout.NORTH);
        mainPane.setBackground(Style.COLOR_RED);
        getContentPane().add(mainPane);

        setSize(Style.WIDTH, Style.HEIGHT);
        setCenter();
    }

    /**
     * 居中显示
     */
    private void setCenter() {
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

    public TitleBarPanel getTitleBarPanel() {
        return titleBarPanel;
    }

    public void setTitleBarPanel(TitleBarPanel titleBarPanel) {
        this.titleBarPanel = titleBarPanel;
    }
}

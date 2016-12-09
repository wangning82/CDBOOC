package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.Style;
import com.cdboo.business.util.Utils;
import com.sun.awt.AWTUtilities;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/11/30.
 */
@Component
public class MainFrame extends JFrame {

    private TitleBarPanel titleBarPanel = new TitleBarPanel();
    private BrowserPanel browserPanel = new BrowserPanel();

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
        mainPane.add(browserPanel, BorderLayout.CENTER);

        mainPane.setBackground(Style.COLOR_DEFAULT);
        getContentPane().add(mainPane);

        setSize(Style.WIDTH, Style.HEIGHT);
        Utils.setCenter(this);

    }

    public TitleBarPanel getTitleBarPanel() {
        return titleBarPanel;
    }

    public void setTitleBarPanel(TitleBarPanel titleBarPanel) {
        this.titleBarPanel = titleBarPanel;
    }

}

package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.Style;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/11/30.
 */
@Component
public class MainFrame extends JFrame {

    private JPanel mainPane = new JPanel(new BorderLayout());

    public MainFrame() {
        setFrameUp();
        initComponents();
    }

    private void setFrameUp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        getContentPane().add(mainPane);
        setSize(Style.WIDTH, Style.HEIGHT);
        setCenter();
    }

    private void setCenter() {
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

    private void initComponents() {

    }
}

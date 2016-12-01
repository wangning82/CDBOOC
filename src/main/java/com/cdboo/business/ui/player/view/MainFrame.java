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

    public MainFrame(){
        setFrameUp();
        initComponents();
        pack();
        setSize(Style.WIDTH, Style.HEIGHT);
    }

    private void setFrameUp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().add(mainPane);
    }

    private void initComponents() {

    }
}

package com.cdboo.business.ui.player.view;

import com.cdboo.business.ui.shared.view.AbstractJPanel;
import com.cdboo.business.util.Style;
import com.cdboo.business.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houyi on 2016/12/2.
 */
public class TitleBarPanel extends AbstractJPanel {
    private JLabel blankLabel;
    private JButton headButton;
    private JLabel nickNameLabel;
    private JButton loginButton;

    private JComboBox timeCB;
    private JLabel timeLabel;
    private JButton settingsButton;

    private JButton minButton;
    private JButton maxButton;
    private JButton closeButton;

    private JSeparator separator1;
    private JSeparator separator2;

    private JPanel headPane;
    private JPanel btnPane;

    public TitleBarPanel() {
        super(new BorderLayout());
    }

    @Override
    protected void setFrameUp() {
        blankLabel = Utils.createLabel("  ");
        headButton = Utils.createButton(new ImageIcon(getClass().getResource("/images/head.jpg")), Style.SIZE_30_30);
        nickNameLabel = Utils.createLabel("匿名用户");
        loginButton = Utils.createButton("登录");

        timeCB = Utils.createComboBox();
        SimpleDateFormat sdf = new SimpleDateFormat("E yyyy/MM/dd");
        timeLabel = Utils.createLabel(sdf.format(new Date()));

        settingsButton = Utils.createButton(new ImageIcon(getClass().getResource("/images/settings.png")), Style.SIZE_30_30);
        minButton = Utils.createButton(new ImageIcon(getClass().getResource("/images/min.png")), Style.SIZE_25_25);
        maxButton = Utils.createButton(new ImageIcon(getClass().getResource("/images/max.png")), Style.SIZE_25_25);
        closeButton = Utils.createButton(new ImageIcon(getClass().getResource("/images/shutdown.png")), Style.SIZE_25_25);

        separator1 = Utils.createSeparator(JSeparator.VERTICAL);
        separator2 = Utils.createSeparator(JSeparator.VERTICAL);

        headPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        headPane.setBackground(Style.COLOR_DEFAULT);

        btnPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPane.setBackground(Style.COLOR_DEFAULT);

        this.setBackground(Style.COLOR_DEFAULT);
    }

    @Override
    protected void initComponents() {
        headPane.add(blankLabel);
        headPane.add(headButton);
        headPane.add(nickNameLabel);
        headPane.add(separator1);
        headPane.add(loginButton);
        this.add(headPane, BorderLayout.WEST);

        btnPane.add(timeCB);
        btnPane.add(timeLabel);
        btnPane.add(settingsButton);

        btnPane.add(separator2);
        btnPane.add(minButton);
        btnPane.add(maxButton);
        btnPane.add(closeButton);
        this.add(btnPane, BorderLayout.EAST);
    }

    public JButton getHeadButton() {
        return headButton;
    }

    public void setHeadButton(JButton headButton) {
        this.headButton = headButton;
    }

    public JLabel getNickNameLabel() {
        return nickNameLabel;
    }

    public void setNickNameLabel(JLabel nickNameLabel) {
        this.nickNameLabel = nickNameLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(JButton settingsButton) {
        this.settingsButton = settingsButton;
    }

    public JButton getMinButton() {
        return minButton;
    }

    public void setMinButton(JButton minButton) {
        this.minButton = minButton;
    }

    public JButton getMaxButton() {
        return maxButton;
    }

    public void setMaxButton(JButton maxButton) {
        this.maxButton = maxButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(JButton closeButton) {
        this.closeButton = closeButton;
    }

    public JPanel getHeadPane() {
        return headPane;
    }

    public void setHeadPane(JPanel headPane) {
        this.headPane = headPane;
    }

    public JPanel getBtnPane() {
        return btnPane;
    }

    public void setBtnPane(JPanel btnPane) {
        this.btnPane = btnPane;
    }

    public JComboBox getTimeCB() {
        return timeCB;
    }

    public void setTimeCB(JComboBox timeCB) {
        this.timeCB = timeCB;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TitleBarPanel());
        frame.setPreferredSize(new Dimension(800, 100));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

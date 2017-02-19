package com.cdboo.business.ui.player.view;

import com.cdboo.business.common.Config;
import com.cdboo.business.ui.shared.view.AbstractJPanel;
import com.cdboo.business.common.JComponentStyle;
import com.cdboo.business.common.JComponentUtils;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houyi on 2016/12/2.
 */
public class TitleBarPanel extends AbstractJPanel {
    private JButton headButton;
    private JLabel nickNameLabel;
    private JButton loginButton;

    //private JComboBox timeCB;
    private JLabel periodLabel;
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
        headButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/head.jpg")), JComponentStyle.SIZE_30_30);
        nickNameLabel = JComponentUtils.createLabel("匿名用户");
        loginButton = JComponentUtils.createButton("登录", new Dimension(50, 30));

        //timeCB = JComponentUtils.createComboBox();
        periodLabel = JComponentUtils.createLabel("选择时段");
        SimpleDateFormat sdf = new SimpleDateFormat("E yyyy/MM/dd");
        timeLabel = JComponentUtils.createLabel(sdf.format(new Date()));

        settingsButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/settings.png")), JComponentStyle.SIZE_30_30);
        minButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/min.png")), JComponentStyle.SIZE_25_25);
        maxButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/max.png")), JComponentStyle.SIZE_25_25);
        closeButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/shutdown.png")), JComponentStyle.SIZE_25_25);

        separator1 = JComponentUtils.createSeparator(JSeparator.VERTICAL);
        separator2 = JComponentUtils.createSeparator(JSeparator.VERTICAL);

        if (Config.getConfigInstance().isAutoLogin() && !StringUtils.isEmpty(Config.getConfigInstance().getUserName())) {
            loadUserInfo();
            loginButton.setVisible(false);
        }
    }

    @Override
    protected void initComponents() {
        headPane = new JPanel();
        headPane.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));
        headPane.setBackground(JComponentStyle.COLOR_DEFAULT);

        btnPane = new JPanel();
        btnPane.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));
        btnPane.setBackground(JComponentStyle.COLOR_DEFAULT);

        this.setBackground(JComponentStyle.COLOR_DEFAULT);

        headPane.add(headButton);
        headPane.add(nickNameLabel);
        headPane.add(Box.createRigidArea(new Dimension(4, 1)));
        headPane.add(separator1);
        headPane.add(loginButton);
        this.add(headPane, BorderLayout.WEST);

        //btnPane.add(timeCB);
        btnPane.add(periodLabel);
        btnPane.add(timeLabel);
        btnPane.add(settingsButton);

        btnPane.add(separator2);
        btnPane.add(minButton);
        //btnPane.add(maxButton);
        btnPane.add(closeButton);
        this.add(btnPane, BorderLayout.EAST);
        this.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 8));
    }

    /**
     * 获取用户信息
     */
    public void loadUserInfo() {
        if (!StringUtils.isEmpty(Config.getConfigInstance().getPhoto()) && new File(Config.getConfigInstance().getPhoto()).exists()) {
            ImageIcon imageIcon = new ImageIcon(Config.getConfigInstance().getPhoto());
            Image smallImage = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
            headButton.setIcon(new ImageIcon(smallImage));
        }else{
            headButton.setIcon(new ImageIcon(getClass().getResource("/images/head.jpg")));
        }
        nickNameLabel.setText(Config.getConfigInstance().getNickName());
    }

    /**
     * 重置用户信息
     */
    public void resetUserInfo(){
        headButton.setIcon(new ImageIcon(getClass().getResource("/images/head.jpg")));
        nickNameLabel.setText("匿名用户");
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

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JLabel getPeriodLabel() {
        return periodLabel;
    }

    public void setPeriodLabel(JLabel periodLabel) {
        this.periodLabel = periodLabel;
    }

    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TitleBarPanel());
        frame.setPreferredSize(new Dimension(800, 100));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    */
}

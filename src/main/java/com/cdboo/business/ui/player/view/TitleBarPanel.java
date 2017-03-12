package com.cdboo.business.ui.player.view;

import com.cdboo.business.common.*;
import com.cdboo.business.ui.shared.view.AbstractJPanel;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houyi on 2016/12/2.
 */
public class TitleBarPanel extends AbstractJPanel {
    private JButton headButton;
    private JLabel nickNameLabel;
    private JButton loginButton;

    private JButton backButton;
    private JButton nextButton;
    private JButton flashButton;
    private JHistoryTextField queryText;
    private JButton queryButton;

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
    private JPanel navigatePane;
    private JPanel queryPane;

    public TitleBarPanel() {
        super(new GridBagLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/background.jpg"));
        g.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
    }

    @Override
    protected void setFrameUp() {
        headButton = new CircleButton();
        headButton.setIcon(new ImageIcon(getClass().getResource("/images/head.png")));
        headButton.setPreferredSize(JComponentStyle.SIZE_30_30);

        nickNameLabel = JComponentUtils.createLabel("匿名用户");
        loginButton = JComponentUtils.createButton("登录", new Dimension(50, 30));

        backButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/back.png")), JComponentStyle.SIZE_30_30);
        nextButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/next.png")), JComponentStyle.SIZE_30_30);
        flashButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/flash.png")), JComponentStyle.SIZE_30_30);

        queryText = new JHistoryTextField();
        queryText.setPreferredSize(new Dimension(160, 24));
        queryText.setHistory(Config.getConfigInstance().getHistory());
        queryButton = JComponentUtils.createButton(new ImageIcon(getClass().getResource("/images/query.png")), JComponentStyle.SIZE_30_30);

        periodLabel = JComponentUtils.createLabel("选择时段");
        SimpleDateFormat sdf = new SimpleDateFormat("E   yyyy/MM/dd");
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
        headPane.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 5));
        headPane.setBackground(null);
        headPane.setOpaque(false);

        headPane.add(headButton);
        headPane.add(Box.createRigidArea(new Dimension(4, 1)));
        headPane.add(nickNameLabel);
        headPane.add(Box.createRigidArea(new Dimension(4, 1)));
        headPane.add(separator1);
        headPane.add(loginButton);
        headPane.add(Box.createRigidArea(new Dimension(10, 1)));

        navigatePane = new JPanel();
        navigatePane.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 5));
        navigatePane.setBackground(null);
        navigatePane.setOpaque(false);
        navigatePane.add(backButton);
        navigatePane.add(nextButton);
        navigatePane.add(flashButton);

        queryPane = new JPanel();
        queryPane.setLayout(new FlowLayout(FlowLayout.LEFT, -3, 5));
        queryPane.setBackground(null);
        queryPane.setOpaque(false);
        queryPane.add(queryText);
        queryPane.add(queryButton);

        btnPane = new JPanel();
        btnPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        btnPane.setBackground(null);
        btnPane.setOpaque(false);

        btnPane.add(periodLabel);
        btnPane.add(Box.createRigidArea(new Dimension(10, 1)));
        btnPane.add(timeLabel);
        btnPane.add(settingsButton);
        btnPane.add(separator2);
        btnPane.add(minButton);
        //btnPane.add(maxButton);
        btnPane.add(closeButton);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 30.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(headPane, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.weightx = 20.0;
        add(navigatePane, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.weightx = 20.0;
        add(queryPane, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.weightx = 30.0;
        add(btnPane, c);

        this.setBorder(BorderFactory.createEmptyBorder(4, 15, 4, 15));
    }

    /**
     * 获取用户信息
     */
    public void loadUserInfo() {
        if (!StringUtils.isEmpty(Config.getConfigInstance().getPhoto()) && new File(Config.getConfigInstance().getPhoto()).exists()) {
            ImageIcon imageIcon = new ImageIcon(Config.getConfigInstance().getPhoto());
            Image smallImage = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_FAST);
            headButton.setIcon(new ImageIcon(smallImage));
        } else {
            headButton.setIcon(new ImageIcon(getClass().getResource("/images/head.png")));
        }
        nickNameLabel.setText(Config.getConfigInstance().getNickName());
    }

    /**
     * 重置用户信息
     */
    public void resetUserInfo() {
        headButton.setIcon(new ImageIcon(getClass().getResource("/images/head.png")));
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

    public JHistoryTextField getQueryText() {
        return queryText;
    }

    public void setQueryText(JHistoryTextField queryText) {
        this.queryText = queryText;
    }

    public JButton getQueryButton() {
        return queryButton;
    }

    public void setQueryButton(JButton queryButton) {
        this.queryButton = queryButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public void setNextButton(JButton nextButton) {
        this.nextButton = nextButton;
    }

    public JButton getFlashButton() {
        return flashButton;
    }

    public void setFlashButton(JButton flashButton) {
        this.flashButton = flashButton;
    }

    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TitleBarPanel());
        frame.setPreferredSize(new Dimension(JComponentStyle.MAIN_WIDTH, 100));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    */
}

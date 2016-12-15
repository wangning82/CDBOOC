package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.Style;
import com.cdboo.business.util.Utils;
import com.sun.awt.AWTUtilities;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/13.
 */
@Component
public class LoginDialog extends JDialog {
    private Point showPossition;
    private JPanel loginPanel;

    private JButton closeButton;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox autoLoginCheckbox;
    private JLabel modPasswordLabel;

    public LoginDialog(){
        initGUI();
    }

    protected void initGUI() {
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        ((JComponent) (this.getContentPane())).setOpaque(false);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        loginPanel = createLoginPanel();
        this.add(loginPanel);
    }

    private JPanel createLoginPanel() {
        loginPanel = new JPanel(new BorderLayout());
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)){
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/login.png"));
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
            }
        };
        logoPanel.setPreferredSize(new Dimension(Style.LOGIN_WIDTH, 78));

        closeButton = Utils.createButton(new ImageIcon(getClass().getResource("/images/shutdown.png")), Style.SIZE_25_25);
        logoPanel.add(closeButton);
        loginPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = Utils.createButton("登 录", Style.FONT_16, new Dimension(132, 38));
        southPanel.add(loginButton);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        loginPanel.add(southPanel, BorderLayout.SOUTH);

        loginPanel.add(getInputPanel(), BorderLayout.CENTER);

        loginPanel.setBackground(Style.COLOR_BG_GRAY);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        return loginPanel;
    }

    private JPanel getInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(14, 60, 0, 60));
        GridBagConstraints c = new GridBagConstraints();

        JLabel label = Utils.createLabel("账 号", Style.FONT_DEFAULT, Style.COLOR_FONT_GRAY, new Dimension(40, 35));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 20.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 12, 0);
        inputPanel.add(label, c);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(100, 30));
        usernameField.setFont(Style.FONT_12);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 80.0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        inputPanel.add(usernameField, c);

        label = Utils.createLabel("密 码", Style.FONT_DEFAULT, Style.COLOR_FONT_GRAY, new Dimension(40, 35));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 20.0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        inputPanel.add(label, c);

        passwordField = new JPasswordField ();
        passwordField.setPreferredSize(new Dimension(100, 30));
        passwordField.setFont(Style.FONT_12);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 80.0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        inputPanel.add(passwordField, c);

        autoLoginCheckbox = Utils.createCheckBox("自动登录", Style.FONT_12, Style.COLOR_FONT_GRAY, JCheckBox.TRAILING);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 40.0;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        inputPanel.add(autoLoginCheckbox, c);

        modPasswordLabel = Utils.createLabel("修改密码", Style.FONT_12, Style.COLOR_FONT_GRAY, new Dimension(40, 35));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 40.0;
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        inputPanel.add(modPasswordLabel, c);
        return inputPanel;
    }

    public LoginDialog showItNow() {
        this.pack();
        if (showPossition == null || (showPossition.x < 0 && showPossition.y < 0))
            this.setLocationRelativeTo(null);
        else
            this.setLocation(new Point(showPossition.x < 0 ? 0 : showPossition.x, showPossition.y < 0 ? 0 : showPossition.y));
        this.setVisible(true);
        return this;
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

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JCheckBox getAutoLoginCheckbox() {
        return autoLoginCheckbox;
    }

    public void setAutoLoginCheckbox(JCheckBox autoLoginCheckbox) {
        this.autoLoginCheckbox = autoLoginCheckbox;
    }

    public JLabel getModPasswordLabel() {
        return modPasswordLabel;
    }

    public void setModPasswordLabel(JLabel modPasswordLabel) {
        this.modPasswordLabel = modPasswordLabel;
    }

    public Point getShowPossition() {
        return showPossition;
    }

    public void setShowPossition(Point showPossition) {
        this.showPossition = showPossition;
    }
}

package com.cdboo.business.ui.player.view;

import com.cdboo.business.ui.shared.view.AbstractJPanel;
import com.cdboo.business.util.Style;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/2.
 */
public class TitleBarPanel extends AbstractJPanel {
    private JButton logoButton;
    private JButton profileButton;
    private JButton minButton;
    private JButton maxButton;
    private JButton closeButton;
    private JSeparator separator;

    private JPanel logoPane;
    private JPanel btnPane;

    public TitleBarPanel() {
        super(new BorderLayout());
    }

    @Override
    protected void setFrameUp() {
        logoButton = new JButton("悦我音乐");
        logoButton.setBorderPainted(false);
        logoButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        logoButton.setForeground(Color.white);

        profileButton = new JButton("个人资料");
        profileButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        profileButton.setForeground(Color.white);

        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBackground(Color.GRAY);
        separator.setPreferredSize(new Dimension(5, 20));

        minButton = new JButton("最小化");
        minButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        minButton.setForeground(Color.white);

        maxButton = new JButton("最大化");
        maxButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        maxButton.setForeground(Color.white);

        closeButton = new JButton("关闭");
        closeButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        closeButton.setForeground(Color.white);

        logoPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPane.setBackground(Style.COLOR_RED);

        btnPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPane.setBackground(Style.COLOR_RED);

        this.setBackground(Style.COLOR_RED);
    }

    @Override
    protected void initComponents() {
        logoPane.add(logoButton);
        this.add(logoPane, BorderLayout.WEST);

        btnPane.add(profileButton);
        btnPane.add(separator);
        btnPane.add(minButton);
        btnPane.add(maxButton);
        btnPane.add(closeButton);
        this.add(btnPane, BorderLayout.EAST);
    }

    public JButton getLogoButton() {
        return logoButton;
    }

    public void setLogoButton(JButton logoButton) {
        this.logoButton = logoButton;
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public void setProfileButton(JButton profileButton) {
        this.profileButton = profileButton;
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

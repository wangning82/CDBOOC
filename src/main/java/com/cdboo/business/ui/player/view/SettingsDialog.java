package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.NPIconFactory;
import com.cdboo.business.util.Style;
import com.cdboo.business.util.Utils;
import com.sun.awt.AWTUtilities;
import org.jb2011.ninepatch4j.NinePatch;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/12 0012.
 */
public class SettingsDialog extends JDialog {
    private NinePatch npBackground = null;
    private Point showPossition = null;
    private JPanel settingsPanel = null;

    private JCheckBox alwaysTop;
    private JCheckBox autoPlay;
    private JLabel restore;
    private JLabel checkUpdate;
    private JLabel logout;
    private JLabel exit;

    public SettingsDialog(Point p) {
        initGUI();
        this.showPossition = p;
    }

    protected void initGUI() {
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        ((JComponent) (this.getContentPane())).setOpaque(false);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        settingsPanel = createSettingsPanel();
        this.add(settingsPanel);
    }

    private JPanel createSettingsPanel() {
        settingsPanel = new JPanel(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS)) {
            @Override
            protected void paintChildren(Graphics g) {
                if (npBackground == null)
                    npBackground = NPIconFactory.getInstance().getTipBackground("shadow_bg_popup.9.png");
                if (npBackground != null)
                    npBackground.draw((Graphics2D) g, 0, 0, this.getWidth(), this.getHeight());
                super.paintChildren(g);
            }
        };
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        alwaysTop = Utils.createCheckBox("窗口总在最前", Style.COLOR_FONT_GRAY, JCheckBox.LEADING);
        autoPlay = Utils.createCheckBox("开机启动播放", Style.COLOR_FONT_GRAY, JCheckBox.LEADING);

        restore = Utils.createLabel(" 恢复频道", Style.FONT_DEFAULT, Style.COLOR_FONT_GRAY);
        checkUpdate = Utils.createLabel(" 检查更新", Style.FONT_DEFAULT, Style.COLOR_FONT_GRAY);
        logout = Utils.createLabel(" 退出登录", Style.FONT_DEFAULT, Style.COLOR_FONT_GRAY);
        exit = Utils.createLabel(" 退出软件", Style.FONT_DEFAULT, Style.COLOR_FONT_GRAY);

        settingsPanel.setOpaque(false);
        settingsPanel.add(alwaysTop);
        settingsPanel.add(autoPlay);
        settingsPanel.add(Box.createVerticalStrut(6));
        settingsPanel.add(restore);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(checkUpdate);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(logout);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(exit);
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(15, 16, 24, 16));
        return settingsPanel;
    }

    public SettingsDialog showItNow() {
        this.pack();
        if (showPossition == null || (showPossition.x < 0 && showPossition.y < 0))
            this.setLocationRelativeTo(null);
        else
            this.setLocation(new Point(showPossition.x < 0 ? 0 : showPossition.x, showPossition.y < 0 ? 0 : showPossition.y));
        this.setVisible(true);
        return this;
    }

    public JCheckBox getAlwaysTop() {
        return alwaysTop;
    }

    public void setAlwaysTop(JCheckBox alwaysTop) {
        this.alwaysTop = alwaysTop;
    }

    public JCheckBox getAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(JCheckBox autoPlay) {
        this.autoPlay = autoPlay;
    }

    public JLabel getRestore() {
        return restore;
    }

    public void setRestore(JLabel restore) {
        this.restore = restore;
    }

    public JLabel getCheckUpdate() {
        return checkUpdate;
    }

    public void setCheckUpdate(JLabel checkUpdate) {
        this.checkUpdate = checkUpdate;
    }

    public JLabel getLogout() {
        return logout;
    }

    public void setLogout(JLabel logout) {
        this.logout = logout;
    }

    public JLabel getExit() {
        return exit;
    }

    public void setExit(JLabel exit) {
        this.exit = exit;
    }
}

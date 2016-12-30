package com.cdboo.business.ui.player.view;

import com.cdboo.business.common.NPIconFactory;
import com.cdboo.business.common.JComponentStyle;
import com.cdboo.business.common.JComponentUtils;
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

    public SettingsDialog() {
        initGUI();
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
        alwaysTop = JComponentUtils.createCheckBox("窗口总在最前", JComponentStyle.COLOR_FONT_GRAY, JCheckBox.LEADING);
        autoPlay = JComponentUtils.createCheckBox("开机启动播放", JComponentStyle.COLOR_FONT_GRAY, JCheckBox.LEADING);

        restore = JComponentUtils.createLabel(" 恢复频道", JComponentStyle.FONT_DEFAULT, JComponentStyle.COLOR_FONT_GRAY, null);
        checkUpdate = JComponentUtils.createLabel(" 检查更新", JComponentStyle.FONT_DEFAULT, JComponentStyle.COLOR_FONT_GRAY, null);
        logout = JComponentUtils.createLabel(" 退出登录", JComponentStyle.FONT_DEFAULT, JComponentStyle.COLOR_FONT_GRAY, null);
        exit = JComponentUtils.createLabel(" 退出软件", JComponentStyle.FONT_DEFAULT, JComponentStyle.COLOR_FONT_GRAY, null);

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

    public Point getShowPossition() {
        return showPossition;
    }

    public void setShowPossition(Point showPossition) {
        this.showPossition = showPossition;
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

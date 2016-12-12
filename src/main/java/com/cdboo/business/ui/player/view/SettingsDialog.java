package com.cdboo.business.ui.player.view;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/12 0012.
 */
public class SettingsDialog extends JDialog {
    private Point showPossition = null;
    private SettingsPanel toastPane = null;

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

        // init main layout
        toastPane = new SettingsPanel();
        this.add(toastPane);
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

    public static SettingsDialog show(Point p) {
        return new SettingsDialog(p).showItNow();
    }

}

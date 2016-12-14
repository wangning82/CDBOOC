package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.NPIconFactory;
import com.cdboo.business.util.Style;
import com.sun.awt.AWTUtilities;
import org.jb2011.ninepatch4j.NinePatch;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/13.
 */
public class LoginDialog extends JDialog {
    private NinePatch npBackground = null;
    private Point showPossition = null;
    private JPanel loginPanel = null;

    public LoginDialog(Point p) {
        initGUI();
        this.showPossition = p;
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
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(Style.COLOR_BG_GRAY);
        ImageIcon icon = new ImageIcon("/images/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
        JLabel logoImage = new JLabel();
        logoImage.setHorizontalAlignment(0);
        logoImage.setIcon(icon);
        logoPanel.add(logoImage);
        logoPanel.setSize(420, 78);
        loginPanel.add(logoPanel, BorderLayout.NORTH);

        loginPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return loginPanel;
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

}

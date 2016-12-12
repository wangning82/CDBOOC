package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.NPIconFactory;
import org.jb2011.ninepatch4j.NinePatch;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/12 0012.
 */
public class SettingsPanel extends JPanel {
    private NinePatch npBackground = null;
    private JComponent content = null;

    public SettingsPanel() {
        super(new BorderLayout());
        initGUI();
    }

    /**
     * Override to impl ninepatch image background.
     */
    @Override
    public void paintChildren(Graphics g) {
        if (npBackground == null)
            // load the nine patch .PNG
            npBackground = NPIconFactory.getInstance().getTipBackground("shadow_bg_popup.9.png");
        if (npBackground != null)
            // paint background with ninepath
            npBackground.draw((Graphics2D) g, 0, 0, this.getWidth(), this.getHeight());
        super.paintChildren(g);
    }

    protected void initGUI() {
        this.setOpaque(false);
        content = createContent();
        this.add(content, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(19, 20, 28, 20));
    }

    /**
     * Subclass may be override to implement itself logic.
     *
     * @return
     */
    protected JComponent createContent() {
        JLabel lb = new JLabel("");
        lb.setForeground(new Color(230, 230, 230));
        return lb;
    }
}

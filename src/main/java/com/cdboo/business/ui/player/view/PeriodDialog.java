package com.cdboo.business.ui.player.view;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.JComponentStyle;
import com.cdboo.business.common.JComponentUtils;
import com.cdboo.business.common.NPIconFactory;
import com.cdboo.business.entity.RestTimeStep;
import com.sun.awt.AWTUtilities;
import org.jb2011.ninepatch4j.NinePatch;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyi on 2016/12/12 0012.
 */
public class PeriodDialog extends JDialog {
    private Point showPossition = null;
    private JPanel periodPanel = null;

    private java.util.List<RestTimeStep> periodList = new ArrayList<RestTimeStep>();
    private JCheckBox[] periodBoxes;

    public PeriodDialog() {

    }

    protected void initGUI() {
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        ((JComponent) (this.getContentPane())).setOpaque(false);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        periodPanel = createPeriodPanel();
        this.add(periodPanel);
    }

    private JPanel createPeriodPanel() {
        periodPanel = new JPanel(new BoxLayout(periodPanel, BoxLayout.Y_AXIS)) {
            @Override
            protected void paintChildren(Graphics g) {
                NinePatch npBackground = NPIconFactory.getInstance().getTipBackground("shadow_bg_popup.9.png");
                npBackground.draw((Graphics2D) g, 0, 0, this.getWidth(), this.getHeight());
                super.paintChildren(g);
            }
        };
        periodPanel.setLayout(new BoxLayout(periodPanel, BoxLayout.Y_AXIS));
        periodPanel.setOpaque(false);

        periodBoxes = new JCheckBox[periodList.size()];
        for (int i = 0; i < periodList.size(); i++) {
            periodBoxes[i] = JComponentUtils.createCheckBox(periodList.get(i).getTimestepName(), JComponentStyle.COLOR_FONT_GRAY, JCheckBox.TRAILING);
            if(Config.getConfigInstance().getPeriodList().contains(periodList.get(i).getTimestepName())){
                periodBoxes[i].setSelected(true);
            }
            periodPanel.add(periodBoxes[i]);
            periodPanel.add(Box.createVerticalStrut(6));
        }

        periodPanel.setBorder(BorderFactory.createEmptyBorder(15, 16, 24, 16));
        return periodPanel;
    }

    public PeriodDialog showItNow() {
        initGUI();
        this.pack();
        if (showPossition == null || (showPossition.x < 0 && showPossition.y < 0))
            this.setLocationRelativeTo(null);
        else
            this.setLocation(new Point(showPossition.x < 0 ? 0 : showPossition.x, showPossition.y < 0 ? 0 : showPossition.y));
        this.setVisible(true);
        this.repaint();
        return this;
    }

    public Point getShowPossition() {
        return showPossition;
    }

    public void setShowPossition(Point showPossition) {
        this.showPossition = showPossition;
    }

    public java.util.List<RestTimeStep> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<RestTimeStep> periodList) {
        this.periodList = periodList;
    }

    public JCheckBox[] getPeriodBoxes() {
        return periodBoxes;
    }

    public void setPeriodBoxes(JCheckBox[] periodBoxes) {
        this.periodBoxes = periodBoxes;
    }
}

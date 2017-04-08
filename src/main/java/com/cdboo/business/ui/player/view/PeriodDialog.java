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
    private JLabel[] periodLabels;

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
        periodPanel.setLayout(new GridBagLayout());
        periodPanel.setOpaque(false);

        periodLabels = new JLabel[periodList.size()];
        if(periodList.size() <= 4){
            for (int i = 0; i < periodList.size(); i++) {
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 100.0;
                c.gridx = 0;
                c.gridy = i;
                c.gridwidth = 1;
                periodLabels[i] = JComponentUtils.createLabel(periodList.get(i).getTimestepName(), JComponentStyle.FONT_DEFAULT, Color.BLACK, null);
                periodPanel.add(periodLabels[i], c);
            }
        }else{
            for (int i = 0; i < periodList.size(); i++) {
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.NONE;
                c.weightx = 40.0;
                c.gridwidth = 1;
                c.gridx = i % 2;
                c.gridy = i / 2;
                c.insets = new Insets(4, 4, 4, 4);
                periodLabels[i] = JComponentUtils.createLabel(periodList.get(i).getTimestepName(), JComponentStyle.FONT_DEFAULT, Color.BLACK, null);
                periodPanel.add(periodLabels[i], c);
            }
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

    public JLabel[] getPeriodLabels() {
        return periodLabels;
    }

    public void setPeriodLabels(JLabel[] periodLabels) {
        this.periodLabels = periodLabels;
    }
}

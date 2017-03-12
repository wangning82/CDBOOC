package com.cdboo.business.ui.player.controller;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.JComponentStyle;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by houyi on 2016/12/15 0015.
 */
@Controller
public class PeriodController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Override
    public void prepareAndOpenFrame() {
        if (mainFrame.getPeriodDialog().getPeriodLabels() != null) {
            for (JLabel periodLabel : mainFrame.getPeriodDialog().getPeriodLabels()) {
                periodLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        loadData(periodLabel, e);
                    }
                });
            }
        }
    }

    private void loadData(JLabel label, MouseEvent e) {
        if (label.getForeground() == Color.BLACK) {
            if (!Config.getConfigInstance().getPeriodList().contains(label.getText())) {
                Config.getConfigInstance().getPeriodList().add(label.getText());
            }
            label.setForeground(JComponentStyle.COLOR_DEFAULT);
        } else {
            Config.getConfigInstance().getPeriodList().remove(label.getText());
            label.setForeground(Color.BLACK);
        }
        Platform.runLater(() -> {
            mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.index"));
        });
    }
}

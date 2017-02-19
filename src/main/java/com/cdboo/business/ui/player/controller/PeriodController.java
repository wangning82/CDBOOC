package com.cdboo.business.ui.player.controller;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * Created by houyi on 2016/12/15 0015.
 */
@Controller
public class PeriodController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Override
    public void prepareAndOpenFrame() {
        if(mainFrame.getPeriodDialog().getPeriodBoxes() != null){
            for(JCheckBox periodBox : mainFrame.getPeriodDialog().getPeriodBoxes()){
                registerItem(periodBox, (ItemEvent e) -> loadData(e));
            }
        }
    }

    private void loadData(ItemEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getItem();
        if (checkBox.isSelected()) {
            if(!Config.getConfigInstance().getPeriodList().contains(checkBox.getText())){
                Config.getConfigInstance().getPeriodList().add(checkBox.getText());
            }
        } else {
            Config.getConfigInstance().getPeriodList().remove(checkBox.getText());
        }
        Platform.runLater(() -> {
            mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.index"));
        });
    }
}

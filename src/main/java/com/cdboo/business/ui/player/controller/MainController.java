package com.cdboo.business.ui.player.controller;

import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by houyi on 2016/11/30.
 */
@Controller
public class MainController extends AbstractFrameController {

    @Autowired
    private MainFrame mainFrame;

    @Override
    public void prepareAndOpenFrame() {

        mainFrame.setVisible(true);
    }
}

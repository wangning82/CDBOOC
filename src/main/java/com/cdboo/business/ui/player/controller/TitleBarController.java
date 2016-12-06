package com.cdboo.business.ui.player.controller;

import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by houyi on 2016/12/6.
 */
@Controller
public class TitleBarController extends AbstractFrameController {
    @Autowired
    private MainFrame mainFrame;

    @Override
    public void prepareAndOpenFrame() {
        registerAction(mainFrame.getTitleBarPanel().getCloseButton(), (e) -> closeClientsWindow());
    }

    private void closeClientsWindow(){
        System.exit(0);
    }
}

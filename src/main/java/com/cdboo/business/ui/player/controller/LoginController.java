package com.cdboo.business.ui.player.controller;

import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by houyi on 2016/12/15 0015.
 */
@Controller
public class LoginController extends AbstractFrameController {

    @Autowired
    private MainFrame mainFrame;

    @Override
    public void prepareAndOpenFrame() {
        registerAction(mainFrame.getLoginDialog().getCloseButton(), (e) -> closeLoginWindow());
    }

    private void closeLoginWindow(){
        mainFrame.getLoginDialog().dispose();
    }
}

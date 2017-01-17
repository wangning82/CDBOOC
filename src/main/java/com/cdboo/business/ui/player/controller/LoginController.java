package com.cdboo.business.ui.player.controller;

import com.cdboo.business.common.Constants;
import com.cdboo.business.common.YamlUtils;
import com.cdboo.business.service.UserService;
import com.cdboo.business.ui.player.view.LoginDialog;
import com.cdboo.business.ui.player.view.MainFrame;
import com.cdboo.business.ui.shared.controller.AbstractFrameController;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by houyi on 2016/12/15 0015.
 */
@Controller
public class LoginController extends AbstractFrameController {

    @Autowired
    private MainFrame mainFrame;

    @Autowired
    private UserService userService;

    @Override
    public void prepareAndOpenFrame() {
        registerAction(mainFrame.getLoginDialog().getCloseButton(), (e) -> closeLoginWindow());
        registerAction(mainFrame.getLoginDialog().getLoginButton(), (e) -> loginAction());

    }

    private void closeLoginWindow() {
        mainFrame.getLoginDialog().dispose();
    }

    private void loginAction() {
        LoginDialog dialog = mainFrame.getLoginDialog();
        String userName = dialog.getUsernameField().getText();
        try{
            String result = userService.checkUser(userName, String.valueOf(dialog.getPasswordField().getPassword()));
            if (Constants.USER_NOT_EXIST.equals(result)) {
                dialog.getMessage().setText("该用户不存在!");
            } else if (Constants.USER_PASSWORD_NOT_CORRECT.equals(result)) {
                dialog.getMessage().setText("用户密码不正确!");
            } else if (Constants.USER_PASSWORD_CORRECT.equals(result)) {
                dialog.reset();
                dialog.dispose();
                userService.saveUserData(userService.getUserData(userName));
                Platform.runLater(
                        () -> {
                            mainFrame.getView().getEngine().load(YamlUtils.getValue("url.cdboo.client.ip") + YamlUtils.getValue("url.cdboo.client.index"));
                        }
                );
            }
        }catch (Exception ex){
            dialog.getMessage().setText("网络连接失败，请联系客服人员！");
        }
    }
}

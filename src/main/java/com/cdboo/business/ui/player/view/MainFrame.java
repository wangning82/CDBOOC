package com.cdboo.business.ui.player.view;

import com.cdboo.business.util.Style;
import com.cdboo.business.util.Utils;
import com.sun.awt.AWTUtilities;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/11/30.
 */
@Component
public class MainFrame extends JFrame {

    private TitleBarPanel titleBarPanel = new TitleBarPanel();
    private JFXPanel webBrowser = new JFXPanel();
    private WebView view;
    private SettingsDialog settingsDialog;
    private LoginDialog loginDialog;

    public MainFrame() {
        initComponents();

        Platform.runLater(() -> {
            Group root = new Group();
            Scene scene = new Scene(root);
            webBrowser.setScene(scene);
            view = new WebView();
            view.setPrefSize(new Integer(Style.MAIN_WIDTH).doubleValue(), new Integer(Style.MAIN_HEIGHT - 30).doubleValue());
            view.getEngine().load("http://www.baidu.com");
            root.getChildren().add(view);
        });

    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        ((JComponent) (getContentPane())).setOpaque(false);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.add(titleBarPanel, BorderLayout.NORTH);
        mainPane.add(webBrowser, BorderLayout.CENTER);

        mainPane.setBackground(Style.COLOR_DEFAULT);
        mainPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        getContentPane().add(mainPane);

        setSize(Style.MAIN_WIDTH, Style.MAIN_HEIGHT);
        Utils.setCenter(this);

    }

    /**
     * 所有关闭操作
     */
    public void shutdownAll(){
        if(settingsDialog != null){
            settingsDialog.dispose();
        }
        if(loginDialog != null){
            loginDialog.dispose();
        }
    }

    public TitleBarPanel getTitleBarPanel() {
        return titleBarPanel;
    }

    public void setTitleBarPanel(TitleBarPanel titleBarPanel) {
        this.titleBarPanel = titleBarPanel;
    }

    public JFXPanel getWebBrowser() {
        return webBrowser;
    }

    public void setWebBrowser(JFXPanel webBrowser) {
        this.webBrowser = webBrowser;
    }

    public WebView getView() {
        return view;
    }

    public void setView(WebView view) {
        this.view = view;
    }

    public SettingsDialog getSettingsDialog() {
        return settingsDialog;
    }

    public void setSettingsDialog(SettingsDialog settingsDialog) {
        this.settingsDialog = settingsDialog;
    }

    public LoginDialog getLoginDialog() {
        return loginDialog;
    }

    public void setLoginDialog(LoginDialog loginDialog) {
        this.loginDialog = loginDialog;
    }
}

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


    public MainFrame() {
        initComponents();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Group root = new Group();
                Scene scene = new Scene(root);
                webBrowser.setScene(scene);
                view = new WebView();
                view.setPrefSize(new Integer(Style.WIDTH).doubleValue(), new Integer(Style.HEIGHT - 30).doubleValue());
                view.getEngine().load("http://www.baidu.com");
                root.getChildren().add(view);
            }
        });
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.add(titleBarPanel, BorderLayout.NORTH);
        mainPane.add(webBrowser, BorderLayout.CENTER);

        mainPane.setBackground(Style.COLOR_DEFAULT);
        getContentPane().add(mainPane);

        setSize(Style.WIDTH, Style.HEIGHT);
        Utils.setCenter(this);

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
}

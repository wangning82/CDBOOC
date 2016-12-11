package com.cdboo.business.ui.player.view;


import com.cdboo.business.util.DisplayThread;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/8.
 */
public class BrowserPanel extends JPanel {

    private DisplayThread displayThread;
    private Canvas canvas;

    public BrowserPanel() {
        displayThread = new DisplayThread();
        displayThread.start();
        canvas = new Canvas();
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    public void addNotify() {
        super.addNotify();
        Display dis = displayThread.getDisplay();
        dis.syncExec(new Runnable() {
            public void run() {
                Shell shell = SWT_AWT.new_Shell(displayThread.getDisplay(),
                        canvas);
                shell.setLayout(new FillLayout());
                final Browser browser = new Browser(shell, SWT.NONE);
                browser.setLayoutData(BorderLayout.CENTER);
                browser.setUrl("https://www.baidu.com");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BrowserPanel());
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

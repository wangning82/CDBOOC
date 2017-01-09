package com.cdboo.business.ui.player.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2017/1/9 0009.
 */
public class SplashWindow extends JWindow{
    private JProgressBar progress; // 进度条

    public SplashWindow() {
        Container container = getContentPane(); // 得到容器
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // 设置光标
        container.add(new JLabel(new ImageIcon(getClass().getResource("/images/splash.png"))), BorderLayout.CENTER); // 增加图片
        progress = new JProgressBar(1, 100); // 实例化进度条
        progress.setStringPainted(true); // 描绘文字
        progress.setString("加载程序中,请稍候......"); // 设置显示文字
        progress.setBackground(Color.white); // 设置背景色
        container.add(progress, BorderLayout.SOUTH); // 增加进度条到容器上

        Dimension screen = getToolkit().getScreenSize(); // 得到屏幕尺寸
        pack(); // 窗口适应组件尺寸
        setLocation((screen.width - getSize().width) / 2,
                (screen.height - getSize().height) / 2); // 设置窗口位置
        toFront(); // 窗口前端显示
        setVisible(true); // 显示窗口
    }

    public JProgressBar getProgress() {
        return progress;
    }

    public void setProgress(JProgressBar progress) {
        this.progress = progress;
    }
}

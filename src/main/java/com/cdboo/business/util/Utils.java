package com.cdboo.business.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/8.
 */
public class Utils {

    /**
     * 居中显示
     * @param frame
     */
    public static void setCenter(JFrame frame) {
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

    /**
     * 新建按钮(带文字和图片)
     * @param text
     * @param imageIcon
     * @param dimension
     * @return
     */
    public static JButton createButton(String text, ImageIcon imageIcon, Dimension dimension){
        JButton button = new JButton(text, imageIcon);
        button.setFont(Style.FONT_DEFAULT);
        button.setForeground(Color.white);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setRolloverEnabled(true);
        button.setPreferredSize(dimension);
        return button;
    }

    /**
     * 新建按钮(带文字和图片)
     * @param text
     * @param imageIcon
     * @return
     */
    public static JButton createButton(String text, ImageIcon imageIcon){
        return createButton(text, imageIcon, null);
    }

    /**
     * 新建按钮（只图片）
     * @param imageIcon
     * @param dimension
     * @return
     */
    public static JButton createButton(ImageIcon imageIcon, Dimension dimension){
        return createButton(null, imageIcon, dimension);
    }

    /**
     * 新建按钮（只文字）
     * @param text
     * @param dimension
     * @return
     */
    public static JButton createButton(String text, Dimension dimension){
        return createButton(text, null, dimension);
    }

    /**
     * 新建按钮（只图片）
     * @param imageIcon
     * @return
     */
    public static JButton createButton(ImageIcon imageIcon){
        return createButton(null, imageIcon, null);
    }

    /**
     * 新建按钮（只文字）
     * @param text
     * @return
     */
    public static JButton createButton(String text){
        return createButton(text, null, null);
    }

    /**
     * 创建文字标签
     * @param text
     * @param font
     * @param color
     * @return
     */
    public static JLabel createLabel(String text, Font font, Color color){
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    /**
     * 创建文字标签
     * @param text
     * @return
     */
    public static JLabel createLabel(String text){
        return createLabel(text, Style.FONT_DEFAULT, Color.white);
    }

    /**
     * 创建分隔符
     * @param direction
     * @return
     */
    public static JSeparator createSeparator(int direction){
        JSeparator separator = new JSeparator(direction);
        separator.setBackground(Color.white);
        separator.setPreferredSize(new Dimension(5, 25));
        separator.setAlignmentX(JSeparator.CENTER);
        return separator;
    }

    /**
     * 创建单选框
     * @return
     */
    public static JComboBox createComboBox(){
        JComboBox comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(100, 28));
        comboBox.setFont(Style.FONT_12);
        comboBox.setForeground(Color.white);
        comboBox.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        comboBox.setMaximumRowCount(6); // 超过6个选项显示滚动条，默认是8
        return comboBox;
    }

}

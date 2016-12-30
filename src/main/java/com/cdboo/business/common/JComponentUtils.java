package com.cdboo.business.common;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houyi on 2016/12/8.
 */
public class JComponentUtils {

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
    public static JButton createButton(String text, Font font, ImageIcon imageIcon, Color fontColor, Dimension dimension){
        JButton button = new JButton(text, imageIcon);
        button.setFont(font);
        button.setForeground(fontColor);
        button.setContentAreaFilled(false); //设置按钮透明
        button.setRolloverEnabled(true);
        button.setPreferredSize(dimension);
        return button;
    }

    /**
     * 新建按钮（只图片）
     * @param imageIcon
     * @param dimension
     * @return
     */
    public static JButton createButton(ImageIcon imageIcon, Dimension dimension){
        return createButton(null, null, imageIcon, Color.white, dimension);
    }

    /**
     * 新建按钮（只文字）
     * @param text
     * @param dimension
     * @return
     */
    public static JButton createButton(String text, Dimension dimension){
        return createButton(text, JComponentStyle.FONT_DEFAULT, null, Color.white, dimension);
    }

    /**
     * 新建按钮
     * @param text
     * @param font
     * @param dimension
     * @return
     */
    public static JButton createButton(String text, Font font, Dimension dimension){
        JButton button = createButton(text, font, null, Color.white, dimension);
        button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.grey));
        button.setContentAreaFilled(true); //设置按钮不透明
        return button;
    }

    /**
     * 创建文字标签
     * @param text
     * @param font
     * @param color
     * @return
     */
    public static JLabel createLabel(String text, Font font, Color color, Dimension dimension){
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setPreferredSize(dimension);
        return label;
    }

    /**
     * 创建文字标签
     * @param text
     * @return
     */
    public static JLabel createLabel(String text){
        return createLabel(text, JComponentStyle.FONT_DEFAULT, Color.white, null);
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
     * 创建下拉框
     * @return
     */
    public static JComboBox createComboBox(){
        JComboBox comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(100, 28));
        comboBox.setFont(JComponentStyle.FONT_12);
        comboBox.setForeground(Color.white);
        comboBox.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        comboBox.setMaximumRowCount(6); // 超过6个选项显示滚动条，默认是8
        return comboBox;
    }

    /**
     * 创建复选框
     * @param text
     * @param color
     * @param textPosition
     * @return
     */
    public static JCheckBox createCheckBox(String text, Color color, int textPosition){
        return createCheckBox(text, JComponentStyle.FONT_DEFAULT, color, textPosition);
    }

    /**
     * 创建复选框
     * @param text
     * @param font
     * @param color
     * @param textPosition
     * @return
     */
    public static JCheckBox createCheckBox(String text, Font font, Color color, int textPosition){
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(font);
        checkBox.setForeground(color);
        checkBox.setHorizontalTextPosition(textPosition);
        return checkBox;
    }

}

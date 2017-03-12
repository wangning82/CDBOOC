package com.cdboo.business.common;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by houyi on 2017/3/11 0011.
 */
public class CircleButton extends JButton {
    // shape对象用于保存按钮的形状，有助于侦听点击按钮事件
    private Shape shape;

    public CircleButton(){
        this(null);
    }

    public CircleButton(String label) {
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    // 画圆的按钮的背景和标签
    protected void paintComponent(Graphics g) {
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }

    public boolean contains(int x, int y) {
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {
            // 构造一个椭圆形对象
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        // 判断鼠标的x、y坐标是否落在按钮形状内。
        return shape.contains(x, y);
    }

    /*
    public static void main(String[] args) {
        JButton button = new CircleButton();
        button.setBackground(Color.green);

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.yellow);
        frame.getContentPane().add(button);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(150, 150);
        frame.setVisible(true);
    }
    */

}

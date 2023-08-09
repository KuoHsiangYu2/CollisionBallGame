package com.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

// 球拍.
public class Racquet extends JPanel {

    private static final long serialVersionUID = 1L;
    int x = 0;
    private static final int y = 570;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 30;

    private CollisionBallGame rootWindow = null;

    public Racquet(CollisionBallGame w) {
        this.rootWindow = w;
    }

    public int getTopY() {
        // 返回球拍所在的水平線.
        return y;
    }

    public void moveRacquet(int xa) {
        if ((x + xa) < (this.rootWindow.getWidth() - Racquet.WIDTH) && (this.x + xa) > 0) {
            // 移動座標 小於右邊邊界 且 大於左邊邊界.
            this.x = this.x + xa;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0, 160, 255));
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

}

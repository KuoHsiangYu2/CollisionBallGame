package com.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

// 球.
public class Ball extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int ballsize = 60;
    int x = 0; // 球預設位置.
    int y = 0;
    int incx = 1; // 小球位置要移動的方向.
    int incy = 1;
    private CollisionBallGame rootWindow = null;

    public Ball(CollisionBallGame w) {
        this.rootWindow = w;
    }

    // 這個方法就是不斷更新小球的位置.
    void moveBall() {
        if ((x + incx) > rootWindow.getWidth() - ballsize) {
            // 如果小球移動後的位置超出視窗範圍的話,移動方向就是-1; 再扣掉球的大小.
            incx = -1;
        }
        if ((x + incx) < 0) {
            incx = 1;
        }
        if ((y + incy) > (rootWindow.getHeight() - ballsize)) {
            rootWindow.gameOver();
        }
        if ((y + incy) < 0) {
            incy = 1;
        }

        x = x + incx;
        y = y + incy;
        if (collision()) {
            // 改變y軸的移動方向.
            incy = -1;

            // 這個是矯正球的位置,為了防止碰撞導致的舊球拍和小球重疊，若重疊的話，計分會瘋狂增加.
            y = (rootWindow.racquet.getTopY() - ballsize);
            CollisionBallGame.score++;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillOval(x, y, ballsize, ballsize);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ballsize, ballsize);
    }

    // 判斷是否碰撞.
    private boolean collision() {
        Rectangle ballBounds = this.getBounds();
        Rectangle racquetBounds = this.rootWindow.racquet.getBounds();
        return racquetBounds.intersects(ballBounds);
    }
}

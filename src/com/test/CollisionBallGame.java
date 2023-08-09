package com.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

// 主視窗.
public class CollisionBallGame extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    public static int score = 0;
    private static Graphics baseGraphics = null;
    public Ball ball = null;
    public Racquet racquet = null;
    int xa = 0; // 球拍 移動單位.
    static String propertiesPath = "";
    static Properties properties = new Properties();

    static {
        Path rootPath = Paths.get(".");
        String rootPathStr = rootPath.toAbsolutePath().normalize().toString();
        propertiesPath = rootPathStr + "\\ball_game.properties";
        System.out.printf("propertiesPath=[%s]%n", propertiesPath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(propertiesPath));
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public CollisionBallGame() {
        this.setTitle("不讓球掉落-遊戲");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 讓視窗顯示於中間.
        this.addKeyListener(this); // 增加鍵盤監聽器.
        ball = new Ball(this);
        racquet = new Racquet(this);
    }

    public static void main(String[] args) {
        System.setErr(System.out);
        System.out.printf("properties rate=[%s]%n", properties.get("rate").toString());
        int rate = Integer.valueOf(properties.get("rate").toString());

        CollisionBallGame ballGame = new CollisionBallGame();
        ballGame.play();
        CollisionBallGame.setBaseGraphics(ballGame.getGraphics());

        Timer timer = new Timer(rate, (ActionEvent e) -> {
            ballGame.move();
            ballGame.update(CollisionBallGame.getBaseGraphics());
        });
        timer.start();

        System.out.println("finish");
    }

    public static Graphics getBaseGraphics() {
        return baseGraphics;
    }

    public static void setBaseGraphics(Graphics baseGraphics) {
        CollisionBallGame.baseGraphics = baseGraphics;
    }

    public void play() {
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.setFont(new Font("Verdana", Font.BOLD, 50));
        g.drawString(String.valueOf(score), 20, 120);
        ball.paint(g);
        racquet.paint(g);
    }

    private void move() {
        ball.moveBall();
        racquet.moveRacquet(xa);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 按下鍵盤與放開鍵盤之間的動作.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 按下鍵盤的動作.
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // 往左移動.
            xa = -2;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // 往右移動.
            xa = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 放開鍵盤的動作.
        xa = 0;
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}

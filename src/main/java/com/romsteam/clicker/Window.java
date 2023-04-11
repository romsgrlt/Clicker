package com.romsteam.clicker;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;


public class Window {
    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics g;

    public Window(GameContainer gc){
        image = new BufferedImage(gc.getWidth(),gc.getHeight(), BufferedImage.TYPE_INT_RGB);

        Dimension size = new Dimension((int) (gc.getWidth()*gc.getScale()), (int) (gc.getHeight()*gc.getScale()));
        canvas = new Canvas();
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);

        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas,BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        g = bufferStrategy.getDrawGraphics();
    }

    public void update(){
        g.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight(),null);
        bufferStrategy.show();
    }
}

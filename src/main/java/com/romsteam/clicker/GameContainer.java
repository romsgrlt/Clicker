package com.romsteam.clicker;

import lombok.Getter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@Getter
public class GameContainer implements Runnable {

    //Window Settings
    private int width = 320, height = 240;
    private float scale = 3.25f;
    private String title = "clickerV1.0";


    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;

    private boolean running = false;
    private final int FPS = 60;
    private final double UPDATE_CAP = 1.0/FPS;


    public void start(){
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        thread = new Thread(this);
        thread.run();
    }
    public void stop(){

    }
    public void run(){

        running = true;
        boolean render = false;

        double firstTime = 0;
        double lastTime = System.nanoTime()/1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while(running){
            render = false;

            firstTime = System.nanoTime()/1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime+=passedTime;

            while(unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;
                render = true;

                //TODO: update game

                intputControls();

                input.update();

                if(frameTime >= 1.0){
                    frameTime=0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: "+fps);
                }
            }
            if(render){
                renderer.clear();
                ++frames;
                //TODO: render game
                window.update();
            }else{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void intputControls() {
        //System.out.println("mouseX:"+input.getMouseX()+"\tmouseY:"+input.getMouseY());

        if(input.isKeyDown(KeyEvent.VK_Z))
            System.out.println("FORWARD");
        if(input.isKeyDown(KeyEvent.VK_Q))
            System.out.println("LEFT");
        if(input.isKeyDown(KeyEvent.VK_S))
            System.out.println("BACKWARD");
        if(input.isKeyDown(KeyEvent.VK_D))
            System.out.println("RIGHT");

        if(input.isButtonDown(MouseEvent.BUTTON1))
            System.out.println("LEFT_CLICK");
        if(input.isButtonDown(MouseEvent.BUTTON3))
            System.out.println("RIGHT_CLICK");
        if(input.isButtonDown(MouseEvent.BUTTON2))
            System.out.println("WHEEL_CLICK");

        if(input.getScroll()!=0)
            System.out.println(input.getScroll());
    }

    public void dispose(){

    }

    public static void main(String[] args){
        new GameContainer().start();
    }
}

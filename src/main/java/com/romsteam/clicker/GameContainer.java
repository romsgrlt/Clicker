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
    private AbstractGame game;

    private boolean running = false;
    private final int FPS = 60;
    private final double UPDATE_CAP = 1.0/FPS;

    public GameContainer(AbstractGame game){
        this.game = game;
    }

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
                game.update(this, (float) UPDATE_CAP);


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
                game.render(this,renderer);
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

    public void dispose(){

    }

}

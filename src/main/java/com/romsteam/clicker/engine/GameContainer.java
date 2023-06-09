package com.romsteam.clicker.engine;

import com.romsteam.clicker.engine.audio.SoundClip;
import com.romsteam.clicker.engine.gfx.Font;
import lombok.Getter;

@Getter
public class GameContainer implements Runnable {

    //Window Settings
    private int width = 320, height = 240;
    private float scale = 3.25f;
    //private int width = 320*8, height = 240*8;
    //private float scale = 3.25f/8;
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
                }
            }
            if(render){
                renderer.clear();
                ++frames;

                game.render(this,renderer);
                renderer.process();

                renderer.drawText("Fps:"+fps,10,10,0xffffffff, Font.CHINYEN_FONT);
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

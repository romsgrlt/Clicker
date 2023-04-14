package com.romsteam.clicker.engine;

import com.romsteam.clicker.engine.audio.SoundClip;
import com.romsteam.clicker.engine.gfx.Font;
import com.romsteam.clicker.engine.gfx.Image;
import com.romsteam.clicker.engine.gfx.ImageTile;
import lombok.ToString;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameManager extends AbstractGame {
    private Image imageRockV3 = new Image("/items/textures/rockV3.png").setAlpha(true);
    private Image imageRockV2 = new Image("/items/textures/rockV2.png").setAlpha(true);

    //var curseur
    private Image image = new Image("/items/textures/cursorV2.png");

    //vars curseur animé
    float curstorStage = 0;
    private ImageTile imageTileCursorV2 = new ImageTile("/items/textures/cursorTilesV2.png",64,64);
    private ImageTile imageTileCursorV3 = new ImageTile("/items/textures/cursorTilesV3.png",64,64).setAlpha(true);

    //vars click animé
    private ImageTile imageTileClickV2 = new ImageTile("/items/textures/clickTilesV2.png",64,64);boolean animLeftClick = false;
    private ImageTile imageTileClickV3 = new ImageTile("/items/textures/clickTilesV3.png",64,64).setAlpha(true);
    float clickStage = 0;

    //vars textBar animé
    float  textBarStage = 0;


    public GameManager(){

    }


    @Override
    public void update(GameContainer gameContainer, float dt) {

        checkInputs(gameContainer);

        //curseur animé
        curstorStage+=dt*20;
        if(curstorStage>19)
            curstorStage=0;

        //textBar animé
        textBarStage+=dt;
        if(textBarStage>1)
            textBarStage=0;

        //clic animé
        if(animLeftClick){
            clickStage+=dt*30;
            if(clickStage>9){
                clickStage = 0;
                animLeftClick = false;
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Renderer renderer) {
        for(int i =0;i<points.size()/2;++i){
        renderer.drawCircle(points.get(i*2),points.get(i*2+1),5,0xffff0000);
        }
        renderer.setzDepth(Integer.MAX_VALUE-2);
        renderer.drawText(message.toString(),10,10,0xffffffff, Font.CHINYEN_FONT,textBarStage<0.5);
        //rendu animation click
        if(animLeftClick) {
            renderer.setzDepth(Integer.MAX_VALUE);
            renderer.drawImageTile(imageTileClickV3, gameContainer.getInput().getMouseX() - imageTileClickV3.getTileWidth() / 2, gameContainer.getInput().getMouseY() - imageTileClickV3.getTileHeight() / 2, (int) clickStage, 0);
        }
        //rendu curseur
        renderer.setzDepth(Integer.MAX_VALUE-1);
        renderer.drawImageTile(imageTileCursorV3,gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY(),(int)curstorStage,0);


    }
    StringBuilder message = new StringBuilder();

    ArrayList<Integer> points = new ArrayList<>();


    private void checkInputs(GameContainer gc) {
        Input input = gc.getInput();
        for(int i = 0;i<256;++i)
            if(input.isKeyDown(i)) {
                int click = (int) Math.random()*4;
                SoundClip.clicks[click].play();
                if(i==8) {
                    if (message.length() > 0)
                        message.deleteCharAt(message.length() - 1);
                }else {
                    message.append((char) i);
                    System.out.println(i);
                    System.out.println(message);
                }
            }

        if(input.isKeyDown(KeyEvent.VK_Z))
            System.out.println("FORWARD");
        if(input.isKeyDown(KeyEvent.VK_Q))
            System.out.println("LEFT");
        if(input.isKeyDown(KeyEvent.VK_S))
            System.out.println("BACKWARD");
        if(input.isKeyDown(KeyEvent.VK_D))
            System.out.println("RIGHT");

        if(input.isButtonDown(MouseEvent.BUTTON1)){
            animLeftClick = true;
            //SoundClip.EXPLOSION_TRANSITION_SOUND.play();
            System.out.println("LEFT_CLICK");
        }
        if(input.isButton(MouseEvent.BUTTON1)){

            points.add(input.getMouseX());
        points.add(input.getMouseY());}
        if(input.isButtonDown(MouseEvent.BUTTON3))
            System.out.println("RIGHT_CLICK");
        if(input.isButtonDown(MouseEvent.BUTTON2))
            System.out.println("WHEEL_CLICK");

        if(input.getScroll()!=0)
            System.out.println(input.getScroll());

        input.update();
    }

    public static void main(String[] args){
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}



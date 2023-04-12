package com.romsteam.clicker.engine;

import com.romsteam.clicker.engine.gfx.Image;
import com.romsteam.clicker.engine.gfx.ImageTile;
import lombok.ToString;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameManager extends AbstractGame {
    //var curseur
    private Image image = new Image("/items/textures/cursor.png");

    //var curseur animé
    float curstorStage = 0;
    private ImageTile imageTileCursor = new ImageTile("/items/textures/cursorTiles.png",64,64);

    //var click animé
    private ImageTile imageTileClick = new ImageTile("/items/textures/clickTiles.png",64,64);boolean animLeftClick = false;
    float clickStage = 0;

    public GameManager(){

    }


    @Override
    public void update(GameContainer gameContainer, float dt) {
        checkInputs(gameContainer);

        //curseur animé
        curstorStage+=dt*20;
        if(curstorStage>19)
            curstorStage=0;
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
        if(animLeftClick)
            renderer.drawImageTile(imageTileClick,gameContainer.getInput().getMouseX()-imageTileClick.getTileWidth()/2,gameContainer.getInput().getMouseY()-imageTileClick.getTileHeight()/2,(int)clickStage,0);
        //rendu curseur
        renderer.drawImageTile(imageTileCursor,gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY(),(int)curstorStage,0);
    }

    private void checkInputs(GameContainer gc) {
        Input input = gc.getInput();

        //System.out.println("mouseX:"+input.getMouseX()+"\tmouseY:"+input.getMouseY());

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
            System.out.println("LEFT_CLICK");
        }
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



package com.romsteam.clicker.engine;

import com.romsteam.clicker.engine.gfx.Image;
import com.romsteam.clicker.engine.gfx.ImageTile;
import lombok.ToString;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

public class GameManager extends AbstractGame {

    private Image image = new Image("/items/textures/cursor.png");
    private ImageTile imageTile = new ImageTile("/items/textures/cursorTiles.png",64,64);

    public GameManager(){

    }
    @Override
    public void update(GameContainer gameContainer, float dt) {
        checkInputs(gameContainer);

        //curseur animé
        curstorStage+=dt*20;
        if(curstorStage>19)
            curstorStage=0;
    }
    float curstorStage = 0;
    @Override
    public void render(GameContainer gameContainer, Renderer renderer) {

        //rendu curseur
        renderer.drawImageTile(imageTile,gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY(),(int)curstorStage,0);
    }





    public static void main(String[] args){
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
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

        if(input.isButtonDown(MouseEvent.BUTTON1))
            System.out.println("LEFT_CLICK");
        if(input.isButtonDown(MouseEvent.BUTTON3))
            System.out.println("RIGHT_CLICK");
        if(input.isButtonDown(MouseEvent.BUTTON2))
            System.out.println("WHEEL_CLICK");

        if(input.getScroll()!=0)
            System.out.println(input.getScroll());

        input.update();
    }
}
@ToString
    class ColorTemp {
    int r,g,b;
        ColorTemp(int x){
            r=new Color(x).getRed();
            g=new Color(x).getGreen();
            b=new Color(x).getBlue();
        }

    }



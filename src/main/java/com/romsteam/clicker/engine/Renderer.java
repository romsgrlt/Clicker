package com.romsteam.clicker.engine;

import com.romsteam.clicker.engine.gfx.Image;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pixelWidth, pixelHeight;
    private int[] pixels;
    public Renderer(GameContainer gc){

        pixelWidth = gc.getWidth();
        pixelHeight = gc.getHeight();
        pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();

    }

    public void clear(){
        for(int i = 0;i< pixels.length;++i){
            pixels[i]=0;
        }
    }

    public void drawImage(Image image, int offsetX, int offsetY){
        int newX = offsetX<0?-offsetX:0;
        int newY = offsetY<0?-offsetY:0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();
        newWidth=newWidth+offsetX<pixelWidth?newWidth:pixelWidth-offsetX;
        newHeight=newHeight+offsetY<pixelHeight?newHeight:pixelHeight-offsetY;

        for(int y=newY;y<newHeight;++y)
            for(int x=newX;x<newWidth;++x)
                setPixel(x+offsetX,y+offsetY,image.getPixels()[x+y*image.getWidth()]);
    }

    private void setPixel(int x, int y, int value) {
        if(x<0||x>=pixelWidth||y<0||y>=pixelHeight||value==0xffff00ff)
            return;
        pixels[x+y*pixelWidth]=value;
    }
}

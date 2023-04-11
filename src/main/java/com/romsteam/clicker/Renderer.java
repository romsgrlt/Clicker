package com.romsteam.clicker;

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
            pixels[i]+=i;
        }
    }
}

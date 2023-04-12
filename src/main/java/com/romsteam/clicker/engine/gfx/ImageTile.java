package com.romsteam.clicker.engine.gfx;

import lombok.Getter;

@Getter
public class ImageTile extends Image{
    int tileWidth,tileHeight;
    public ImageTile(String path, int tileWidth, int tileHeight) {
        super(path);
        this.tileWidth=tileWidth;
        this.tileHeight=tileHeight;
    }

    public ImageTile setAlpha(boolean bool){
        super.setAlpha(true);
        return this;
    }

    public Image getTileImage(int tileX, int tileY){

        int[] pixels = new int[tileHeight*tileWidth];
        for(int y = 0; y < tileHeight; ++y)
            for(int x = 0; x < tileWidth; ++x){
                pixels[x+y*tileWidth]=getPixels()[x+tileX*tileWidth+(y+tileY*tileHeight)*getWidth()];
            }
        return new Image(pixels,tileWidth,tileHeight).setAlpha(isAlpha());
    }

}

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

}

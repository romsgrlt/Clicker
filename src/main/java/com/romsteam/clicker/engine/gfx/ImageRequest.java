package com.romsteam.clicker.engine.gfx;

import lombok.Getter;

@Getter
public class ImageRequest {
    private Image image;
    private int zDepth;
    private int offsetX,offsetY;

    public ImageRequest(Image image, int zDepth, int offsetX, int offsetY){
        this.image = image;
        this.zDepth=zDepth;
        this.offsetX=offsetX;
        this.offsetY = offsetY;
    }
}

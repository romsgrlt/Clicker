package com.romsteam.clicker.engine.gfx;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Getter
public class Image {
    private int width,height;
    private int[] pixels;
    private boolean alpha = false;

    public Image(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Image.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = image.getWidth();
        height = image.getHeight();

        pixels = image.getRGB(0,0,width,height,null, 0, width);

        image.flush();
    }

    public Image(int[] pixels, int width, int height) {
        this.pixels=pixels;
        this.width=width;
        this.height=height;
    }

    public Image setAlpha(boolean b) {
        this.alpha=true;
        return this;
    }
}

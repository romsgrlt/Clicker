package com.romsteam.clicker.engine.gfx;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Getter
public class Image {
    private int width,height;
    private int[] pixels;

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
}

package com.romsteam.clicker.engine.gfx;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Font {
    public static String CHINYEN_FONT_PATH = "/fonts/font2.png";
    public static Font CHINYEN_FONT = new Font(CHINYEN_FONT_PATH);

    private Image fontImage;
    private int[] offsets;
    private int [] widths;

    public Font(String path){

        fontImage = new Image(path);

        offsets = new int[256];
        widths = new int[256];

        int unicode = 0;
        int width = 0;
        for(int i = 0; i<fontImage.getWidth();++i){
            ++width;
            if(fontImage.getPixels()[i]==0xff0000ff)
                offsets[unicode]=i;

            else if(fontImage.getPixels()[i]==0xffffff00) {
                widths[unicode] = width;
                ++unicode;
                width=0;
            }
        }

    }
}

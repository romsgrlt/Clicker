package com.romsteam.clicker.engine;

import com.romsteam.clicker.engine.gfx.Font;
import com.romsteam.clicker.engine.gfx.Image;
import com.romsteam.clicker.engine.gfx.ImageTile;

import java.awt.*;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

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
            pixels[i]= 0;
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

    public void drawImageTile(ImageTile imageTile, int offsetX, int offsetY, int tileX, int tileY){
        int newX = offsetX<0?-offsetX:0;
        int newY = offsetY<0?-offsetY:0;
        int newWidth = imageTile.getTileWidth();
        int newHeight = imageTile.getTileHeight();
        newWidth=newWidth+offsetX<pixelWidth?newWidth:pixelWidth-offsetX;
        newHeight=newHeight+offsetY<pixelHeight?newHeight:pixelHeight-offsetY;

        for(int y=newY;y<newHeight;++y)
            for(int x=newX;x<newWidth;++x) {
                int pixel = (tileX*imageTile.getTileWidth())+x;
                pixel += (tileY*imageTile.getTileHeight()+y)*imageTile.getWidth();
                setPixel(x + offsetX, y + offsetY, imageTile.getPixels()[pixel]);
            }
    }

    public void drawText(String text, int offsetX, int offsetY, int color, Font font){
        Image fontImage = font.getFontImage();

        int offset = 0;

        for(int i = 0;i<text.length();++i){
            int unicode = text.codePointAt(i);

            for(int y = 0;y< fontImage.getHeight();++y){
                for(int x = 0;x<font.getWidths()[unicode];++x){
                    if(fontImage.getPixels()[x+font.getOffsets()[unicode]+y*fontImage.getWidth()]==0xffffffff){
                        setPixel(x+offsetX+offset,y+offsetY,color);
                    }
                }
            }
            offset+=font.getWidths()[unicode];
        }
    }

    private void setPixel(int x, int y, int value) {
        if(x<0||x>=pixelWidth||y<0||y>=pixelHeight||value==0xffff00ff)
            return;
        pixels[x+y*pixelWidth]=value;
    }

    public static List<Integer> colorDegrade(int color1, int color2, int size){
        List<Integer> result = new ArrayList<>();
        Color c1 = new Color(color1);
        Color c2 = new Color(color2);
        int r =c1.getRed(),g=c1.getGreen(),b = c1.getBlue();
        int r2 =c2.getRed(),g2=c2.getGreen(),b2 = c2.getBlue();

        for(int i = 0; i <size/2;++i){
            int newr = r + (r2-r)*i*2/size;
            int newg = g + (g2-g)*i*2/size;
            int newb = b + (b2-b)*i*2/size;
            result.add(new Color(newr,newg,newb).hashCode());
        }
        Color c = c1;c1=c2;c2=c;
        r =c1.getRed();g=c1.getGreen();b = c1.getBlue();
        r2 =c2.getRed();g2=c2.getGreen();b2 = c2.getBlue();
        for(int i = 0; i <size/2;++i){
            int newr = r + (r2-r)*i*2/size;
            int newg = g + (g2-g)*i*2/size;
            int newb = b + (b2-b)*i*2/size;
            result.add(new Color(newr,newg,newb).hashCode());
        }

        return result;
    }
}

package com.romsteam.clicker.engine;

import com.romsteam.clicker.engine.gfx.Font;
import com.romsteam.clicker.engine.gfx.Image;
import com.romsteam.clicker.engine.gfx.ImageRequest;
import com.romsteam.clicker.engine.gfx.ImageTile;

import java.awt.*;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Renderer {
    private List<ImageRequest> imageRequestList = new ArrayList<>();

    private int pixelWidth, pixelHeight;
    private int[] pixels;
    private int[] zAxis;

    public void setzDepth(int zDepth) {
        this.zDepth = zDepth;
    }

    private int zDepth = 0;
    private boolean processing = false;

    public Renderer(GameContainer gc){

        pixelWidth = gc.getWidth();
        pixelHeight = gc.getHeight();
        pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zAxis = new int[pixels.length];

    }
    public void clear(){
        for(int i = 0;i< pixels.length;++i){
            pixels[i]= 0;
            zAxis[i]=0;
        }
    }

    public void drawImage(Image image, int offsetX, int offsetY){

        if(image.isAlpha()&&!processing) {
            imageRequestList.add(new ImageRequest(image, zDepth, offsetX, offsetY));
            return;
        }

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
        if(imageTile.isAlpha()&&!processing){
            imageRequestList.add(new ImageRequest(imageTile.getTileImage(tileX,tileY),zDepth,offsetX,offsetY));
            return;
        }

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
        int alpha = (value>>24)&0xff;
        if(x<0||x>=pixelWidth||y<0||y>=pixelHeight||(alpha==0))
            return;

        int idPixel = x+y*pixelWidth;

        if(zAxis[idPixel]>zDepth)
            return;

        zAxis[idPixel]=zDepth;

        if(alpha==255)
            pixels[idPixel]=value;
        else{
            int color = pixels[idPixel];
            int red =  ((color>>16)&0xff) - (int)((((color>>16)&0xff)-((value>>16)&0xff))*(alpha/255f));
            int green = ((color>>8)&0xff) - (int)((((color>>8)&0xff)-((value>>8)&0xff))*(alpha/255f));
            int blue =  (color&0xff) - (int)(((color&0xff)-(value&0xff))*(alpha/255f));

            pixels[idPixel]=(255<<24|red<<16|green<<8|blue);
        }
    }

    public void process(){
        processing = true;
        imageRequestList.sort(new Comparator<ImageRequest>() {
            @Override
            public int compare(ImageRequest ir1, ImageRequest ir2) {
                return ir1.getZDepth()== ir2.getZDepth()?0:ir1.getZDepth()>ir2.getZDepth()?1:-1;
            }
        });
        for(ImageRequest imageRequest : imageRequestList){
            zDepth=imageRequest.getZDepth();
            drawImage(imageRequest.getImage(),imageRequest.getOffsetX(),imageRequest.getOffsetY());
        }
        imageRequestList.clear();
        processing = false;
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

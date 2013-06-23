/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import Util.Vector2;
import Util.ImageLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author George
 */
public class Tile implements ImageObserver {
    
    static BufferedImage dirtTexture = new BufferedImage(8,8,BufferedImage.TYPE_4BYTE_ABGR);
    public static final int maxH = 16;
    float height;
    
    public Tile(){
        height = 0;
    }
    
    public Tile(float h){
        height = h;
    }
    
    public void drawTile(int i, int j, Vector2 v,Graphics g,float scale){
        Image b = Tile.dirtTexture;
        g.drawImage(b, (int)v.x,(int)v.y,(int)(b.getWidth(this)*scale),(int)(b.getHeight(this)*scale),MiningPipes.imob);
        g.setColor(new Color(0,0,0,(int)(255-(height/Tile.maxH*255))));
        g.fillRect((int)v.x,(int)v.y,(int)(b.getWidth(this)*scale),(int)(b.getHeight(this)*scale));
        
    }

    public void set(float h){
        height = h;
    }

    public void raise(float h){
        height += h;
        height = Math.max(0, Math.min(height,maxH));
    }
    
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
    {
        return true;
    }
    
    public static void loadContent(ImageLoader im)
    {
        try{
        dirtTexture = (BufferedImage) im.loadImage(ImageLoader.TERRAIN,0,0);
        }
        
        catch(Exception e){System.out.println("tile load exception");
        }
    }
       
}

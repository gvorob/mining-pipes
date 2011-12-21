/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author George
 */
class Tile implements ImageObserver {
    
    static BufferedImage dirtTexture;
    public static final int maxH = 16;
    float height;
    
    public Tile(){
        height = 0;
    }
    
    public Tile(float h){
        height = h;
    }
    
    public void drawTile(int i, int j, Vector2 v,Graphics g){
        BufferedImage b = Tile.dirtTexture;
        Graphics g2= b.getGraphics();
        g2.setColor(new Color(0,0,0,height/Tile.maxH*255));
        g2.fillRect(0,0,b.getWidth(),b.getHeight());
        g.drawImage((Image)b, (int)v.x,(int)v.y,this);
      
        
    }

    public void set(float h){
        height = h;
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
    
    
    
    
    
}

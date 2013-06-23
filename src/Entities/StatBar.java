/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import miningpipes.Game;
import Util.Vector2;

/**
 *
 * @author George
 */
public class StatBar extends Entity{
    Color color;
    float width;
    boolean visible = false;
    float height = .05f;
    float percentFilled = 1f;
    
    public StatBar(Vector2 v, float w, float h, Color c)
    {
        super(v);
        width = w;
        height = h;
        color = c;
    }
    
    public void setVisible(boolean flag)
    {
        visible = flag;
    }
    
    public void setFill(float f)
    {
        percentFilled = f;
    }
    
    public void update(Game tGame){}
   
    
    
    public void draw(Game tGame,BufferedImage im)
    {
        if(visible)
        {
            Point coord = tGame.getScreenCoordFromTile(pos);
            Graphics g = im.getGraphics();
            int pix = tGame.getTilePixels();
            int w = (int)(width*pix);
            int h = (int)(height*pix);
            g.setColor(Color.white);
            g.fillRect(coord.x,coord.y, w, h);
            g.setColor(color);
            g.fillRect(coord.x, coord.y, (int)(w*percentFilled), h);
        }
    }
    
    public boolean isInTile(Point p)
    {
        return false;
    }

    public void remove()
    {
        markedForDeath = true;
    }
    
}

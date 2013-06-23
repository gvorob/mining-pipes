/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author George
 */
public class Screen implements ImageObserver
{
    public Frame f;
    public Canvas c;
    
    public Screen(int x, int y)
    {
        f = new Frame("Hello");
        f.setSize(x, y);
        f.setVisible(true);
        c = new Canvas();
        c.setSize(x, y);
        c.setVisible(true);
        f.add(c);
    }
    
    public void clear()
    {
        Graphics g = c.getGraphics();
        g.fillRect(0, 0,10000, 10000);
    }
    
    public void draw(Image b)
    {
        try{
        Graphics g = c.getGraphics();
        
        g.drawImage(b,0,0,this);
        }
        catch (Exception e){System.out.println("screendrawException");}
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}

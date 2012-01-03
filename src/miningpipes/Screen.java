/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    
    public void draw(Image b) throws IOException
    {
        Graphics g = c.getGraphics();
        g.drawImage(b,0,0,this);
    }


    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}

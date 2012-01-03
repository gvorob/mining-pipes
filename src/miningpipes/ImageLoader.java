/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class ImageLoader implements ImageObserver{
    
    public Image[] Images;
    public static final int TERRAIN = 0;
    public static final int TOOLS = 1;
    public static final int STRUCTURES = 2;
    
    public ImageLoader()
    {
        Images = new Image[3];
        File f = new File("src/Terrain.png");
        Images[TERRAIN] =  Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
        f = new File("src/Tools.png");
        Images[TOOLS]=  Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
        f = new File("src/Structures.png");
        Images[STRUCTURES]=  Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }
    
    public  Image loadImage(int image, int x, int y)
    {
        return loadImage(image,x,y,1,1);
       
    }
    
    public Image loadImage(int image, int x, int y, int w, int h)
    {
        BufferedImage b = new BufferedImage(8*w,8*h,BufferedImage.TYPE_4BYTE_ABGR);
        
        
        Graphics g = b.getGraphics();
        g.drawImage(Images[image], 0, 0, 8*w,8*h,8*x,8*y,8*(x+w),8*(y+h),this);
        return b;
        
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
    
}

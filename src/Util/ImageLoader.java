/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public class ImageLoader{
    
    public Image[] Images;
    public static final int TERRAIN = 0;
    public static final int TOOLS = 1;
    public static final int STRUCTURES = 2;
    public static final int ENTITIES = 3;
    
    public ImageLoader()
    {
        Images = new Image[4];
        File f = new File("src/Terrain.png");
        System.out.println(f.getAbsolutePath());
        Images[TERRAIN] =  Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
        f = new File("src/Tools.png");
        System.out.println(f.getAbsolutePath());
        Images[TOOLS]=  Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
        f = new File("src/Structures.png");
        System.out.println(f.getAbsolutePath());
        Images[STRUCTURES]=  Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
        f = new File("src/Entity.png");
        System.out.println(f.getAbsolutePath());
        Images[ENTITIES]=  Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
        
    }
    
    public  Image loadImage(int image, int x, int y)
    {
        return loadImage(image,x,y,1,1);
       
    }
    
    public Image loadImage(int image, int x, int y, int w, int h)
    {
        BufferedImage b = new BufferedImage(8*w,8*h,BufferedImage.TYPE_4BYTE_ABGR);
        
        
        Graphics g = b.getGraphics();
        g.drawImage(Images[image], 0, 0, 8*w,8*h,8*x,8*y,8*(x+w),8*(y+h),MiningPipes.imob);
        try {Thread.sleep(2);} 
        catch (InterruptedException ex) {Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);}
        return b;
        
    }

}

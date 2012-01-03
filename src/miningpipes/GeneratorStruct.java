/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author George
 */
public class GeneratorStruct extends Structure{
    
    int storedEnergy;
    int energyRate;
    static BufferedImage texture;
    
    public GeneratorStruct(int x, int y, int startEn,int enRate)
    {
        super(x,y);
        storedEnergy=startEn;
        energyRate=enRate;
    }
    
    public static void loadContent(ImageLoader im)
    {
        Graphics g = texture.getGraphics();
        g.drawImage(im.loadImage(ImageLoader.STRUCTURES, 0, 0, 2, 2),0,0,MiningPipes.imob);
    }
    
    public void update()
    {
        storedEnergy+=energyRate;
    }
    
    public void draw(BufferedImage im)
    {
        
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.image.BufferedImage;

/**
 *
 * @author George
 */
public class Structure {
    
    int x;
    int y;
    boolean markedForDeath;
    
    public Structure(int X, int Y)
    {
        x=X;
        y=Y;
        markedForDeath = false;
    }
    
    public void update()
    {
        
    }
    
    public void draw(BufferedImage im)
    {
        
    }
    
    public boolean isMarkedForDeath(){return markedForDeath;}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

/**
 *
 * @author George
 */
public class StructArray {
    
    Structure[] structures;
    
    public StructArray()
    {    
        structures = new Structure[0];
    }
    
    public void add(Structure s)
    {
       Structure[] temp = new Structure[Array.getLength(structures)+1];
       System.arraycopy(structures, 0, temp, 0, Array.getLength(structures));
       structures = temp;
       structures[Array.getLength(structures)-1]=s;
    }
    
    public void remove()
    {
        int numMarkedForDeath = 0;
        for(int i = 0;i<Array.getLength(structures);i++)
        {
            if(structures[i].isMarkedForDeath())
            numMarkedForDeath++;
        }
        
        int[] structsToSave = new int[Array.getLength(structures)-numMarkedForDeath];
        int currNum = 0;
        for(int i = 0;i<Array.getLength(structures);i++)
        {
            if(!structures[i].isMarkedForDeath())
            {
                structsToSave[currNum]=i;
                currNum++;
            }
        }
        
        Structure[] temp = new Structure[Array.getLength(structsToSave)];
        for(int i =0;i<Array.getLength(structsToSave);i++)
        {
            temp[i] = structures[structsToSave[i]];
        }
        
        structures = temp;
    }
    
    public void update()
    {
        for(int i = 0;i<Array.getLength(structures);i++)
        {
            structures[i].update();
        }
    }
    
    public void draw(BufferedImage im)
    {
        for(int i = 0;i<Array.getLength(structures);i++)
        {
            structures[i].draw(im);
        }
    }
}

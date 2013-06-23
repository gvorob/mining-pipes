/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import Structures.Structure;
import Util.BoundingBox;
import Util.Vector2;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
       boolean slotFound = false;
       for(int i = 0; i < structures.length && !slotFound; i++)
       {
           if(structures[i] == null)
           {
               slotFound = true;
               structures[i] = s;
           }
       }
       if(!slotFound)
       {
           Structure[] temp = new Structure[Array.getLength(structures)+1];
           System.arraycopy(structures, 0, temp, 0, Array.getLength(structures));
           structures = temp;
           structures[Array.getLength(structures)-1]=s;
       }
    }
    
    public boolean isObstructed(Point p)
    {
        for(int i = 0; i < Array.getLength(structures);i++)
        {
            if(structures[i].isObstructing(p))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isObstructed(int x, int y)
    {
        return isObstructed(new Point(x,y));
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
    
    public void update(Game tGame)
    {
        for(int i = 0;i<Array.getLength(structures);i++)
        {
            structures[i].update(tGame);
            remove();
        }
    }
    
    public Attackable hasAttackable(Point p)
    {
        for(int i = 0;i<structures.length;i++)
        {
            if(structures[i].attackableAt(p));
            return structures[i];
        }
        return null;
    }
    
    public void draw(BufferedImage im,Game tGame)
    {
        Graphics g = im.getGraphics();
        for(int i = 0;i<Array.getLength(structures);i++)
        {
            Point p = tGame.getScreenCoordFromTile(structures[i].pos);
            g.drawImage(structures[i].draw(tGame,(int)(tGame.tileWidth*tGame.scaleFactor)), p.x, p.y,MiningPipes.imob);
        }
    }

    Structure getStruct(Point p) throws Exception{
        for(int i = 0; i < Array.getLength(structures);i++)
        {
            if (structures[i].isObstructing(p))
            return structures[i];
        }
        throw new Exception();
    }
    
    public boolean isObstructed(BoundingBox bounds)
    {
        for(int i = 0; i < structures.length; i ++)
        {
            if(structures[i].getBounds().isIntersecting(bounds))
            {return true;}
        }
        return false;
    }

    public Attackable[] getStructsWithinRadius(Vector2 pos, double range) {
        ArrayList<Attackable> temp = new ArrayList<Attackable>(0);
        for(int i = 0;i<structures.length;i++)
        {
            if(structures[i].getBounds().distanceTo(pos) <= range && !structures[i].isMarkedForDeath())
            {
                temp.add(structures[i]);
            }
        }
        return temp.toArray(new Attackable[0]);
    }
}

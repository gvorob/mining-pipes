/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import Util.Vector2;
import Entities.AttackableEntity;
import Entities.Entity;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author George
 */
public class EntityArray {
    
    Entity[] entities;
    
    public EntityArray()
    {    
        entities = new Entity[0];
    }
    
    public void add(Entity e)
    {
       Entity[] temp = new Entity[Array.getLength(entities)+1];
       System.arraycopy(entities, 0, temp, 0, Array.getLength(entities));
       entities = temp;
       entities[Array.getLength(entities)-1]=e;
    }
    
    public int[] hasEntity(Point p)
    {
        int counter = 0;
        
        for(int i = 0; i < Array.getLength(entities);i++)
        {
            if(entities[i].isInTile(p))
            {
                counter++;
            }
        }
        
        int[] temp = new int[counter];
        counter = 0;
        
        for(int i = 0; i < Array.getLength(entities);i++)
        {
            if(entities[i].isInTile(p))
            {
                temp[counter] = i;
                counter++;
            }
        }
        
        return temp;
    }
    
    public int[] hasEntity(int x, int y)
    {
        return hasEntity(new Point(x,y));
    }
    
    public void remove()
    {
        int numMarkedForDeath = 0;
        for(int i = 0;i<Array.getLength(entities);i++)
        {
            if(entities[i].isMarkedForDeath())
            numMarkedForDeath++;
        }
        
        int[] entsToSave = new int[Array.getLength(entities)-numMarkedForDeath];
        int currNum = 0;
        for(int i = 0;i<Array.getLength(entities);i++)
        {
            if(!entities[i].isMarkedForDeath())
            {
                entsToSave[currNum]=i;
                currNum++;
            }
        }
        
        Entity[] temp = new Entity[Array.getLength(entsToSave)];
        for(int i =0;i<Array.getLength(entsToSave);i++)
        {
            temp[i] = entities[entsToSave[i]];
        }
        
        entities = temp;
    }
    
    AttackableEntity[] getAttackablesWithinRadius(Vector2 pos, float range)
    {   
        ArrayList<AttackableEntity> temp = new ArrayList<AttackableEntity>(0);
        for(int i = 0;i<entities.length;i++)
        {
            if(entities[i].isAttackable())
            {
                if(((AttackableEntity)entities[i]).getBounds().distanceTo(pos) <=range)
                {
                    temp.add((AttackableEntity)entities[i]);
                }
            }
        }
        return temp.toArray(new AttackableEntity[0]);
    }
    
    public void update(Game tGame)
    {
        for(int i = 0;i<Array.getLength(entities);i++)
        {
            entities[i].update(tGame);
        }
        remove();
    }
    
    public void draw(BufferedImage im,Game tGame)
    {
        for(int i = 0;i<Array.getLength(entities);i++)
        {
            entities[i].draw(tGame,im);
        }
    }

    Entity getEnt(int x){
        return entities[x];
    }
}

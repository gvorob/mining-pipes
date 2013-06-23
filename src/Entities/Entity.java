/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import miningpipes.Game;
import Util.Vector2;

/**
 *
 * @author George
 */
public abstract class Entity {
    
    
    protected Vector2 pos;
    protected boolean markedForDeath;
    
    public Entity(Vector2 v)
    {
        pos = v;
        markedForDeath = false;
    }
    
    public abstract void update(Game tGame);
    
    public abstract void draw(Game tGame,BufferedImage im);
    
    public abstract boolean isInTile(Point p);
    
    public boolean isMarkedForDeath(){return markedForDeath;}
    
    public Vector2 getPos()
    {
        return pos;
    }
    
    public boolean isAttackable()
    {
        return false;
    }
}

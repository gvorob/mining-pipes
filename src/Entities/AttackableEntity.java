/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Entity;
import java.awt.Point;
import miningpipes.Attackable;
import Util.BoundingBox;
import Util.Vector2;

/**
 *
 * @author George
 */
public abstract class AttackableEntity extends Entity implements Attackable {
    float maxHealth;
    float health;
    BoundingBox bounds;
    
    public AttackableEntity(Vector2 pos, Vector2 size, float h)
    {
        super(pos);
        health = maxHealth = h;
        bounds = new BoundingBox(pos,size);
    }
    
    public boolean isAttackable()
    {
        return !markedForDeath;
    }
    
    public BoundingBox getBounds()
    {
        return bounds;
    }
    
    public boolean isInTile(Point p)
    {
        return bounds.isInTile(p);
    }
    
    public void getAttacked(float i)
    {
        health-=i;
        if(health<=0)
        {
            die();
        }
    }
    
    public void die()
    {
        markedForDeath = true;
    }
}

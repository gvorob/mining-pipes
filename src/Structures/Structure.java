/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Util.BoundingBox;
import Util.Vector2;
import Entities.StatBar;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import miningpipes.Attackable;
import miningpipes.Game;
import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public abstract class Structure implements Attackable{
    
    public Point pos;
    public float health;
    public BoundingBox bounds;
    public float maxHealth;
    boolean markedForDeath;
    StatBar healthBar;
    
    public Structure(Point p, BoundingBox b, float h)
    {
        pos = p;
        bounds = b;
        health = maxHealth = h;
        markedForDeath = false;
        healthBar = new StatBar(new Vector2(pos.x, pos.y + bounds.getSize().y), bounds.getSize().x, .05f, Color.red);
        MiningPipes.theGame.addEntity(healthBar);
    }
    
    public void update(Game tGame)
    {
        if(health == maxHealth)
        {
            healthBar.setVisible(false);
        }
        else
        {
            healthBar.setVisible(true);
        }
        healthBar.setFill((float)health/maxHealth);
        if(health<=0)
        {
            die();
        }
    }
    
    public abstract BufferedImage draw(Game tGame,int tP);
    
    public boolean isObstructing(Point p){return bounds.isInTile(p);}
    
    public boolean isMarkedForDeath(){return markedForDeath;}

    public abstract EnergyStructure canConnect(Point p,Structure t);
    
    public abstract boolean attackableAt(Point p);
    
    public BoundingBox getBounds(){return bounds;}
    
    public Vector2 getCenter()
    {
        return getBounds().getCenter();
    }
    
    public Point getPosition() {
        return pos;
    }
    
    protected void die()
    {
        markedForDeath = true;
        healthBar.remove();
    }
}

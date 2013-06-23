/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates;

import Util.Vector2;
import Entities.Entity;
import java.awt.Point;
import java.awt.image.BufferedImage;
import miningpipes.Game;

/**
 *
 * @author George
 */
public class EntityTemplate extends Entity {
    
    public EntityTemplate(Vector2 v)
    {
        super(v);
        //Init things here.
    }
    
    public void update(Game tGame)
    {
        //Actions that occur every tick.
    }
    
    public void draw(Game tGame,BufferedImage im)
    {
        //Draw to im, that's what is drawn to the screen.
        //Use tGame.getTile(someVector2OnTheGrid); to get the screen coords to draw to.
    }
    
    public boolean isInTile(Point p)
    {
        //Check if this entity clips with the tile. If it is a non-colliding entity, return false. Use at your discretion.
        return false;
    }
}

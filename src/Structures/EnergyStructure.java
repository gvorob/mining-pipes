/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Util.BoundingBox;
import java.awt.Point;
import miningpipes.Game;
import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public abstract class EnergyStructure extends Structure{
    
    int netIndex;
    float storedEnergy;
    
    
    public EnergyStructure(Point p, BoundingBox b, float health)
    {
        super(p, b, health);
        netIndex = MiningPipes.theGame.getNet().add(this);
        
    }
    
    public void createLinks(Game tGame)
    {
        Point[] points = {
            new Point(pos.x+1, pos.y),
            new Point(pos.x, pos.y+1),
            new Point(pos.x-1, pos.y),
            new Point(pos.x, pos.y-1)
        };
        
        for(int i = 0; i < 4; i++)
        {
            try{
                Structure temp = tGame.getStruct(points[i]);
                EnergyStructure temp2 = temp.canConnect(points[i], this);
                if(temp2!=null)
                tGame.getNet().addLink2Way(netIndex, temp2.netIndex);
            }
            catch(Exception e){}
        }
    }
    
    public EnergyStructure canConnect(Point p,Structure t)
    {
        if(bounds.isInTile(p))
            return this;
        return null;
    }
    
    public float surplusEnergy()
    {
        return 0;
    }
    
    public float neededEnergy()
    {
        return 0;
    }
    
    public void receiveEnergy(float e)
    {
        storedEnergy+=e;
    }
    
    protected void die()
    {
        MiningPipes.theGame.getNet().remove(netIndex);
        super.die();
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Util.ImageLoader;
import Util.AnimationScheduler;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import miningpipes.Attackable;
import miningpipes.Game;
import miningpipes.MiningPipes;
import Util.Vector2;

/**
 *
 * @author George
 */
public class FliesEntity extends AttackableEntity{
    
    static BufferedImage[] text = new BufferedImage[3];    
    
    static final float defaultHealth = 5;
    static final Vector2 defaultSize = new Vector2(1,1);
    static final int frameDelay = 150;
    static final float range = 12;
    static final float speed = 5;
    static final float attack = 5;
    
    private Attackable currTarget;
    private AnimationScheduler animator;
            
    
    public FliesEntity(Vector2 v)
    {
        super(v,defaultSize, defaultHealth);
        animator = new AnimationScheduler(3, frameDelay);
    }
    
    public static void loadContent(ImageLoader im)
    {
        text[0] = (BufferedImage) im.loadImage(ImageLoader.ENTITIES, 0, 0);
        text[1] = (BufferedImage) im.loadImage(ImageLoader.ENTITIES, 1, 0);
        text[2] = (BufferedImage) im.loadImage(ImageLoader.ENTITIES, 2, 0);
    }
    
    public void update(Game tGame)
    {
        animator.update();
        currTarget = chooseTarget(searchTargets(tGame));
        if(currTarget!=null)
        {
            moveTowardsTarget(currTarget);
            if(isOnTarget())
            {
                currTarget.getAttacked(attack*Game.timePerTick());
            }
        }
    }
    
    private void moveTowardsTarget(Attackable target)
    {
        moveTowards(target.getBounds().getPointNearest(getCenter()));
    }
    
    private void moveTowards(Vector2 target)
    {
        Vector2 temp = Vector2.vecSubt(target, getCenter());
        temp.normalize();
        temp.vecMult(Game.timePerTick()*(float)speed);
        move(temp);
    }
    
    public Vector2 getCenter()
    {
        return bounds.getCenter();
    }
    
    private boolean isOnTarget()
    {
        if(currTarget!=null) 
        {
            return currTarget.getBounds().getPointNearest(getCenter()).equals(getCenter());
        }
        return false;
    }
    
    private void move(Vector2 m)
    {
        pos.vecAdd(m);
    }
    
    private Attackable chooseTarget(Attackable[] targets)
    {
        Attackable temp = null;
        if(targets.length>0)
        {
            double lowVal = 1; //lowest pathing value till now
            int bestTarg = -1; //Best target so far
            for(int i=0;i<targets.length;i++)
            {
                double tempVal = calcPathCost(targets[i]);
                if(tempVal>lowVal)
                {
                    lowVal = tempVal;
                    bestTarg = i;
                }
            }
            if(bestTarg!=-1)
            {
                temp = targets[bestTarg];
            }
        }
        return temp;
    }
    
    private float calcPathCost(Attackable target)
    {
        return range/target.getBounds().distanceTo(pos);
    }
    
    private Attackable[] searchTargets(Game tGame)
    {
        return tGame.getStructsWithinRadius(getCenter(),range);
    }
    
    private Attackable getAttackableAt(Point p,Game tGame)
    {
        return tGame.hasAttackable(p);
    }
        
    public boolean isObstructing(Point p)
    {
        return ((p.x==pos.x)&&(p.y==pos.y));
    }
    
    public void draw(Game tGame,BufferedImage im)
    {
        int frame = animator.getFrame();
        Image pic = text[frame];
        Point p = tGame.getScreenCoordFromTile(pos);
        Graphics g = im.getGraphics();
        int scaledWidth = (int)(pic.getWidth(MiningPipes.imob)*tGame.scaleFactor);
        g.drawImage(pic,p.x,p.y,scaledWidth,scaledWidth,MiningPipes.imob);
        
    }

}

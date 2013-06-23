/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Entities.LightningEffect;
import Util.BoundingBox;
import Util.Vector2;
import Util.Mouse;
import Entities.StatBar;
import Util.ImageLoader;
import Util.AnimationScheduler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import miningpipes.Attackable;
import miningpipes.Game;
import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public class TeslaStruct extends EnergyStructure{

    static final int frameDelay = 75;
    static final float defaultHealth = 100;
    static int maxCooldown = 250;
    static int energyPerShot = 20;
    static int damagePerShot = 20;
    static final float maxEnergy = 100;
    static final int range = 7;
    static final float chargeRate = 100;
    
    int timeElapsed;
    AnimationScheduler animator;
    int cooldown;
    Attackable currTarget;
    StatBar energyBar;

    static final int SHOT_EFFECT_TIME = 150;
    static final Color SHOT_EFFECT_COLOR = new Color(70, 160, 255);
    static final int SHOT_EFFECT_WIDTH = 2;
    static final int SHOT_RECURSIONS = 6;
    static final float SHOT_DISPLACEMENT = 1f; 
    static final float SHOT_COEFFICIENT = .5f;
    
    static BufferedImage base;
    static BufferedImage[] coil = new BufferedImage[8];


    public TeslaStruct(Point p, int startEn)
    {
        super(p,new BoundingBox(Vector2.fromPoint(p),new Vector2(1,1)),defaultHealth);
        animator = new AnimationScheduler(8, frameDelay);
        cooldown = maxCooldown;
        energyBar = new StatBar(Vector2.vecAdd(new Vector2(0f, .05f),getBounds().getBL()), 1f, .05f, Color.blue);
        energyBar.setVisible(true);
        MiningPipes.theGame.addEntity(energyBar);
        createLinks(MiningPipes.theGame);
    }

    public static void loadContent(ImageLoader im)
    {
        base = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 0, 2);
        coil[0] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 0, 1);
        coil[1] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 1, 1);
        coil[2] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 2, 1);
        coil[3] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 3, 1);
        coil[4] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 4, 1);
        coil[5] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 5, 1);
        coil[6] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 6, 1);
        coil[7] = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 7, 1);
    }

    public void update(Game tGame)
    {
        cooldown -= MiningPipes.updateTime;
        energyBar.setFill((float)storedEnergy/maxEnergy);
        if(cooldown <= 0) 
        {
            cooldown = 0;
            currTarget = chooseTarget(searchTargets(tGame));
            if(currTarget!= null && storedEnergy>energyPerShot)
            {
                tGame.addEntity(shoot(currTarget));
            }
        }
        
        super.update(tGame);
        animator.update();
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
    private double calcPathCost(Attackable target)
    {
        return range/target.getBounds().distanceTo(getCenter());
    }
    private Attackable[] searchTargets(Game tGame)
    {
        return tGame.getAttackablesWithinRadius(getCenter(),range);
    }
    private LightningEffect shoot(Attackable target)
    {
        storedEnergy-=energyPerShot;
        cooldown = maxCooldown;
        target.getAttacked(damagePerShot);
        return new LightningEffect(target.getBounds().getCenter(), getCenter(), SHOT_EFFECT_TIME, SHOT_EFFECT_COLOR, SHOT_EFFECT_WIDTH, SHOT_RECURSIONS, SHOT_DISPLACEMENT, SHOT_COEFFICIENT);
    }
    public static boolean canPlace(Game tGame, Mouse tMouse)
    {
        Point m = tMouse.get();
        Point p = tGame.tileFromMouseCoord(m);

        return !tGame.isObstructed(p.x,p.y);

    }

    public BufferedImage draw(Game tGame,int tP)
    {
        BufferedImage im = new BufferedImage(tP*2,tP*2,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = im.getGraphics();
        g.drawImage(base,0, 0,(int)(base.getWidth(MiningPipes.imob)*tGame.scaleFactor),(int)(base.getWidth(MiningPipes.imob)*tGame.scaleFactor), MiningPipes.imob);
        g.drawImage(coil[animator.getFrame()],0,0, tP, tP, MiningPipes.imob);
        return im;
    }

    public boolean attackableAt(Point p) {
        return isObstructing(p);
    }
    

    public void getAttacked(float i) {
        health -= i;
    }

    protected void die()
    {
        energyBar.remove();
        super.die();
    }
    
    public float neededEnergy()
    {
        float needed = maxEnergy-storedEnergy;
        return (needed > chargeRate * MiningPipes.theGame.timePerTick()?
                chargeRate * MiningPipes.theGame.timePerTick()
                :needed);
    }
        
}

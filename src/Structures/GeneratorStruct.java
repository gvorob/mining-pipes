/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Util.BoundingBox;
import Util.Vector2;
import Util.Mouse;
import Util.ImageLoader;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import miningpipes.Game;
import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public class GeneratorStruct extends EnergyStructure{
    
    static final float energyRate = 10;
    static BufferedImage texture;
    static final float defaultHealth = 100;
    static final float maxEnergy = 30;
    static final float dischargeRate = 10;
    
    public GeneratorStruct(Point p)
    {
        super(p,new BoundingBox(Vector2.fromPoint(p), new Vector2(1,1)),defaultHealth);
        storedEnergy=0;
        createLinks(MiningPipes.theGame);
    }
    
    public static void loadContent(ImageLoader im)
    {
        texture = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 0, 0, 1, 1);
    }
    
    public void update(Game tGame)
    {
        super.update(tGame);
        storedEnergy+=energyRate*tGame.timePerTick();
    }
    
    public static boolean canPlace(Game tGame, Mouse tMouse)
    {
        Point m = tMouse.get();
        Point p = tGame.tileFromMouseCoord(m);
        
        return !tGame.isObstructed(p.x,p.y);
        
    }
    
    public BufferedImage draw(Game tGame,int tP)
    {
        BufferedImage im = new BufferedImage(tP,tP,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = im.getGraphics();
        g.drawImage(texture,0, 0,(int)(texture.getWidth(MiningPipes.imob)*tGame.scaleFactor),(int)(texture.getWidth(MiningPipes.imob)*tGame.scaleFactor), MiningPipes.imob);
        //MiningPipes.theScreen.draw(im);
        return im;
    }

    public boolean attackableAt(Point p) {
        return isObstructing(p);
    }
    
    public void getAttacked(float i) {
        health -= i;
    }
    
    public float surplusEnergy()
    {
        return (storedEnergy > dischargeRate * MiningPipes.theGame.timePerTick()?
                dischargeRate * MiningPipes.theGame.timePerTick():
                storedEnergy);
    }
}

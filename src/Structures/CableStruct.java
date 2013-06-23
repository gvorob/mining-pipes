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
public class CableStruct extends EnergyStructure{
    
    static BufferedImage mainText;
    static BufferedImage leftText;
    static BufferedImage topText;
    static BufferedImage rightText;
    static BufferedImage bottomText;
    
    static final float defaultHealth = 10;
    static final float wireWidth = .25f;
    static final float distToWire = (.5f - wireWidth/2f);
    
    boolean[] connections = new boolean[]{false,false,false,false};
    
    public CableStruct(Point p)
    {
        super(p,new BoundingBox(Vector2.fromPoint(p),new Vector2(1,1)),defaultHealth);
        createLinks(MiningPipes.theGame);
    }
    
    public static void loadContent(ImageLoader im)
    {
        mainText = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 2, 0);
        leftText = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 3, 0);
        topText = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 4, 0);
        rightText = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 5, 0);
        bottomText = (BufferedImage) im.loadImage(ImageLoader.STRUCTURES, 6, 0);
    }
    
    public void update(Game tGame)
    {
        super.update(tGame);
        boolean[] tempConnections = new boolean[4];
        for(int i = 0; i < 4; i++)
        {
            
            tempConnections[i] = true;
            
            Point temp = new Point((i==3?-1:i%2)+pos.x,(i==3?0:i-1)+pos.y);
            try{tempConnections[i] = tGame.getStruct(temp).canConnect(temp,this) != null;}
            catch(Exception e){tempConnections[i]=false;}
        }
        if(!tempConnections.equals(connections))
        {
            updateBounds();
            connections = tempConnections;
        }
    }
    
    public void updateBounds()
    {
        float top       = connections[0]? pos.y :(float)pos.y + distToWire;
        float right     = connections[1]? pos.x + 1 :(float)pos.x + 1 - distToWire;
        float bottom    = connections[2]? pos.y + 1 :(float)pos.y + 1 - distToWire;
        float left      = connections[3]? pos.x :(float)pos.x + distToWire;
        bounds = new BoundingBox(new Vector2(left,top),Vector2.vecSubt(new Vector2(right,bottom), new Vector2(left,top)));
    }
    
    public static boolean canPlace(Game tGame, Mouse tMouse)
    {
        Point m = tMouse.get();
        Point p = tGame.tileFromMouseCoord(m);
        
        return(!(
            tGame.isObstructed(p.x,p.y)
        ));
        
    }
    
    public BufferedImage draw(Game tGame,int tP)
    {
        
        
        BufferedImage im = new BufferedImage(tP,tP,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = im.getGraphics();
        if(connections[3])
        g.drawImage(leftText,0, 0,(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor),(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor), MiningPipes.imob);
        if(connections[0])
        g.drawImage(topText,0, 0,(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor),(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor), MiningPipes.imob);
        if(connections[1])
        g.drawImage(rightText,0, 0,(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor),(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor), MiningPipes.imob);
        if(connections[2])
        g.drawImage(bottomText,0, 0,(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor),(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor), MiningPipes.imob);
        if(!((connections[3]&&connections[1]&&!connections[0]&&!connections[2])||(!connections[3]&&!connections[1]&&connections[0]&&connections[2])))
        {
            g.drawImage(mainText,0, 0,(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor),(int)(mainText.getWidth(MiningPipes.imob)*tGame.scaleFactor), MiningPipes.imob);
        }
        //MiningPipes.theScreen.draw(im);
        return im;
    }
    
    public boolean attackableAt(Point p) {
        return isObstructing(p);
    }
    
    public void getAttacked(float i)
    {
        health-=i;
    }
}

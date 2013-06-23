/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Util.Vector2;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import miningpipes.Game;

/**
 *
 * @author George
 */
public class LightningEffect extends Entity {
    private long deathTime;
    private  ArrayList<Vector2> path;
    private Color color;
    private int width;
    
    public static int halo = 0;
    public static Color haloColor = new Color(255, 255, 255, 100);
    
    public LightningEffect(Vector2 start, Vector2 end, long delay, Color c, int w, int numRecursions, float displacementInitial, float displacementFactor) {
        super(start);
        deathTime = System.currentTimeMillis()+delay;
        color = c;
        width = w;
        path = genPath(start, end, numRecursions, displacementInitial, displacementFactor);
    }
    
    private ArrayList<Vector2> genPath(Vector2 s, Vector2 e, int n, float d, float f)
    {
        ArrayList<Vector2> temp = new ArrayList<Vector2>(2+((int)Math.pow(2,n-1)));
        temp.add(s);
        temp.add(e);
        genSegment(temp, 1 ,n,d,f);
        
        return temp;
    }
    
    private void genSegment(ArrayList<Vector2> v, int i, int n, float d, float f)
    {
        if(n == 0)return;
        Random rand = new Random();
        Vector2 start = v.get(i);
        Vector2 end = v.get(i-1);
        Vector2 temp = Vector2.vecMult(.5f,Vector2.vecAdd(start, end));
        temp.vecAdd(Vector2.vecMult(d, new Vector2(rand.nextFloat()*2-1,rand.nextFloat()*2-1)));
        d*=f;
        v.add(i,temp);
        genSegment(v,i,n-1,d,f);
        genSegment(v,i+((int)Math.pow(2,n-1)),n-1,d,f);
    }
    
    public void update(Game tGame) {
        if(System.currentTimeMillis()>deathTime) markedForDeath = true;
    }
    
    public void draw(Game tGame, BufferedImage im) {
        float tp = tGame.getTilePixels();
        Graphics2D g = im.createGraphics();
        g.setStroke(new BasicStroke(width+halo, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(haloColor);
        for(int i = 1; i< path.size(); i++)
        {
            Point p1 = tGame.getScreenCoordFromTile(path.get(i));
            Point p2 = tGame.getScreenCoordFromTile(path.get(i-1));
            g.drawLine(p1.x,p1.y,p2.x,p2.y);
        }
        g.setColor(color);
        g.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for(int i = 1; i< path.size(); i++)
        {
            Point p1 = tGame.getScreenCoordFromTile(path.get(i));
            Point p2 = tGame.getScreenCoordFromTile(path.get(i-1));
            g.drawLine(p1.x,p1.y,p2.x,p2.y);
        }
    }
    
    public boolean isInTile(Point p) {
        return false;
    }
}

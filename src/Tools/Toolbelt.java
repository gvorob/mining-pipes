/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Tools.Tool;
import Util.Mouse;
import Util.ImageLoader;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.lang.reflect.Array;
import miningpipes.Game;

/**
 *
 * @author George
 */
public class Toolbelt implements ImageObserver{
    
    Tool[] tools;
    int currTool;
    
    public Toolbelt(){
        tools = new Tool[0];
        currTool = 0;
    }
    
    public boolean isValidTool(int i)
    {
        return (i<Array.getLength(tools)&&i>=0);
    }
    
    public void setTool(int i)
    {
        if(isValidTool(i))
        {currTool = i;}
    }
    
    public void add(Tool t)
    {
        
        Tool[] tempList = new Tool[Array.getLength(tools)+1];
        
        for(int i=0;i<Array.getLength(tools);i++)
        {
            tempList[i]=tools[i];
        }
        tempList[Array.getLength(tempList)-1]=t;
        tools = tempList;
    }
    
    public void update(Mouse tMouse, Game tGame)
    {
        if(tGame.getTile(tGame.tileFromMouseCoord(tMouse.get()))!=null)
        tools[currTool].update(tMouse,tGame);        
    }
    
    public void lClick(Point p,boolean left, boolean down, Game tGame)
    {
        if(tGame.getTile(tGame.tileFromMouseCoord(p))!=null)
        tools[currTool].lClick(p,left,down,tGame);
    }
    
    public void draw(BufferedImage im)
    {
        try{Graphics g = im.getGraphics();
        g.drawRect(10,300,100,100);
        g.drawImage(tools[currTool].icon,20,310,this);
        g.drawString(tools[currTool].name, 20, 350);}
        catch(Exception e){}
    }
    
    public void loadContent(ImageLoader im)
    {
        for(int i = 0;i<Array.getLength(tools);i++)
        {
            tools[i].loadContent(im);
        }
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}

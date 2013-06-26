/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Tools.Tool;
import Util.Mouse;
import Util.ImageLoader;
import java.awt.Point;
import miningpipes.Game;
import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public class RaiseTool extends Tool {
    
    private final float raiseSpeed = 30;
    
    public RaiseTool()
    {
        super();
        name = "Raise";
        
    }
    
    public void loadContent(ImageLoader im)
    {    
        icon = im.loadImage(ImageLoader.TOOLS, 0, 0,2,2);
    }
    
    
    public void update(Mouse tMouse, Game tGame)
    {
        if(tMouse.getL())
        {
            tGame.getTile(tGame.tileFromMouseCoord(tMouse.get())).raise(raiseSpeed*Game.timePerTick());
        }
    }
    
    public void lClick(Point p, boolean left, boolean down,Game tGame)
    {
    }
}

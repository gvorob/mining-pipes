/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Util.Mouse;
import Util.ImageLoader;
import java.awt.Point;
import Structures.CableStruct;
import miningpipes.Game;

/**
 *
 * @author George
 */
public class CableTool extends Tool {
    
    public CableTool()
    {
        super();
        name = "Cable";
        
    }
    
    public void update(Mouse tMouse, Game tGame)
    {
        if(tMouse.getL())
        {
            if (CableStruct.canPlace(tGame,tMouse))
            {
                Point p = tMouse.get();
                tGame.addStructure(new CableStruct(tGame.tileFromMouseCoord(p)));
            }
        }
    }
    
    public void loadContent(ImageLoader im)
    {    
        icon = im.loadImage(ImageLoader.TOOLS, 6, 0, 2, 2);
    }
    
    public void lClick(Mouse tMouse,Game tGame)
    {
        
    }
}

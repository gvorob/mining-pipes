/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Util.Mouse;
import Util.ImageLoader;
import java.awt.Point;
import miningpipes.Game;
import Structures.TeslaStruct;

/**
 *
 * @author George
 */
public class TeslaTool extends Tool {
    
    public TeslaTool()
    {
        super();
        name = "Tesla Coil";
        
    }
    
    public void loadContent(ImageLoader im)
    {    
        icon = im.loadImage(ImageLoader.TOOLS, 2, 2, 2, 2);
    }
    
    public void update(Mouse tMouse, Game tGame)
    {
        if(tMouse.getL())
        {
            if (TeslaStruct.canPlace(tGame,tMouse))
            {
                Point p = tMouse.get();
                tGame.addStructure(new TeslaStruct(tGame.tileFromMouseCoord(p),200));
            }
        }
    }
    
    public void lClick(Mouse tMouse,Game tGame)
    {
        
    }
}

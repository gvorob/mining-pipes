/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Util.Mouse;
import Util.ImageLoader;
import java.awt.Point;
import miningpipes.Game;
import Structures.GeneratorStruct;

/**
 *
 * @author George
 */
public class EnergyTool extends Tool {
    
    public EnergyTool()
    {
        super();
        name = "Generator";
        
    }
    
    public void loadContent(ImageLoader im)
    {    
        icon = im.loadImage(ImageLoader.TOOLS, 4, 0, 2, 2);
    }
    
    public void update(Mouse tMouse, Game tGame)
    {
        if(tMouse.getL())
        {
            if (GeneratorStruct.canPlace(tGame,tMouse.get()))
            {
                Point p = tMouse.get();
                tGame.addStructure(new GeneratorStruct(tGame.tileFromMouseCoord(p)));
            }
        }
    }
    
    public void lClick(Point p, boolean left, boolean down,Game tGame)
    {
    }
}

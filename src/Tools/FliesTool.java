/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Util.Mouse;
import Entities.FliesEntity;
import Util.ImageLoader;
import java.awt.Point;
import miningpipes.Game;
import Util.Vector2;

/**
 *
 * @author George
 */
public class FliesTool extends Tool {
    
    
    public FliesTool()
    {
        super();
        name = "Spawn Flies";
        
    }
    
    public void loadContent(ImageLoader im)
    {    
        icon = im.loadImage(ImageLoader.TOOLS, 0, 2, 2, 2);
    }
    
    public void update(Mouse tMouse,Game tGame){}
    public void lClick(Mouse tMouse,Game tGame)
    {
        Point p =tMouse.get();
        Vector2 v = new Vector2(p.x,p.y);
        Vector2 tV = tGame.gridCoordFroomScreenCoord(v);
        tGame.addEntity(new FliesEntity(tV));
    }
}

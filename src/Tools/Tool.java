/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Util.Mouse;
import Util.ImageLoader;
import java.awt.Image;
import java.awt.Point;
import miningpipes.Game;

/**
 *
 * @author George
 */
public abstract class Tool {
    
    public Image icon;
    public String name;
    
    public Tool(){
        
    }
    
    public void lClick(Point p,boolean left,boolean down,Game tGame)
    {
        
    }
    
    public void update(Mouse tMouse,Game tGame){}
    
    public void loadContent(ImageLoader im)
    {
        
    }
}

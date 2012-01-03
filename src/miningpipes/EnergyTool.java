/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

/**
 *
 * @author George
 */
public class EnergyTool extends Tool {
    
    private final float raiseSpeed = 10;
    
    public EnergyTool()
    {
        super();
        name = "Generator";
        
    }
    
    public void loadContent(ImageLoader im)
    {    
        icon = im.loadImage(ImageLoader.TOOLS, 4, 0, 2, 2);
    }
    
    public void lClick(Mouse tMouse,Game tGame)
    {
        
    }
}

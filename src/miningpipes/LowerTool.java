/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

/**
 *
 * @author George
 */
public class LowerTool extends Tool {
    
    private final float lowerSpeed = -10;
    
    public LowerTool()
    {
        super();
        name = "Lower";
        
    }
    
    public void loadContent(ImageLoader im)
    {    
        icon = im.loadImage(ImageLoader.TOOLS, 2, 0,2,2);
    }
    
    public void lClick(Mouse tMouse,Game tGame)
    {
        tGame.getTile(tGame.tileFromMouseCoord(tMouse.get())).raise(lowerSpeed*((float)MiningPipes.updateTime/1000));
    }
}

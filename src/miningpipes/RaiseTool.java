/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

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
    
    public void lClick(Mouse tMouse,Game tGame)
    {
        tGame.getTile(tGame.tileFromMouseCoord(tMouse.get())).raise(raiseSpeed*((float)MiningPipes.updateTime/1000));
    }
}

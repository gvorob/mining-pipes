/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author George
 */
public class Game {
    public final int gridWidth = 100;
    public final int gridHeight = 100;
    public final float scaleFactor = 3;
    public final int windowWidth = 1000;
    public final int windowHeight = 600;
    public final int tileWidth = 8;
    public final int panSpeed = 10;
       

        

        Grid tileGrid;
        Vector2 viewWindow;


        protected void Initialize()
        {
            viewWindow = Vector2.Zero;

            
        }

        protected void LoadContent()
        {
            
            tileGrid = new Grid(gridWidth, gridHeight, tileWidth, scaleFactor, windowWidth, windowHeight);
        }

      
        protected void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        private Vector2 moveViewWindow(){
            Vector2 moveVec=Vector2.Zero;
            
            if (MiningPipes.thisKeyboard.getKey(KeyEvent.VK_W))
            {
                moveVec.add(0, -1f);
            }
            if (MiningPipes.thisKeyboard.getKey(KeyEvent.VK_S))
            {
                moveVec.add(0, 1f);
            }
            if (MiningPipes.thisKeyboard.getKey(KeyEvent.VK_A))
            {
                moveVec.add(-1f, 0);
            }
            if (MiningPipes.thisKeyboard.getKey(KeyEvent.VK_D))
            {
                moveVec.add(1f, 0);
            }
            if (moveVec.length() != 0)
            {
                moveVec.times((panSpeed*((float)MiningPipes.updateTime / 1000))/moveVec.length());
            }
            return moveVec;

           }
        
        protected void Update()
        {
            
            
            if (MiningPipes.thisMouse.getL())
            {
                Point mouseTile=tileGrid.tileFromScreenCoord(MiningPipes.thisMouse.get(), viewWindow);
                tileGrid.changeBlock(mouseTile, new Tile(5));
            }

            viewWindow.add(moveViewWindow());
            
        }

        
        protected void Draw(Image im)
        {
            tileGrid.Draw(im,viewWindow);
            
        }
}

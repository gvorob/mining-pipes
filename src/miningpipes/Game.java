/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class Game implements ImageObserver{
    public final int gridWidth = 500;
    public final int gridHeight = 500;
    public final float scaleFactor = 3;
    
    public final int tileWidth = 8;
    public final int panSpeed = 20;
    public final Point viewSize = new Point(1000,600);
       

    protected final int viewWidth = 1000;
    protected final int viewHeight = 600;
        
        Toolbelt tBelt;
        Grid tileGrid;
        Vector2 viewWindow;
        BufferedImage currentState;

        public Game(){
            Initialize();
            LoadContent(new ImageLoader());
        }
        
        protected void Initialize()
        {
            viewWindow = Vector2.Zero();
            tileGrid = new Grid(gridWidth, gridHeight);
            currentState = new BufferedImage(viewSize.x,viewSize.y,BufferedImage.TYPE_4BYTE_ABGR);
            tBelt = new Toolbelt();
            tBelt.add(new RaiseTool());
            tBelt.add(new LowerTool());
            tBelt.add(new EnergyTool());
            tBelt.setTool(0);
        }
        
        public void keyPressed(KeyEvent e)
        {
            
            if (Character.isDigit(e.getKeyChar()))
            {
                //System.out.println(e.getKeyCode()-48);
                tBelt.setTool(e.getKeyCode()-48);
            }
        }

        protected void LoadContent(ImageLoader im)
        {
            Tile.loadContent(im);
            tBelt.loadContent(im);
        }

        private Vector2 moveViewWindow(){
            Vector2 moveVec=Vector2.Zero();
            
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
        
        public void lClick(Mouse tMouse)
        {
            
        }
        
        public void Update()
        {
            
            
            if (MiningPipes.thisMouse.getL())
            {
                //Point mouseTile=tileGrid.tileFromScreenCoord(MiningPipes.thisMouse.get(), viewWindow);
                //tileGrid.changeBlock(mouseTile, new Tile(5));
                tBelt.lClick(MiningPipes.thisMouse, this);
            }
            
            
            viewWindow.add(moveViewWindow().x,moveViewWindow().y);
            
            Draw(currentState);
        }

        
        
        protected void Draw(BufferedImage im)
        {
            tileGrid.Draw(im,viewWindow,viewWidth,viewHeight,scaleFactor,(int)(tileWidth*scaleFactor));
            tBelt.draw(im);
        }

        public Tile getTile(Point p)
        {
            return tileGrid.getTile(p);
        }
        
        public Point tileFromMouseCoord(Point p)
        {
            return tileGrid.tileFromScreenCoord(p,viewWindow,(int)(tileWidth*scaleFactor));
        }
        
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}

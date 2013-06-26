/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import Entities.Entity;
import Structures.GeneratorStruct;
import Structures.TeslaStruct;
import Structures.Structure;
import Structures.CableStruct;
import Tools.RaiseTool;
import Util.Vector2;
import Tools.FliesTool;
import Tools.Toolbelt;
import Tools.LowerTool;
import Tools.TeslaTool;
import Tools.EnergyTool;
import Tools.CableTool;
import Util.Mouse;
import Entities.FliesEntity;
import Power.PowerNet;
import Util.BoundingBox;
import Util.ImageLoader;
import Util.Screen;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.rmi.CORBA.UtilDelegate;
import sun.text.normalizer.UCharacter;

/**
 *
 * @author George
 */
public final class Game implements ImageObserver,Util.MouseEventListener,Util.KeyEventListener,Util.TimerListener{
    public final int gridWidth = 100;
    public final int gridHeight = 100;
    public final float scaleFactor = 3;
    
    public final int tileWidth = 8;
    public final int panSpeed = 20;
    public final Point viewSize = new Point(1000,600);
       

    protected final int viewWidth = 1000;
    protected final int viewHeight = 600;
        
    Toolbelt tBelt;
    Grid tileGrid;
    Vector2 viewWindow;
    //BufferedImage currentState;
    StructArray structures;
    EntityArray entities;
    Mouse tMouse;
    Screen tScreen;
    PowerNet pNet;

    public Game(Mouse theMouse, Screen theScreen){
        tScreen = theScreen;
        tMouse = theMouse;
        tMouse.addListener(this);
        Initialize();
        LoadContent(new ImageLoader());
    }

    protected void Initialize()
    {
        viewWindow = Vector2.Zero();
        tileGrid = new Grid(gridWidth, gridHeight);
        //currentState = new BufferedImage(viewSize.x,viewSize.y,BufferedImage.TYPE_4BYTE_ABGR);
        tBelt = new Toolbelt();
        tBelt.add(new RaiseTool());
        tBelt.add(new LowerTool());
        tBelt.add(new EnergyTool());
        tBelt.add(new CableTool());
        tBelt.add(new FliesTool());
        tBelt.add(new TeslaTool());
        tBelt.setTool(0);
        structures = new StructArray();
        entities = new EntityArray();
        pNet = new PowerNet();
    }

    public boolean KeyChange(int kcode, boolean down)
    {
        
        if (kcode >= KeyEvent.VK_0 && kcode <= KeyEvent.VK_9)
        {
            tBelt.setTool(kcode - 48);
        }
        return true;
    }

    protected void LoadContent(ImageLoader im)
    {
        Tile.loadContent(im);
        tBelt.loadContent(im);
        GeneratorStruct.loadContent(im);
        CableStruct.loadContent(im);
        FliesEntity.loadContent(im);
        TeslaStruct.loadContent(im);
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

    public boolean mouseClicked(Point p, boolean left, boolean down)
    {
        tBelt.lClick(p,left,down,this);
        return true;
    }
    
    public boolean mouseMoved(int oldX, int oldY, int x, int y, boolean left, boolean right)
    {
        return true;
    }

    public void timerEvent()
    {
        
        tBelt.update(tMouse, this);

        viewWindow.add(moveViewWindow().x,moveViewWindow().y);
        float vH = viewSize.y/(tileWidth*scaleFactor);
        float vW = viewSize.x/(tileWidth*scaleFactor);
        if (viewWindow.x<0)
        {viewWindow.x = 0;}
        if(viewWindow.y<0)
        {viewWindow.y = 0;}
        if(viewWindow.x+vW>gridWidth)
        {viewWindow.x = gridWidth-vW;}
        if(viewWindow.y+vH>gridHeight)
        {viewWindow.y = gridHeight-vH;}
        pNet.update(this);
        structures.update(this);
        entities.update(this);
        Draw(tScreen.buffer);//currentState);
    }

    public static float timePerTick()
    {
        return (float)MiningPipes.updateTime/(float)1000;
    }

    protected void Draw(BufferedImage im)
    {
        Graphics g = im.getGraphics();
        tileGrid.Draw(im,viewWindow,viewWidth,viewHeight,scaleFactor,(int)(tileWidth*scaleFactor));
        tBelt.draw(im);
        structures.draw(im, this);
        entities.draw(im, this);

        //int tP = (int)(tileWidth*scaleFactor);
        //Point p = tileGrid.tileFromScreenCoord(tMouse.get(),viewWindow,tP);
        //Point s = getScreenCoordFromTile(p.x, p.y);
        //g.drawRect(s.x, s.y,tP,tP);
        tScreen.flushBuffer();
    }

    //public Image getCurrentState(){return currentState;}
    public Tile getTile(Point p){return tileGrid.getTile(p);}
    public Point tileFromMouseCoord(Point p){return tileGrid.tileFromScreenCoord(p,viewWindow,(int)(tileWidth*scaleFactor));}
    public Vector2 gridCoordFroomScreenCoord(Vector2 v){return tileGrid.tileFromScreenCoord(v,viewWindow,(int)(tileWidth*scaleFactor));}
    public Point getScreenCoordFromTile(Vector2 v){return tileGrid.getScreenCoordFromTile(v, viewWindow, (int)(tileWidth*scaleFactor));}
    public Point getScreenCoordFromTile(Point p)    {return tileGrid.getScreenCoordFromTile(p, viewWindow, getTilePixels());}        
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){return true;}
    public Structure getStruct(Point p) throws Exception {return structures.getStruct(p);}
    public Attackable[] getAttackablesWithinRadius(Vector2 p, float range){return entities.getAttackablesWithinRadius(p, range);}
    public Attackable[] getStructsWithinRadius(Vector2 pos, float range){return structures.getStructsWithinRadius(pos, range);}
    public void addEntity(Entity e){entities.add(e);}
    public void addStructure(Structure s){structures.add(s);}
    public boolean isObstructed(int x, int y){return structures.isObstructed(x, y);}
    public boolean isObstructed(BoundingBox bounds){return structures.isObstructed(bounds);}
    public Attackable hasAttackable(Point p){return structures.hasAttackable(p);}
    public int getTilePixels(){return (int)(tileWidth*scaleFactor);}
    public PowerNet getNet(){return pNet;}
}

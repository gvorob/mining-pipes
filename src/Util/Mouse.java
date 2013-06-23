/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import miningpipes.Game;



/**
 *
 * @author George
 */
public class Mouse extends MouseInputAdapter{
    private int x;
    private int y;
    private boolean l;
    private boolean r;
    public Game tGame;
    
    public Mouse()
    {
        
    }
    
    public void initGame(Game theGame)
    {
        if(tGame!=null)
        {System.out.println("Game already initialized");
        return;
        }
        
        tGame = theGame;
    }
    
    public void mouseMoved(MouseEvent e){
        x=e.getX();
        y=e.getY();
    }
    
    public void mouseDragged(MouseEvent e){
        mouseMoved(e);
    }
    
    public void checkGameInit()
    {
        if(tGame==null)System.out.println("Game not initialized");        
    }
    
    public void mousePressed(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1)
        {
            l=true;
            checkGameInit();
            tGame.lClick(this);
        }
        if(e.getButton()==MouseEvent.BUTTON3)
            r=true;
        
    }
    
    public void mouseReleased(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1)
            l=false;
        if(e.getButton()==MouseEvent.BUTTON3)
            r=false;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Point get()
    {
        return new Point(x,y);
    }
    
    public boolean getL(){
        return l;
    }
    
    public boolean getR(){
        return r;
    }
}

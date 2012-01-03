/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

/**
 *
 * @author George
 */
public class MiningPipes implements TimerListener{

    /**
     * @param args the command line arguments
     */
    
    
    public static Image cursor;
    public static Mouse thisMouse;
    public static Keyboard thisKeyboard;
    public static final int updateTime = 25;
    public static Timer theTimer;
    public static TimerMiddleman tm;
    public static Screen theScreen;
    public static Game theGame;
    public static final int windowWidth = 1000;
    public static final int windowHeight = 600;
    public static ImageLoader im;
    public static StaticImageObserver imob;
        
    public static void main(String[] args){
        imob = new StaticImageObserver();
        Toolkit t = Toolkit.getDefaultToolkit();
        im = new ImageLoader();
        theScreen = new Screen(windowWidth,windowHeight);
        cursor = t.getImage("src/cursor.png");
        thisMouse = new Mouse();
        thisKeyboard = new Keyboard();
        
        try{theGame = new Game();}
        catch(Exception e){System.out.println("game constructor exception");}
        
        theTimer = new Timer(updateTime);
        
        theScreen.c.addKeyListener(thisKeyboard);
        theScreen.c.addMouseListener(thisMouse);
        theScreen.c.addMouseMotionListener(thisMouse);
        tm = new TimerMiddleman(theGame,theScreen);
        
        theTimer.addListener(tm);
        
        theGame.LoadContent(im);
        
        theTimer.start();
        
        
    }

    public void timerEvent() {
        
    }

}

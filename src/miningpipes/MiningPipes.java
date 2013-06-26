/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import Util.StaticImageObserver;
import Util.Timer;
import Util.TimerListener;
import Util.Screen;
import Util.Keyboard;
import Util.Mouse;
import Util.ImageLoader;
import java.awt.Image;
import java.awt.Toolkit;

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
        thisKeyboard = new Keyboard();
        thisMouse = new Mouse();
        
        theTimer = new Timer(updateTime);
        
        theScreen.c.addKeyListener(thisKeyboard);
        theScreen.c.addMouseListener(thisMouse);
        theScreen.c.addMouseMotionListener(thisMouse);
        theGame = new Game(thisMouse,theScreen);
        thisMouse.addListener(theGame);
        thisKeyboard.addListener(theGame);
        theTimer.addListener(theGame);
        
        theGame.LoadContent(im);
        
        theTimer.start();
        
        
    }

    public void timerEvent() {
        
    }

}

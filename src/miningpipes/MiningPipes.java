/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

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
    public static TimerMiddleman tm;
    public static Screen theScreen;
    public static final int windowWidth = 1000;
    public static final int windowHeight = 600;
        
    public static void main(String[] args) {
        Toolkit t = Toolkit.getDefaultToolkit();
        cursor = t.getImage("cursor.png");
        tm = new TimerMiddleman();
        thisMouse = new Mouse();
        thisKeyboard = new Keyboard();
        theTimer = new Timer(updateTime);
        theTimer.addListener(tm);
        theScreen = new Screen(windowWidth,windowHeight);
        
        
        
        
        
        theTimer.start();
        
        
    }

    public void timerEvent() {
        
    }

}

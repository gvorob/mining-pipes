/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Util.Screen;
import Util.Mouse;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import miningpipes.Game;
import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public class TimerMiddleman implements TimerListener{

    public Game theGame;
    public Screen theScreen;
    BufferedImage currentState;
    
    public TimerMiddleman(Game tGame,Screen tScreen){
        theGame=tGame;
        theScreen = tScreen;
    }
    
    public void timerEvent() {
        theGame.update();
        theScreen.draw(theGame.getCurrentState());
        
    }
    
    public void keyPressed(KeyEvent e)
    {
        MiningPipes.theGame.keyPressed(e);
    }
    
    public void lClick(Mouse tMouse)
    {
        MiningPipes.theGame.lClick(tMouse);
    }
}

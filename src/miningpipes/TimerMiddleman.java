/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        theGame.Update();
        try {
            theScreen.draw(theGame.currentState);
        } catch (IOException ex) {
            Logger.getLogger(TimerMiddleman.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

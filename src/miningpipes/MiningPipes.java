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
public class MiningPipes {

    /**
     * @param args the command line arguments
     */
    
    
    public static Image cursor;
    public static Mouse thisMouse;
    public static Keyboard thisKeyboard;
    public static final int updateTime = 25;
        
    public static void main(String[] args) {
        Toolkit t = Toolkit.getDefaultToolkit();
        cursor = t.getImage("cursor.png");
        thisMouse = new Mouse();
        thisKeyboard = new Keyboard();
        
        
    }
    
    

}

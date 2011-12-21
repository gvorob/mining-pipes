/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

import java.awt.Canvas;
import java.awt.Frame;

/**
 *
 * @author George
 */
public class Screen {
    public Frame f;
    public Canvas c;
    
    public Screen(int x, int y){
        f = new Frame("Hello");
        f.setSize(x, y);
        f.setVisible(true);
        
        
        
        
    }
    
    
}

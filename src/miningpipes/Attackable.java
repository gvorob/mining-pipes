package miningpipes;


import Util.BoundingBox;
import Util.Vector2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */
public interface Attackable {
    
    public BoundingBox getBounds();
    
    public void getAttacked(float i);
    
}

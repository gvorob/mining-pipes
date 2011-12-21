/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miningpipes;

/**
 *
 * @author George
 */
public class Vector2 {
    public float x,y;
    
    public Vector2(float X,float Y){
        x=X;
        y=Y;
    }
   
    public float length()
    {
        return (float)Math.sqrt(x*x+y*y);
    }
    
    public void add(float X, float Y){
        x+=X;
        y+=Y;
    }
    
    public void add(Vector2 vect){
        x+=vect.x;
        y+=vect.y;
    }
    
    public void times(float f){
        x*=f;
        y*=f;
    }
    
    public static Vector2 Zero = new Vector2(0,0);
}

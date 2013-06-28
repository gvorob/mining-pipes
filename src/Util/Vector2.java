/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Point;

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
        x = x + X;
        y = y + Y;
    }
    
    public void add(Vector2 vect){
        x += vect.x;
        y += vect.y;
    }
    
    public void times(float f){
        x*=f;
        y*=f;
    }
    
    public static Vector2 Zero(){
        return new Vector2(0,0);
    }
    
    public boolean equals(Vector2 v)
    {
        return v.x==x&&v.y==y;
    }
    
    public Point getPoint()
    {
        return new Point((int)x,(int)y);
    }
    
    public Vector2 clone()
    {
        return new Vector2(x,y);
    }
    
    public static Vector2 fromPoint(Point p)
    {
        return new Vector2(p.x,p.y);
    }
    
    public static Vector2 vecMult(float coEffish, Vector2 vec)
    {
        Vector2 temp = Zero();
        temp.x = vec.x * coEffish;
        temp.y = vec.y * coEffish;
        return temp;
    }

    public void vecMult(float coEffish)
    {
        x *= coEffish;
        y *= coEffish;
    }

    public void vecAdd(Vector2 b)
    {
        x += b.x;
        y += b.y;
    }

    public static Vector2 vecAdd(Vector2 a, Vector2 b)
    {
        Vector2 temp = Zero();
        temp.x = a.x + b.x;
        temp.y = a.y + b.y;
        return temp;
    }

    public void vecSubt(Vector2 b)
    {
        x -= b.x;
        y -= b.y;
    }

    public static Vector2 vecSubt(Vector2 a, Vector2 b)
    {
        Vector2 temp = Zero();
        temp.x = a.x - b.x;
        temp.y = a.y - b.y;
        return temp;
    }
        
    public void normalize()
    {
        float factor = 1/length();
        factor = length() == 0 ? 0 : factor;
        vecMult(factor);
    }
    
    public String toString()
    {
        return "x:" + String.valueOf(x) + ", y:" + String.valueOf(y);
    }
}

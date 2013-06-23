/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Power;

/**
 *
 * @author George
 */
class Link {
    int a;
    int b; 
    double costToB;
    
    public Link(int A, int B, double cost)
    {
        a = A;
        b = B;
        costToB = cost;
    }
    
    public int getA(){return a;}
    public int getB(){return b;}
    public double getCost(){return costToB;}
}

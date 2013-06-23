/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import miningpipes.MiningPipes;

/**
 *
 * @author George
 */
public class AnimationScheduler {
    
    private int timeElapsed;
    private int delay;
    private int currFrame = 0;
    private int numFrames;
    
    public AnimationScheduler(int length, int del)
    {
        numFrames = length;
        delay = del;
    } 
    
    public void update()
    {
        timeElapsed += MiningPipes.updateTime;
        if(timeElapsed >= delay)
        {
            timeElapsed -= delay;
            currFrame += 1;
            if(currFrame>=numFrames)
                currFrame = 0;
        }
    }
    
    public int getFrame()
    {
        return currFrame;
    }
    
}

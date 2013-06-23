/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Power;

import Structures.EnergyStructure;
import java.util.Arrays;
import miningpipes.Game;

/**
 *
 * @author George
 */
public class PowerNet {
    public static Link[] nullArray = {};
    EnergyStructure[] structs;
    Link[][] links;
    
    public PowerNet()
    {
        structs = new EnergyStructure[0];
        links = new Link[0][];
    }
    
    public int add(EnergyStructure e)
    {
        int index = -1;
        boolean added = false;
        for(int i = 0; i < structs.length && !added; i++)
        {
            if(structs[i] == null)
            {
                structs[i] = e;
                added = true;
                index = i;
            }
        }
        
        if(!added)
        {
            index = structs.length;
            EnergyStructure[] tempStructs = Arrays.copyOf(structs, index+5);
            Link[][] tempLinks = Arrays.copyOf(links, index + 5);
            tempStructs[index] = e;
            for(int i = index; i < tempLinks.length; i++)
            {
                tempLinks[i] = nullArray;
            }
            structs = tempStructs;
            links = tempLinks;
        }
        
        return index;
    }
    
    public EnergyStructure get(int index)
    {
        return structs[index];
    }
    
    public void remove(int index)
    {
        structs[index] = null;
        removeLinks(index);
    }
    
    private void removeLinks(int index)
    {
        links[index] = new Link[0];
        for(int i = 0; i < links.length; i++)
        {
            for(int j = 0; j < links[i].length; j++)
            {
                try{
                    if(links[i][j].getB() == index)
                        links[i][j] =  null;
                }
                catch(NullPointerException e){}
            }
        }
    }
    
    public void update(Game tGame)
    {
        float availEnergy = 0;
        float energyDemand = 0;
        for(int i = 0; i < structs.length; i++)
        {
            if(structs[i]!=null)
            availEnergy += structs[i].surplusEnergy();
        }
        for(int i = 0; i < structs.length; i++)
        {
            if(structs[i]!=null)
            energyDemand += structs[i].neededEnergy(); 
        }
        if(availEnergy>=energyDemand)
        {
            for(int i = 0; i < structs.length; i++)
            {
                if(structs[i]!=null)
                structs[i].receiveEnergy(structs[i].neededEnergy());
            }
        }
        else
        {
            for(int i = 0; i < structs.length; i++)
            {   if(structs[i]!=null)
                structs[i].receiveEnergy(structs[i].neededEnergy()*availEnergy/energyDemand);
            }
        }
            
    }
    
    public boolean calcShortestPath(int a, int b)
    {
        boolean[] visited = new boolean[links.length];        
        
        return pathCalcRecurse(a, b, visited);
        
    }
    
    private boolean pathCalcRecurse(int last, int to, boolean[] visited)
    {
        if(last == to)
            return true;
        visited[last] = true;
        boolean flag = false;
        for(int i = 0; i < links[last].length && !flag; i++)
        {
            int temp = links[last][i].getB();
            if(!visited[temp])
            {
                flag = pathCalcRecurse(temp,to,visited);
            }
        }
        return flag;
    }

    public void addLinks(int a, int[] b)
    {
        for(int i = 0; i < b.length;i++)
        {
            addLink2Way(a,b[i]);
        }
    }
    
    public void addLink2Way(int a, int b)
    {
        addLink(a,b);
        addLink(b,a);
    }
    
    public void addLink(int a, int b)
    {
        boolean linkFound = false;
        for(int i = 0; i < links[a].length && !linkFound;i++)
        {
            linkFound = links[a][i].getB() == b;
        }
        if(!linkFound)
        {
            for(int i = 0; i < links[a].length && !linkFound; i++)
            {
                if(links[a][i] == null)
                {
                    links[a][i] = new Link(a, b, 0);
                    linkFound = true;
                }
            }
        }
        if(!linkFound)
        {
            links[a] = Arrays.copyOf(links[a], links[a].length + 1);
            links[a][links[a].length-1] = new Link(a, b, 0);
        }
    }
}

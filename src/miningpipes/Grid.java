package miningpipes;

import Util.Misc;
import Util.Vector2;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



    public class Grid
    {
        public int gridWidth;
        public int gridHeight;


        protected Tile[][] tileGrid;

        
        public Tile getTile(Point p)
        {
            return getTile(p.x,p.y);
        }

        public Tile getTile(int i, int j)
        {
            if(i>gridWidth-1||i<0|j>gridHeight-1||j<0)
                return null;
            return tileGrid[i][j];
        }
        
        public int getTilePixels(int w, int s)
        {
             return (int)(w * s);
        }
        public Grid(int GridWidth, int GridHeight)
        {
            gridWidth = GridWidth;
            gridHeight = GridHeight;

            tileGrid = new Tile[GridWidth][GridHeight];

            for (int i = 0; i < gridWidth; i++)
            {
                for (int j = 0; j < gridHeight; j++)
                {
                    tileGrid[i][j] = new Tile(10);
                }
            }


        }
        public Boolean tileIsInGrid(int x, int y)
        {
            return !(x >= gridWidth || x < 0 || y >= gridHeight || y < 0);
        }

        public void Draw(BufferedImage g, Vector2 ViewWindow,int vW, int vH,float sF,int tP)
        {
                   
            

            int xOffset = pixelsFromFloat(ViewWindow.x,tP);
            int yOffset = pixelsFromFloat(ViewWindow.y,tP);

            int xTilesOnscreen = (int)Math.ceil((vW + xOffset) / (double)tP);
            int yTilesOnscreen = (int)Math.ceil((vH + yOffset) / (double)tP);
              
            int xStart = Math.max((int)Math.floor(ViewWindow.x), 0);
            int yStart = Math.max((int)Math.floor(ViewWindow.y), 0);

            int gridStartX = 0;
            if (ViewWindow.x < 0)
            {
                xOffset = -1 * (tP - xOffset);
                gridStartX = (int)Math.ceil(ViewWindow.x);
            }
            int gridStartY = 0;
            if (ViewWindow.y < 0)
            {
                yOffset = -1 * (tP - yOffset);
                gridStartY = (int)Math.ceil(ViewWindow.y);
            }

            int xTilesToDraw = (int)Math.min(xTilesOnscreen, gridWidth - xStart);
            int yTilesToDraw = (int)Math.min(yTilesOnscreen, gridHeight - yStart);


            for (int i = xStart; i < xTilesToDraw + xStart; i++)
            {

                for (int j = yStart; j < yTilesToDraw + yStart; j++)
                {

                    drawTile(g, i, j, xOffset + (int)(xStart * tP) + (int)(gridStartX * tP), yOffset + (int)(yStart * tP) + (int)(gridStartY * tP),tP,sF);
                }
            }
            
            
            return;
        }
        
       /* public Point getScreenCoordFromTile(int x, int y,Vector2 ViewWindow,int tP)
        {
            
            int xOffset = pixelsFromFloat(ViewWindow.x,tP);
            int yOffset = pixelsFromFloat(ViewWindow.y,tP);
            int xStart = Math.max((int)Math.floor(ViewWindow.x), 0);
            int yStart = Math.max((int)Math.floor(ViewWindow.y), 0);
            Point p = tileFromScreenCoord(new Point(x,y),ViewWindow,tP);
            return new Point(tP * (p.x-xStart) - xOffset, tP * (p.y - yStart) - yOffset);
            
        }*/
        public Point getScreenCoordFromTile(Vector2 v,Vector2 ViewWindow,int tP)
        {
            return new Point((int)((v.x-ViewWindow.x)*tP),(int)((v.y-ViewWindow.y)*tP));
            
        }
        
        public Point getScreenCoordFromTile(Point p,Vector2 ViewWindow,int tP)
        {
            return new Point((int)((p.x-ViewWindow.x)*tP),(int)((p.y-ViewWindow.y)*tP));
            
        }
        
        public void changeBlock(int x, int y, Tile tile)
        {
            if (tileIsInGrid(x, y))
            {
                tileGrid[x][y] = tile;
            }

        }
        public void changeBlock(Point coords, Tile tile)
        {
            changeBlock(coords.x, coords.y, tile);
        }
        public Point tileFromScreenCoord(Point mouseCoord, Vector2 viewWindow, int tP)
        {
            //Misc.prln("---");
            //Misc.prln(viewWindow.toString());
            Vector2 tileCoord = new Vector2(mouseCoord.x,mouseCoord.y);
            tileCoord.vecMult(1 / (float) tP);
            tileCoord.vecAdd(viewWindow);
            
            Point temp = new Point();
            temp.x = (int)Math.floor(tileCoord.x);
            temp.y = (int)Math.floor(tileCoord.y);
            return temp;
        }
        public Vector2 tileFromScreenCoord(Vector2 coord, Vector2 viewWindow, int tP)
        {
            Vector2 tileCoord = new Vector2(coord.x,coord.y);

            tileCoord.x /= tP;
            tileCoord.x += viewWindow.x;
            tileCoord.y /= tP;
            tileCoord.y += viewWindow.y;
            return tileCoord;
        }
        protected void drawTile(BufferedImage im, int i, int j, int xOffset, int yOffset, int tP,float sF)
        {
            Graphics g = im.getGraphics();
            this.tileGrid[i][j].drawTile(i, j,new Vector2(tP * i - xOffset, tP * j - yOffset),g,sF);


            return;
        }
        protected int pixelsFromFloat(float location, int tP)
        {
            return (int)Math.round(tP * (location - Math.floor(location)));
        }
    }


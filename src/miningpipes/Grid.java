package miningpipes;

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
            Point p = tileFromScreenCoord(MiningPipes.thisMouse.get(),ViewWindow,tP);
            Graphics bg = g.getGraphics();
            bg.drawRect(tP * (p.x-xStart) - xOffset, tP * (p.y - yStart) - yOffset,tP,tP);
            
            return;
        }
        
        public Point getScreenCoordFromTile(int x, int y,Vector2 ViewWindow,int tP)
        {
            
            int xOffset = pixelsFromFloat(ViewWindow.x,tP);
            int yOffset = pixelsFromFloat(ViewWindow.y,tP);
            int xStart = Math.max((int)Math.floor(ViewWindow.x), 0);
            int yStart = Math.max((int)Math.floor(ViewWindow.y), 0);
            Point p = tileFromScreenCoord(new Point(x,y),ViewWindow,tP);
            return new Point(tP * (p.x-xStart) - xOffset, tP * (p.y - yStart) - yOffset);
            
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
        public Point tileFromScreenCoord(Point coord, Vector2 viewWindow, int tP)
        {
            Point tileCoord = new Point();


            coord.x /= tP;
            coord.x += viewWindow.x;
            coord.y /= tP;
            coord.y += viewWindow.y;

            tileCoord.x = (int)Math.floor(coord.x);
            tileCoord.y = (int)Math.floor(coord.y);
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


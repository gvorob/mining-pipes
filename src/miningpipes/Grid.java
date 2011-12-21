package miningpipes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



    public class Grid
    {
        public int gridWidth;
        public int gridHeight;


        protected float scaleFactor;

        protected int viewWidth;
        protected int viewHeight;
        protected int tileWidth;

        protected Tile[][] tileGrid;


        public int getTilePixels()
        {
             return (int)(tileWidth * scaleFactor);
        }
        public Grid(int GridWidth, int GridHeight, int TileWidth, float ScaleFactor, int ViewWidth, int ViewHeight)
        {
            gridWidth = GridWidth;
            gridHeight = GridHeight;
            scaleFactor = ScaleFactor;
            viewWidth = ViewWidth;
            viewHeight = ViewHeight;
            tileWidth = TileWidth;

            tileGrid = new Tile[GridWidth][GridHeight];

            for (int i = 0; i < gridWidth; i++)
            {
                for (int j = 0; j < gridHeight; j++)
                {
                    tileGrid[i][j] = new Tile(1);
                }
            }


        }
        public Boolean tileIsInGrid(int x, int y)
        {
            return !(x >= gridWidth || x < 0 || y >= gridHeight || y < 0);
        }

        public void Draw(Image g, Vector2 ViewWindow)
        {

            int xOffset = pixelsFromFloat(ViewWindow.x);
            int yOffset = pixelsFromFloat(ViewWindow.y);

            int xTilesOnscreen = (int)Math.ceil((viewWidth + xOffset) / (double)getTilePixels());
            int yTilesOnscreen = (int)Math.ceil((viewHeight + yOffset) / (double)getTilePixels());
              
            int xStart = Math.max((int)Math.floor(ViewWindow.x), 0);
            int yStart = Math.max((int)Math.floor(ViewWindow.y), 0);

            int gridStartX = 0;
            if (ViewWindow.x < 0)
            {
                xOffset = -1 * (getTilePixels() - xOffset);
                gridStartX = (int)Math.ceil(ViewWindow.x);
            }
            int gridStartY = 0;
            if (ViewWindow.y < 0)
            {
                yOffset = -1 * (getTilePixels() - yOffset);
                gridStartY = (int)Math.ceil(ViewWindow.y);
            }

            int xTilesToDraw = (int)Math.min(xTilesOnscreen, gridWidth - xStart);
            int yTilesToDraw = (int)Math.min(yTilesOnscreen, gridHeight - yStart);


            for (int i = xStart; i < xTilesToDraw + xStart; i++)
            {

                for (int j = yStart; j < yTilesToDraw + yStart; j++)
                {


                    drawTile(g, i, j, xOffset + (int)(xStart * getTilePixels()) + (int)(gridStartX * getTilePixels()), yOffset + (int)(yStart * getTilePixels()) + (int)(gridStartY * getTilePixels()));
                }
            }
            return;
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
        public Point tileFromScreenCoord(Point coord, Vector2 viewWindow)
        {
            Point tileCoord = new Point();


            coord.x /= getTilePixels();
            coord.x += viewWindow.x;
            coord.y /= getTilePixels();
            coord.y += viewWindow.y;

            tileCoord.x = (int)Math.floor(coord.x);
            tileCoord.y = (int)Math.floor(coord.y);
            return tileCoord;
        }
        protected void drawTile(Image im, int i, int j, int xOffset, int yOffset)
        {
            Graphics g = im.getGraphics();
            this.tileGrid[i][j].drawTile(i, j,new Vector2(getTilePixels() * i - xOffset, getTilePixels() * j - yOffset),g);


            return;
        }
        protected int pixelsFromFloat(float location)
        {
            return (int)Math.round(getTilePixels() * (location - Math.floor(location)));
        }
    }


package com.TEAM_NAME.Fructus;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;

public class Renderer extends ApplicationAdapter {
	
	static double posX = 22;
	static double posY = 20;
	static double dirX = -1;
	static double dirY = 0;
	static double planeX = 0;
	static double planeY = .67f;
	
	static ArrayList<Integer> xBatch = new ArrayList<Integer>();
	static ArrayList<Integer> y1Batch = new ArrayList<Integer>();
	static ArrayList<Integer> y2Batch = new ArrayList<Integer>();
	static ArrayList<Integer> lineHeightBatch = new ArrayList<Integer>();
	static ArrayList<Integer> textureXBatch = new ArrayList<Integer>();
	static ArrayList<Integer> selectTexture = new ArrayList<Integer>();

	public void render(){		
		int drawStart;
		int drawEnd;
		float cameraX = -2;
		double rayPosX;
		double rayPosY;
	    double rayDirX;
	    double rayDirY;
	    double wallX; //Where was the wall hit?

	    float sideDistX;
	    float sideDistY;
		int mapX;
		int mapY;
		
	    int stepX;
	    int stepY;

	    boolean hit; 
		int side = 0;
		float deltaDistX;
	    float deltaDistY;
	    float perpWallDist;
	    
		for(int x=0; x<GameMain.screenWidth; x++ ){
			 cameraX = 2 * x / (float)GameMain.screenWidth - 1;
			 rayPosX = posX;
			 rayPosY = posY;
		     rayDirX = dirX + planeX * cameraX;
		     rayDirY = dirY + planeY * cameraX;
		     
		     //Map Coordinates
	         mapX = (int)rayPosX;
	         mapY = (int)rayPosY;
	
	         //Rays that represent distance between next x or y
	         deltaDistX = (float) Math.sqrt(1+ (rayDirY * rayDirY) / (rayDirX * rayDirX));
	         deltaDistY = (float) Math.sqrt(1+ (rayDirX * rayDirX) / (rayDirY * rayDirY));

	         hit = false; 
	      
		      if (rayDirX < 0){
		        stepX = -1;
		        sideDistX = (float) ((rayPosX - mapX) * deltaDistX);
		      }
		      else{
		        stepX = 1;
		        sideDistX = (float) ((mapX + 1f - rayPosX) * deltaDistX);
		      }
		      if (rayDirY < 0){
		        stepY = -1;
		        sideDistY = (float) ((rayPosY - mapY) * deltaDistY);
		      }
		      else{
		        stepY = 1;
		        sideDistY = (float) ((mapY + 1f - rayPosY) * deltaDistY);
		      }
	      
	      while (!hit){
	        if (sideDistX < sideDistY){
	          sideDistX += deltaDistX;
	          mapX += stepX;
	          side = 0;
	        }
	        else{
	          sideDistY += deltaDistY;
	          mapY += stepY;
	          side = 1;
	        	
	        }
	        //Check if the ray has hit a wall
	        if (MapChunk.map[mapX][mapY] > 0) 
	        	hit = true;
	      } 
		
	      if (side == 0)
	    	  perpWallDist = (float) Math.abs((mapX - rayPosX + (1 - stepX) / 2) / rayDirX);
	      else
	    	  perpWallDist = (float) Math.abs((mapY - rayPosY + (1 - stepY) / 2) / rayDirY);
	      
	      //Calculates the height of the line to draw on screen
	      int lineHeight = (int) Math.abs((int)GameMain.screenHeight / perpWallDist);
	      
	       
	      //calculates the start and end pixel to fill in current render stripe
	      drawStart = -lineHeight / 2 + GameMain.screenHeight / 2;
	      
	      if(drawStart < 0)drawStart = 0;
	      	drawEnd = lineHeight / 2 + GameMain.screenHeight / 2;
	      	
	      if(drawEnd >= GameMain.screenHeight)
	    	drawEnd = GameMain.screenHeight - 1;
	      
	      if (side == 1) wallX = rayPosX + ((mapY - rayPosY + (1 - stepY) / 2) / rayDirY) * rayDirX;
	      else       wallX = rayPosY + ((mapX - rayPosX + (1 - stepX) / 2) / rayDirX) * rayDirY;
	      wallX -= Math.floor((wallX));
	       
	      //x coordinate on the texture
	      int texX = (int)wallX * 64;
	      if(side == 0 && rayDirX > 0) texX = 64 - texX - 1;
	      if(side == 1 && rayDirY < 0) texX = 64 - texX - 1;
	      
	      //Add all values to the batch now
	      xBatch.add(x);
	      y1Batch.add(drawStart);
	      y2Batch.add(drawEnd);
	      lineHeightBatch.add(lineHeight);
	      textureXBatch.add(texX);
	      selectTexture.add(MapChunk.map[mapX][mapY]);
	      
		}
	}
}


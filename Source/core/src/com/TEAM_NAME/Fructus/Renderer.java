package com.TEAM_NAME.Fructus;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Renderer extends ApplicationAdapter {
	
	double posX = 22;
	double posY = 20;
	double dirX = -1;
	double dirY = 0;
	double planeX = 0;
	double planeY = 1.5f;
	double time = 0;
	double oldTime = 0;
	int drawStart;
	int drawEnd;
	Color color;
	ShapeRenderer shape = new ShapeRenderer();
	double cameraX = 2;
	double rayPosX;
	double rayPosY;
    double rayDirX;
    double rayDirY;
    double sideDistX;
    double sideDistY;
	int mapX;
	int mapY;
	
    int stepX;
    int stepY;

    int hit; 
    int side; 
	
	double deltaDistX;
    double deltaDistY;
    double perpWallDist;
    
    ArrayList<Integer> xBatch = new ArrayList<Integer>();
    ArrayList<Integer> y1Batch = new ArrayList<Integer>();
    ArrayList<Integer> y2Batch = new ArrayList<Integer>();
    ArrayList<Color> colorBatch = new ArrayList<Color>();

	public void render(){
		for(int x=0; x<GameMain.screenWidth; x++){
			 cameraX = 2 * x / (double)GameMain.screenWidth - 1;
			 rayPosX = posX;
			 rayPosY = posY;
		     rayDirX = dirX + planeX * cameraX;
		     rayDirY = dirY + planeY * cameraX;
		    
		    //which box of the map we're in  
	         mapX = (int)rayPosX;
	         mapY = (int)rayPosY;
	
	         //length of ray from one x or y-side to next x or y-side
	         deltaDistX = Math.sqrt(1 + (rayDirY * rayDirY) / (rayDirX * rayDirX));
	         deltaDistY = Math.sqrt(1 + (rayDirX * rayDirX) / (rayDirY * rayDirY));

	         hit = 0; //was there a wall hit?
	      
	         //calculate step and initial sideDist
		      if (rayDirX < 0){
		        stepX = -1;
		        sideDistX = (rayPosX - mapX) * deltaDistX;
		      }
		      else{
		        stepX = 1;
		        sideDistX = (mapX + 1.0 - rayPosX) * deltaDistX;
		      }
		      if (rayDirY < 0){
		        stepY = -1;
		        sideDistY = (rayPosY - mapY) * deltaDistY;
		      }
		      else{
		        stepY = 1;
		        sideDistY = (mapY + 1.0 - rayPosY) * deltaDistY;
		      }
	      
	      //perform DDA
	      while (hit == 0){
	        //jump to next map square, OR in x-direction, OR in y-direction
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
	        //Check if ray has hit a wall
	        if (MapChunk.map[mapX][mapY] > 0) 
	        	hit = 1;
	      } 
		
	      //Calculate distance projected on camera direction (oblique distance will give fisheye effect!)
	      if (side == 0)
	    	  perpWallDist = Math.abs((mapX - rayPosX + (1 - stepX) / 2) / rayDirX);
	      else
	    	  perpWallDist = Math.abs((mapY - rayPosY + (1 - stepY) / 2) / rayDirY);
	      
	      //Calculate height of line to draw on screen
	      int lineHeight = (int) Math.abs((int)GameMain.screenHeight / perpWallDist);
	      
	       
	      //calculate lowest and highest pixel to fill in current stripe
	      drawStart = -lineHeight / 2 + GameMain.screenHeight / 2;
	      
	      if(drawStart < 0)drawStart = 0;
	      	drawEnd = lineHeight / 2 + GameMain.screenHeight / 2;
	      	
	      if(drawEnd >= GameMain.screenHeight)
	    	drawEnd = GameMain.screenHeight - 1;
				      
	      switch(MapChunk.map[mapX][mapY]){
		      case 1:
		    	 color = Color.RED;
		    	 break;
		      case 2: 
		    	  color = Color.OLIVE; 
		    	  break;
		      case 3: 
		    	  color = Color.NAVY; 
		    	  break;
		      case 4: 
		    	  color = Color.GRAY; 
		    	  break;
		      case 7:
		    	  color = Color.PINK;
		    	  break;
		      
		      default: 
		    	  color = Color.YELLOW; 
		    	  break;
	      }

	      //draw the pixels of the stripe as a vertical line	
	      xBatch.add(x);
	      y1Batch.add(drawStart);
	      y2Batch.add(drawEnd);
	      colorBatch.add(color);
	      
		}
	}
}


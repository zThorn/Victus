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
	double posY = 12;
	double dirX = -1;
	double dirY = 0;
	double planeX = 0;
	double planeY = .66;
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


	public void init(){
	
	
	
	}
	
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
	      side = 0; //was a NS or a EW wall hit?
	      
	      //calculate step and initial sideDist
	      if (rayDirX < 0)
	      {
	        stepX = -1;
	        sideDistX = (rayPosX - mapX) * deltaDistX;
	      }
	      else
	      {
	        stepX = 1;
	        sideDistX = (mapX + 1.0 - rayPosX) * deltaDistX;
	      }
	      if (rayDirY < 0)
	      {
	        stepY = -1;
	        sideDistY = (rayPosY - mapY) * deltaDistY;
	      }
	      else
	      {
	        stepY = 1;
	        sideDistY = (mapY + 1.0 - rayPosY) * deltaDistY;
			
		}
	      
	      //perform DDA
	      while (hit == 0)
	      {
	        //jump to next map square, OR in x-direction, OR in y-direction
	        if (sideDistX < sideDistY)
	        {
	          sideDistX += deltaDistX;
	          mapX += stepX;
	          side = 0;
	        }
	        else
	        {
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
	      if(drawEnd >= GameMain.screenHeight)drawEnd = GameMain.screenHeight - 1;
				      
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
	      default: 
	    	  color = Color.YELLOW; 
	    	  break;
	      }
	      //if (side == 1) {color.mul(.5f);}

	      //draw the pixels of the stripe as a vertical line	
	      xBatch.add(x);
	      y1Batch.add(drawStart);
	      y2Batch.add(drawEnd);
	      colorBatch.add(color);
	      
		}
		double moveSpeed = Gdx.graphics.getDeltaTime()/2;
		double rotSpeed = Gdx.graphics.getDeltaTime()/2;
		
		//Move forwards
		if(Gdx.input.isKeyPressed(Keys.W)){
			if(MapChunk.map[(int)(posX+dirX*moveSpeed)][(int)posY] == 0){
				posX += dirX * moveSpeed;
			}
			if(MapChunk.map[(int)posX][(int)(posY+dirY*moveSpeed)] == 0){
				posY += dirY * moveSpeed;
			}
		}	
		//Move Backwards
		if(Gdx.input.isKeyPressed(Keys.S)){
				if(MapChunk.map[(int)(posX-dirX*moveSpeed)][(int)posY] == 0){
					posX -= dirX * moveSpeed;
				}
				if(MapChunk.map[(int)posX][(int)(posY-dirY*moveSpeed)] == 0){
					posY -= dirY * moveSpeed;
				}
		}	
		
		//Strafe Left
		if(Gdx.input.isKeyPressed(Keys.Q)){
			if(MapChunk.map[(int)(posX-dirX*moveSpeed)][(int)posY] == 0){
				posX -= planeX * moveSpeed;				}
			if(MapChunk.map[(int)posX][(int)(posY-dirY*moveSpeed)] == 0){
				posY -= planeY * moveSpeed;				}
		}
		
		//Strafe Right
		if(Gdx.input.isKeyPressed(Keys.E)){
			if(MapChunk.map[(int)(posX-dirX*moveSpeed)][(int)posY] == 0){
				posX += planeX * moveSpeed;			
				}
			if(MapChunk.map[(int)posX][(int)(posY-dirY*moveSpeed)] == 0){
				posY += planeY * moveSpeed;			
				}
		}	
		
		//Rotate right
		if(Gdx.input.isKeyPressed(Keys.D)){
			  double oldDirX = dirX;
		      dirX = dirX * Math.cos(-rotSpeed) - dirY * Math.sin(-rotSpeed);
		      dirY = oldDirX * Math.sin(-rotSpeed) + dirY * Math.cos(-rotSpeed);
		      double oldPlaneX = planeX;
		      planeX = planeX * Math.cos(-rotSpeed) - planeY * Math.sin(-rotSpeed);
		      planeY = oldPlaneX * Math.sin(-rotSpeed) + planeY * Math.cos(-rotSpeed);
			}	
		
		//Rotate left
		if(Gdx.input.isKeyPressed(Keys.A)){
				  double oldDirX = dirX;
			      dirX = dirX * Math.cos(rotSpeed) - dirY * Math.sin(rotSpeed);
			      dirY = oldDirX * Math.sin(rotSpeed) + dirY * Math.cos(rotSpeed);
			      double oldPlaneX = planeX;
			      planeX = planeX * Math.cos(rotSpeed) - planeY * Math.sin(rotSpeed);
			      planeY = oldPlaneX * Math.sin(rotSpeed) + planeY * Math.cos(rotSpeed);
				}

		
	}
}


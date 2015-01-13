package com.TEAM_NAME.Fructus;

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
	
	
	
	
	public void init(){
	
	
	
	}
	
	public void render(){
		for(int x=0; x<GameMain.screenWidth; x++){
			double cameraX = 2 * x / (double)GameMain.screenWidth - 1;
			double rayPosX = posX;
			double rayPosY = posY;
		    double rayDirX = dirX + planeX * cameraX;
		    double rayDirY = dirY + planeY * cameraX;
		    
		    //which box of the map we're in  
	      int mapX = (int)rayPosX;
	      int mapY = (int)rayPosY;
	       
	      //length of ray from current position to next x or y-side
	      double sideDistX;
	      double sideDistY;
	       
	       //length of ray from one x or y-side to next x or y-side
	      double deltaDistX = Math.sqrt(1 + (rayDirY * rayDirY) / (rayDirX * rayDirX));
	      double deltaDistY = Math.sqrt(1 + (rayDirX * rayDirX) / (rayDirY * rayDirY));
	      double perpWallDist;
	       
	      //what direction to step in x or y-direction (either +1 or -1)
	      int stepX;
	      int stepY;

	      int hit = 0; //was there a wall hit?
	      int side = 0; //was a NS or a EW wall hit?
	      
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
	      case 2: color = Color.GREEN; break;
	      case 3: color = Color.BLUE; break;
	      case 4: color = Color.WHITE; break;
	      default: color = Color.YELLOW; break;
	      }
	      //if (side == 1) {color.mul(.5f);}

	      //draw the pixels of the stripe as a vertical line	
	      RendererUtil.drawLine(shape, color, x, drawStart, drawEnd);
		}
		double moveSpeed = Gdx.graphics.getDeltaTime()*5;
		double rotSpeed = Gdx.graphics.getDeltaTime()*3;
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			if(MapChunk.map[(int)(posX+dirX*moveSpeed)][(int)posY] == 0){
				posX += dirX * moveSpeed;
			}
			if(MapChunk.map[(int)posX][(int)(posY+dirY*moveSpeed)] == 0){
				posY += dirY * moveSpeed;
			}
		}	if(Gdx.input.isKeyPressed(Keys.S)){
				if(MapChunk.map[(int)(posX-dirX*moveSpeed)][(int)posY] >= 1){
					posX -= dirX * moveSpeed;
				}
				if(MapChunk.map[(int)posX][(int)(posY-dirY*moveSpeed)] >= 1){
					posY -= dirY * moveSpeed;
				}
		}	
		if(Gdx.input.isKeyPressed(Keys.D)){
			  double oldDirX = dirX;
		      dirX = dirX * Math.cos(-rotSpeed) - dirY * Math.sin(-rotSpeed);
		      dirY = oldDirX * Math.sin(-rotSpeed) + dirY * Math.cos(-rotSpeed);
		      double oldPlaneX = planeX;
		      planeX = planeX * Math.cos(-rotSpeed) - planeY * Math.sin(-rotSpeed);
		      planeY = oldPlaneX * Math.sin(-rotSpeed) + planeY * Math.cos(-rotSpeed);
			}	
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

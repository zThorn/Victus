package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Controls {

	
	public void update(Renderer r){
		double moveSpeed = Gdx.graphics.getDeltaTime()*5;
		double rotSpeed = Gdx.graphics.getDeltaTime()*3;
		
		//Move forwards
		if(Gdx.input.isKeyPressed(Keys.W)){
			if(MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 0){
				r.posX += r.dirX * moveSpeed;
			}
			if(MapChunk.map[(int)r.posX][(int)(r.posY+r.dirY*moveSpeed)] == 0){
				r.posY += r.dirY * moveSpeed;
			}
		}	
		//Move Backwards
		if(Gdx.input.isKeyPressed(Keys.S)){
				if(MapChunk.map[(int)(r.posX-r.dirX*moveSpeed)][(int)r.posY] == 0){
					r.posX -= r.dirX * moveSpeed;
				}
				if(MapChunk.map[(int)r.posX][(int)(r.posY-r.dirY*moveSpeed)] == 0){
					r.posY -= r.dirY * moveSpeed;
				}
		}	
		
		//Strafe Left
		if(Gdx.input.isKeyPressed(Keys.Q)){
			if(MapChunk.map[(int)(r.posX-r.dirX*moveSpeed)][(int)r.posY] == 0){
				r.posX -= r.planeX * moveSpeed;				}
			if(MapChunk.map[(int)r.posX][(int)(r.posY-r.dirY*moveSpeed)] == 0){
				r.posY -= r.planeY * moveSpeed;				}
		}
		
		//Strafe Right
		if(Gdx.input.isKeyPressed(Keys.E)){
			if(MapChunk.map[(int)(r.posX-r.dirX*moveSpeed)][(int)r.posY] == 0){
				r.posX += r.planeX * moveSpeed;			
				}
			if(MapChunk.map[(int)r.posX][(int)(r.posY-r.dirY*moveSpeed)] == 0){
				r.posY += r.planeY * moveSpeed;			
				}
		}	
		
		//Rotate right
		if(Gdx.input.isKeyPressed(Keys.D)){
			  double oldDirX = r.dirX;
			  r.dirX = r.dirX * Math.cos(-rotSpeed) - r.dirY * Math.sin(-rotSpeed);
			  r.dirY = oldDirX * Math.sin(-rotSpeed) + r.dirY * Math.cos(-rotSpeed);
		      double oldPlaneX = r.planeX;
		      r.planeX = r.planeX * Math.cos(-rotSpeed) - r.planeY * Math.sin(-rotSpeed);
		      r.planeY = oldPlaneX * Math.sin(-rotSpeed) + r.planeY * Math.cos(-rotSpeed);
			}	
		
		//Rotate left
		if(Gdx.input.isKeyPressed(Keys.A)){
				  double oldDirX = r.dirX;
				  r.dirX = r.dirX * Math.cos(rotSpeed) - r.dirY * Math.sin(rotSpeed);
				  r.dirY = oldDirX * Math.sin(rotSpeed) + r.dirY * Math.cos(rotSpeed);
			      double oldPlaneX = r.planeX;
			      r.planeX = r.planeX * Math.cos(rotSpeed) - r.planeY * Math.sin(rotSpeed);
			      r.planeY = oldPlaneX * Math.sin(rotSpeed) + r.planeY * Math.cos(rotSpeed);
				}
	}
}

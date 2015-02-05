package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Controls {

	public int forwardKey = Keys.W;
	public int backKey = Keys.S;
	
	public int rotateLeft = Keys.A;
	public int rotateRight= Keys.D;
	
	public int strafeRight= Keys.E;
	public int strafeLeft= Keys.Q;
	
	
	public void update(Renderer r){
		double moveSpeed = Gdx.graphics.getDeltaTime()*5;
		double rotSpeed = Gdx.graphics.getDeltaTime()*.5;
		
		//Move forwards
		if(Gdx.input.isKeyPressed(forwardKey)){
			if(MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 0 ||
					MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7	){
				r.posX += Math.asin(r.dirX) * moveSpeed;
				System.out.println(r.dirX);
			}
			if(MapChunk.map[(int)r.posX][(int)(r.posY+r.dirY*moveSpeed)] == 0 ||
					MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7){
				r.posY += (Math.asin(r.dirX)*moveSpeed);
				System.out.println(r.dirX);
			}
		}	
		//Move Backwards
		if(Gdx.input.isKeyPressed(backKey)){
				if(MapChunk.map[(int)(r.posX-r.dirX*moveSpeed)][(int)r.posY] == 0||
						MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7){
					r.posX -= r.dirX * moveSpeed;
				}
				if(MapChunk.map[(int)r.posX][(int)(r.posY-r.dirY*moveSpeed)] == 0||
						MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7){
					r.posY -= r.dirY * moveSpeed;
				}
		}	
		
		//Strafe Left
		if(Gdx.input.isKeyPressed(strafeLeft)){
			if(MapChunk.map[(int)(r.posX-r.dirX*moveSpeed)][(int)r.posY] == 0||
					MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7){
				r.posX -= r.planeX * moveSpeed;				}
			if(MapChunk.map[(int)r.posX][(int)(r.posY-r.dirY*moveSpeed)] == 0||
					MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7){
				r.posY -= r.planeY * moveSpeed;				}
		}
		
		//Strafe Right
		if(Gdx.input.isKeyPressed(strafeRight)){
			if(MapChunk.map[(int)(r.posX-r.dirX*moveSpeed)][(int)r.posY] == 0||
					MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7){
				r.posX += r.planeX * moveSpeed;			
				}
			if(MapChunk.map[(int)r.posX][(int)(r.posY-r.dirY*moveSpeed)] == 0||
					MapChunk.map[(int)(r.posX+r.dirX*moveSpeed)][(int)r.posY] == 7){
				r.posY += r.planeY * moveSpeed;			
				}
		}	
		
		//Rotate right
		if(Gdx.input.isKeyPressed(rotateRight)){
			  double oldDirX = r.dirX;
			  r.dirX = r.dirX * Math.cos(-rotSpeed) - r.dirY * Math.sin(-rotSpeed);
			  r.dirY = oldDirX * Math.sin(-rotSpeed) + r.dirY * Math.cos(-rotSpeed);
		      double oldPlaneX = r.planeX;
		      r.planeX = r.planeX * Math.cos(-rotSpeed) - r.planeY * Math.sin(-rotSpeed);
		      r.planeY = oldPlaneX * Math.sin(-rotSpeed) + r.planeY * Math.cos(-rotSpeed);
			}	
		
		//Rotate left
		if(Gdx.input.isKeyPressed(rotateLeft)){
				  double oldDirX = r.dirX;
				  r.dirX = r.dirX * Math.cos(rotSpeed) - r.dirY * Math.sin(rotSpeed);
				  r.dirY = oldDirX * Math.sin(rotSpeed) + r.dirY * Math.cos(rotSpeed);
			      double oldPlaneX = r.planeX;
			      r.planeX = r.planeX * Math.cos(rotSpeed) - r.planeY * Math.sin(rotSpeed);
			      r.planeY = oldPlaneX * Math.sin(rotSpeed) + r.planeY * Math.cos(rotSpeed);
				}
	}
}

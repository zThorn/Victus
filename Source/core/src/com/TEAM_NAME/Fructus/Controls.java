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
	
	
	public void update(){
		double moveSpeed = Gdx.graphics.getDeltaTime()*5;
		double rotSpeed = Gdx.graphics.getDeltaTime()*1.25;
		
		//Move forwards
		if(Gdx.input.isKeyPressed(forwardKey)){
			if(MapChunk.map[(int) Math.round((Renderer.posX+Renderer.dirX*moveSpeed))][(int) Math.round(Renderer.posY)] == 0 ||
					MapChunk.map[(int)Math.round(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Math.round(Renderer.posY)] == 7	){
				Renderer.posX += Renderer.dirX * moveSpeed;
			}
			if(MapChunk.map[(int)Math.round(Renderer.posX)][(int)Math.round(Renderer.posY+Renderer.dirY*moveSpeed)] == 0 ||
					MapChunk.map[(int)Math.round(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Math.round(Renderer.posY)] == 7){
				Renderer.posY += Renderer.dirY*moveSpeed;
			}
		}	
		//Move Backwards
		if(Gdx.input.isKeyPressed(backKey)){
				if(MapChunk.map[(int)(Renderer.posX-Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 0||
						MapChunk.map[(int)(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 7){
					Renderer.posX -= Renderer.dirX * moveSpeed;
				}
				if(MapChunk.map[(int)Renderer.posX][(int)(Renderer.posY-Renderer.dirY*moveSpeed)] == 0||
						MapChunk.map[(int)(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 7){
					Renderer.posY -= Renderer.dirY * moveSpeed;
				}
		}	
		
		//Strafe Left
		if(Gdx.input.isKeyPressed(strafeLeft)){
			if(MapChunk.map[(int)(Renderer.posX-Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 0||
					MapChunk.map[(int)(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 7){
				Renderer.posX -= Renderer.planeX * moveSpeed;				}
			if(MapChunk.map[(int)Renderer.posX][(int)(Renderer.posY-Renderer.dirY*moveSpeed)] == 0||
					MapChunk.map[(int)(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 7){
				Renderer.posY -= Renderer.planeY * moveSpeed;				}
		}
		
		//Strafe Right
		if(Gdx.input.isKeyPressed(strafeRight)){
			if(MapChunk.map[(int)(Renderer.posX-Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 0||
					MapChunk.map[(int)(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 7){
				Renderer.posX += Renderer.planeX * moveSpeed;			
				}
			if(MapChunk.map[(int)Renderer.posX][(int)(Renderer.posY-Renderer.dirY*moveSpeed)] == 0||
					MapChunk.map[(int)(Renderer.posX+Renderer.dirX*moveSpeed)][(int)Renderer.posY] == 7){
				Renderer.posY += Renderer.planeY * moveSpeed;			
				}
		}	
		
		//Rotate right
		if(Gdx.input.isKeyPressed(rotateRight)){
			  double oldDirX = Renderer.dirX;
			  Renderer.dirX = Renderer.dirX * Math.cos(-rotSpeed) - Renderer.dirY * Math.sin(-rotSpeed);
			  Renderer.dirY = oldDirX * Math.sin(-rotSpeed) + Renderer.dirY * Math.cos(-rotSpeed);
		      double oldPlaneX = Renderer.planeX;
		      Renderer.planeX = Renderer.planeX * Math.cos(-rotSpeed) - Renderer.planeY * Math.sin(-rotSpeed);
		      Renderer.planeY = oldPlaneX * Math.sin(-rotSpeed) + Renderer.planeY * Math.cos(-rotSpeed);
			}	
		
		//Rotate left
		if(Gdx.input.isKeyPressed(rotateLeft)){
				  double oldDirX = Renderer.dirX;
				  Renderer.dirX = Renderer.dirX * Math.cos(rotSpeed) - Renderer.dirY * Math.sin(rotSpeed);
				  Renderer.dirY = oldDirX * Math.sin(rotSpeed) + Renderer.dirY * Math.cos(rotSpeed);
			      double oldPlaneX = Renderer.planeX;
			      Renderer.planeX = Renderer.planeX * Math.cos(rotSpeed) - Renderer.planeY * Math.sin(rotSpeed);
			      Renderer.planeY = oldPlaneX * Math.sin(rotSpeed) + Renderer.planeY * Math.cos(rotSpeed);
				}
	}
}

package com.TEAM_NAME.Fructus;


import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

public class GameMain extends Game{
	SpriteBatch batch;
	Texture img1;
	Texture img2;

	static int screenWidth = 960;
	static int screenHeight = 540;
	OrthographicCamera camera;
	Renderer r;
	ShapeRenderer shape;
	Controls c;
	FPSLogger log;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		r = new Renderer();
		c = new Controls();
		log = new FPSLogger();
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		font = new BitmapFont();
		GLProfiler.enable();
		img1 = new Texture(Gdx.files.internal("game_textures/pineapple.png"));
		img2 = new Texture(Gdx.files.internal("game_textures/purple_grape.png"));

	}

	@Override
	public void render () {
	    Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

		r.render();
		c.update(r);
		batch.begin();  
		font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 20, 30);   
		font.draw(batch, "draw calls: "+GLProfiler.drawCalls, 20,45);
		GLProfiler.reset();
		Iterator<Integer> xIt = r.xBatch.iterator();
		Iterator<Integer> y1It = r.y1Batch.iterator();
		Iterator<Integer> y2It = r.y2Batch.iterator();
		Iterator<Integer> temp = r.testBatch.iterator();
		Iterator<Integer> selectedTile = r.selectTexture.iterator();
		int textX = 0;
		while(y2It.hasNext()){
			int tempY = y2It.next();
			int tempY1 = y1It.next();
			int tileNumber = selectedTile.next();
			float height = tempY1 - tempY;
	        //batch.draw(texture, (float)left, (float)wall.top, (float)width, (float)wall.height, (int)textureX, 0, 1, texture.getHeight(), false, true);
			if(tileNumber == 1){
	        batch.draw(img1, (float)xIt.next(), (float)tempY, 3.2f,(float)height, temp.next()+50, 0, 1,64, false, true);
			} else {
		       batch.draw(img2, (float)xIt.next(), (float)tempY, 3.2f,(float)height, temp.next()+50, 0, 1,64, false, true);
			}
	        
		}
		batch.end();

		//Movement
		r.xBatch.clear();
		r.y1Batch.clear();
		r.y2Batch.clear();
		r.selectTexture.clear();
		r.testBatch.clear();


		
		


		}		
}

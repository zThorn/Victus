package com.TEAM_NAME.Fructus;


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

public class GameMain extends Game{
	SpriteBatch batch;
	Texture img;
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
		r = new Renderer();
		c = new Controls();
		log = new FPSLogger();
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		font = new BitmapFont();

	}

	@Override
	public void render () {
	    Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

		r.render();
		c.update(r);
		batch.begin();  
		font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 20, 30);    
		batch.end();
		//Movement
		
		
		
		


		}		
}

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
	Texture img;
	static int screenWidth = 1920;
	static int screenHeight = 1080;
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
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		font = new BitmapFont();
		GLProfiler.enable();

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
		batch.end();
		Iterator<Integer> xIt = r.xBatch.iterator();
		Iterator<Integer> y1It = r.y1Batch.iterator();
		Iterator<Integer> y2It = r.y2Batch.iterator();
		Iterator<Color> colorIt = r.colorBatch.iterator();
		
		shape.begin(ShapeRenderer.ShapeType.Line);
		while(xIt.hasNext()){
			RendererUtil.drawLine(shape, colorIt.next(), xIt.next(), y1It.next(), y2It.next());
		}
		shape.end();
		//Movement
		r.xBatch.clear();
		r.y1Batch.clear();
		r.y2Batch.clear();
		r.colorBatch.clear();


		
		


		}		
}

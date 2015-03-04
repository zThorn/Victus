package com.TEAM_NAME.Fructus;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

public class GameMain implements ApplicationListener{
	SpriteBatch batch;
	

	static int screenWidth = 800;
	static int screenHeight = 600;
	Renderer r;
	Controls c;
	FPSLogger log;
	Player p;
	BitmapFont font;
    Plane floor;
	Walls w;
	
	@Override
	public void create () {
		w = new Walls();
		w.loadTextures();
		w.generateWorld();
		r = new Renderer();
		log = new FPSLogger();
		font = new BitmapFont();
        batch = new SpriteBatch();
		r.create();
        floor = new Plane(r.getPerspectiveCamera());

        p = new Player(r.getPerspectiveCamera());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		GLProfiler.enable();
		Gdx.graphics.setVSync(false);
	}

	@Override
	public void render () {

	    //Renderer -> render
		r.render();
		p.movePlayer();
        floor.render();
        batch.begin();
            RendererUtil.renderDebug(font, batch);
            GLProfiler.reset();
        batch.end();
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}		
}

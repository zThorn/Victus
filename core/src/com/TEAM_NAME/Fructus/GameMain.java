package com.TEAM_NAME.Fructus;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

public class GameMain extends Game{
	SpriteBatch batch;
	Texture img1;
	Texture img2;
	Texture img3;
	Texture img4;

	static int screenWidth = 800;
	static int screenHeight = 600;
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
		font = new BitmapFont();

		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		GLProfiler.enable();
		
		img1 = new Texture(Gdx.files.internal("game_textures/pineapple.png"));
		img2 = new Texture(Gdx.files.internal("game_textures/purple_grape.png"));
		img3 = new Texture(Gdx.files.internal("game_textures/red_apple.png"));
		img4 = new Texture(Gdx.files.internal("game_textures/strawberry.png"));
		Gdx.graphics.setVSync(false);
		

	}

	@Override
	public void render () {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	    //Renderer -> render
		r.render();
		
		//Controls -> update
		c.update();
		
		//Draw
		batch.begin();  
			RendererUtil.renderDebug(font,batch);
			GLProfiler.reset();
			RendererUtil.drawTextures(r,batch,img1,img2,img3,img4);
		batch.end();
		}		
}

package com.TEAM_NAME.Fructus;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
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
	MapChunk m;
    ModelBuilder modelBuilder = new ModelBuilder();

    @Override
	public void create () {
		m = new MapChunk();
		m.makeMap();

		w = new Walls();
		//Loads all of the all textures from a file
		w.loadTextures();
		//Populates the world with cubes representing the world
		r = new Renderer();
		r.create();

		w.generateWorld();
		
		
		log = new FPSLogger();
		font = new BitmapFont();
        batch = new SpriteBatch();

        //I create a box of 1x1x1 in order to automagically calculate the bounding
        //box used for collision detection
        p = new Player(modelBuilder.createBox(1f,1f,1f,new Material(TextureAttribute.createDiffuse(Walls.greenAppleTexture)),
                VertexAttributes.Usage.Position| VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates));
        p.setupCamera(Renderer.getPerspectiveCamera());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		GLProfiler.enable();
	}

	@Override
	public void render () {
	    //Renderer -> render
		p.movePlayer();
		r.render();
		    
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
		
	}		
}

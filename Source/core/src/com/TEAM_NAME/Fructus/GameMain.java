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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameMain extends Game{
	SpriteBatch batch;
	Texture img;
	static int screenWidth = 800;
	static int screenHeight = 600;
	OrthographicCamera camera;
	Renderer r;
	ShapeRenderer shape;
	@Override
	public void create () {
		r = new Renderer();
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void render () {
	    Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

		r.render();
		
		//Movement
		
		
		
		


		}		
}

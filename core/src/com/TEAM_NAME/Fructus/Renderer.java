package com.TEAM_NAME.Fructus;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Renderer implements ApplicationListener {
	ModelBatch modelBatch;
	SpriteBatch batch;
	ModelInstance instance;
	PerspectiveCamera camera;
	Wall wall;
	Model model;
	Environment environment;
	Vector3 pos = new Vector3();
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		camera = new PerspectiveCamera(45,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(20f,0f,100f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1.0f));


	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl20.glCullFace(GL20.GL_BACK);
		Gdx.gl20.glEnable(GL20.GL_DEPTH_TEST);
		Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		//Walls.greenAppleTexture.bind();
		
		for(GameObject ins: Walls.getWalls()){
			if(isVisible(ins,camera)){
                modelBatch.begin(camera);
                modelBatch.render(ins,environment);
                modelBatch.end();
			}
		}
		
	}
	
	public PerspectiveCamera getPerspectiveCamera(){
		return this.camera;
	}
	public boolean isVisible(GameObject i, Camera c){
		 	i.transform.getTranslation(pos);
		    pos.add(i.center);
		    return c.frustum.sphereInFrustum(pos, i.radius);
		
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


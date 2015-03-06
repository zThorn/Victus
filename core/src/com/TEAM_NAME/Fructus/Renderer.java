package com.TEAM_NAME.Fructus;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.PerspectiveCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;

public class Renderer implements ApplicationListener {
	ModelBatch modelBatch;
	SpriteBatch batch;
	ModelInstance instance;
	PerspectiveCamera camera;
	Model model;
	static Environment environment;
	Vector3 pos = new Vector3();
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		camera = new PerspectiveCamera(45,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f,0f,0f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1.0f));
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	public static Environment getEnvironment(){
		return environment;
	}
	@Override
	public void render() {
		Gdx.gl30.glEnable(GL30.GL_TEXTURE_2D);
		Gdx.gl30.glEnable(GL30.GL_BLEND);
		
		Gdx.gl30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl30.glCullFace(GL30.GL_BACK);
		Gdx.gl30.glEnable(GL30.GL_DEPTH_TEST);
		Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
		
		//Walls.greenAppleTexture.bind();
        modelBatch.begin(camera);

        for(GameObject ins: Walls.getWalls()) {
            if (isVisible(ins, camera)) {
                modelBatch.render(ins, environment);

            }
            if(Player.bounds.intersects(ins.getBoundingBox()) ) {
                Player.colliding = true;
                System.out.println("Collided with: "+ins.bounds+ "Player position: "+Player.bounds);
                Player.moveBack();
            } else {
                Player.colliding = false;
                //System.out.println(ins.getBoundingBox().min);
            }
        }
        modelBatch.end();

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


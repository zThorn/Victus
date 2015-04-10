package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;

public class Renderer implements ApplicationListener {
	ModelBatch modelBatch;
	SpriteBatch batch;
	ModelInstance instance;
	static PerspectiveCamera camera;
	Model model;
	static Environment environment;
	Vector3 pos = new Vector3();
    boolean collision = false;
    static int renderedobjects = 0;
    private boolean passMade = false;
    public boolean hitJournal = false;
    String fragmentShader = "";
    String vertexShader;
    ShaderProgram shaderProgram;
    
	@Override
	public void create() {
		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, .2f));
        
        batch = new SpriteBatch();
        modelBatch = new ModelBatch();
		camera = new PerspectiveCamera(67,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		camera.position.set((float)Math.random()*200f, 0f, (float)Math.random()*200);
		boolean generate=true;
		float x;
    	float y;
        while(generate){
        	x = (float)Math.random()*200f;
        	y = (float)Math.random()*200f;
        	
        	if(MapChunk.map[(int)x+1] [(int)camera.position.z+1] < 1 ||
			MapChunk.map[(int)x-1] [(int)camera.position.z+1] < 1 ||
			MapChunk.map[(int)x+1] [(int)camera.position.z-1] < 1 ||
			MapChunk.map[(int)x-1] [(int)camera.position.z-1] < 1){
        		camera.position.set(x, 0f,y);
        		generate=false;
        	}
        	
            
        }

        camera.lookAt(0,0,0);
        camera.near = .1f;
        camera.far = 25f;
        camera.update();
        
	}
	@Override
	public void resize(int width, int height) {		
	}
	
	public static Environment getEnvironment(){
		return environment;
	}
	@Override
	public void render() {

        renderedobjects = 0;

        Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
        Gdx.gl20.glEnable(GL20.GL_CULL_FACE);
        Gdx.gl20.glCullFace(GL20.GL_BACK);
		Gdx.gl20.glEnable(GL20.GL_DEPTH_TEST);
		Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0.13f, 0.13f, 0.13f, 1);

		
		passMade = false;
        modelBatch.begin(camera);
        for(GameObject ins: Walls.getWalls()) {
            if (isVisible(ins, camera)) {
                modelBatch.render(ins, environment);
                renderedobjects++;
                if(ins.getBoundingBox().intersects(Player.bounds) && passMade == false){
                	passMade = true;
                	Player.colliding = true;
                } else if(!passMade && !ins.getBoundingBox().intersects(Player.bounds)){
                	Player.colliding = false;
                }
            }
        }
        
        for(Plane pl: Walls.getPlanes()){
        	modelBatch.render(pl,environment);
        	renderedobjects++;
        }
        
        for(GameObject j: Walls.getJournals()){
            modelBatch.render(j, environment);
            renderedobjects++;
            hitJournal = false;
            
        	if(j.getBoundingBox().intersects(Player.bounds)){
        		hitJournal = true;
        		//Modal code would go here
            }
        }
        modelBatch.end();
    }

	public static PerspectiveCamera getPerspectiveCamera(){
		return camera;
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

	}
	}


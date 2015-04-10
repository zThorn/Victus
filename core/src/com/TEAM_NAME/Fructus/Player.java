package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * Created by zthorn on 2/22/15.
 */
public class Player extends GameObject {
    private static PerspectiveCamera camera;
    private FirstPersonCamera fpcc ;
    private DirectionalLight dirLight;
    private PointLight pLight;
    public static InputMultiplexer inputMultiplexer;
    
    public static boolean colliding = false;
    public static BoundingBox bounds;

    public Player(Model model){
        super(model);
        bounds = new BoundingBox();
		calculateBoundingBox(bounds);
		bounds.getCenter(center);
		bounds.getDimensions(dimensions);
    }

    public void setupCamera(PerspectiveCamera c){
        camera = c;
        fpcc = new FirstPersonCamera(camera);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(fpcc);
        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCursorCatched(true);
        dirLight = new DirectionalLight();
        dirLight.set(new Color(.2f,.2f,.2f,.05f), camera.direction);
        pLight = new PointLight();
        pLight.set(new Color(.1f,.1f,.1f,1),camera.position,30f);
        
        Renderer.environment.add(pLight);
        Renderer.environment.add(dirLight);
    }
    
    public void movePlayer(){
        fpcc.update();
        pLight.position.set(camera.position);
        dirLight.direction.set(camera.direction.x,camera.direction.y+.45f,camera.direction.z);
        bounds.set(new Vector3(camera.position.x-.25f,-1,camera.position.z-.35f),new Vector3(camera.position.x+.25f,1,camera.position.z+.25f));

    }


    public PerspectiveCamera getCamera(){return this.camera;}


}

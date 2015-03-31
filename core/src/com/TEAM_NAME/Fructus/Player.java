package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by zthorn on 2/22/15.
 */
public class Player extends GameObject {
    private static PerspectiveCamera camera;
    private FirstPersonCamera fpcc ;
    private DirectionalLight dirLight;
    private PointLight pLight;
    
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
        Gdx.input.setInputProcessor(fpcc);
        Gdx.input.setCursorCatched(true);
        dirLight = new DirectionalLight();
        dirLight.set(new Color(.8f,.8f,.8f,1), camera.direction);
        pLight = new PointLight();
        pLight.set(new Color(.1f,.1f,.1f,1),camera.position,80f);
        
        Renderer.environment.add(pLight);
        Renderer.environment.add(dirLight);
    }
    
    public void movePlayer(){
        fpcc.update();
        pLight.position.set(camera.position);
        dirLight.direction.set(camera.direction.x,camera.direction.y+.45f,camera.direction.z);
        bounds.set(new Vector3(camera.position.x-.55f,-1,camera.position.z-.55f),new Vector3(camera.position.x+.55f,1,camera.position.z+.55f));

    }


    public PerspectiveCamera getCamera(){return this.camera;}


}

package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * Created by zthorn on 2/22/15.
 */
public class Player extends GameObject {
    private static PerspectiveCamera camera;
    private FirstPersonCamera fpcc ;
    private DirectionalLight dirLight;
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
        Renderer.environment.add(dirLight = new DirectionalLight().set(250,235,125,new Vector3(20,0,1)));
    }
    
    public void movePlayer(){
        fpcc.update();
        dirLight.set(250, 235, 125, camera.direction);
        bounds.set(new Vector3(camera.position.x-.55f,-1,camera.position.z-.55f),new Vector3(camera.position.x+.55f,1,camera.position.z+.55f));
        
        
        // System.out.println(this.transform);


    }


    public PerspectiveCamera getCamera(){return this.camera;}


}

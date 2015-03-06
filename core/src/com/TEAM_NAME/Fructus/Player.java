package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

/**
 * Created by zthorn on 2/22/15.
 */
public class Player extends GameObject {
    private static PerspectiveCamera camera;
    private FirstPersonCamera fpcc ;
    private DirectionalLight dirLight;
    public static boolean colliding = false;
    public static Vector3 movementAmt;

    protected static BoundingBox bounds;

    public Player(Model model){
        super(model);
        bounds = new BoundingBox();
        calculateBoundingBox(bounds);
        bounds.getCenter(center);
        bounds.getDimensions(dimensions);
        bounds.set(new Vector3(0f,0f,0f), new Vector3(2f,1f,1f));

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

        if(colliding){
           // camera.position.sub(FirstPersonCamera.tmp);
            //

        }
        if(FirstPersonCamera.hasMoved) {
            System.out.println("PLAYER: "+bounds);

            dirLight.set(250, 235, 125, camera.direction);
            FirstPersonCamera.tmp.y = 0;
            bounds.set(bounds.min.add(FirstPersonCamera.tmp),bounds.max.add(FirstPersonCamera.tmp));

        }

    }

    public static void moveBack(){
        camera.position.sub(FirstPersonCamera.tmp);
        bounds.set(bounds.min.sub(FirstPersonCamera.tmp),bounds.max.sub(FirstPersonCamera.tmp));

    }
    public PerspectiveCamera getCamera(){return this.camera;}


}

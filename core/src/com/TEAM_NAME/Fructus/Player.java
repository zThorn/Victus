package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by zthorn on 2/22/15.
 */
public class Player {
    private PerspectiveCamera camera;
    private FirstPersonCamera fpcc ;
    private DirectionalLight dirLight;
    public Player(PerspectiveCamera c){
        camera = c;
        fpcc = new FirstPersonCamera(camera);
        fpcc.setVelocity(10f);
        fpcc.setDegreesPerPixel(100f);
        Gdx.input.setInputProcessor(fpcc);
        Gdx.input.setCursorCatched(true);
        Renderer.environment.add(dirLight = new DirectionalLight().set(250,235,125,new Vector3(20,0,1)));

    }
    
    public void movePlayer(){
    	fpcc.update();
    	dirLight.set(250, 235, 125, camera.direction);
    }
    
    public PerspectiveCamera getCamera(){return this.camera;}


}

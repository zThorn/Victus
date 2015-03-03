package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;

/**
 * Created by zthorn on 2/22/15.
 */
public class Player {
    private PerspectiveCamera camera;
    private FirstPersonCameraController fpcc ;
    
    public Player(PerspectiveCamera c){
        camera = c;
        fpcc = new FirstPersonCameraController(camera);
        fpcc.setVelocity(10f);
        //fpcc.setDegreesPerPixel(.5f);
        Gdx.input.setInputProcessor(fpcc);
        //Gdx.input.setCursorCatched(true);
    }
    
    public void movePlayer(){
    	fpcc.update();
    }
    
    public PerspectiveCamera getCamera(){return this.camera;}


}

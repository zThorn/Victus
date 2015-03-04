package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by zthorn on 3/3/2015.
 */
public class FirstPersonCamera extends FirstPersonCameraController {
    private Camera camera;
    private float velocity = 5;
    private float degreesPerPixel = 0.15f;
    private final Vector3 tmp = new Vector3();
    public FirstPersonCamera(Camera camera){
        super(camera);
        this.camera = camera;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY){
        float deltaX = -Gdx.input.getDeltaX() * degreesPerPixel;
        //float deltaY = -Gdx.input.getDeltaY() * degreesPerPixel;
        camera.direction.rotate(camera.up, deltaX);
        tmp.set(camera.direction).crs(camera.up).nor();
       // camera.direction.rotate(tmp, deltaY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        float deltaX = -Gdx.input.getDeltaX() * degreesPerPixel;
        //float deltaY = -Gdx.input.getDeltaY() * degreesPerPixel;
        camera.direction.rotate(camera.up, deltaX);
        tmp.set(camera.direction).crs(camera.up).nor();
        // camera.direction.rotate(tmp, deltaY);
        return true;
    }
}

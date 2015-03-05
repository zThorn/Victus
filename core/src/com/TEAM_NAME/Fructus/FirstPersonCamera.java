package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;

/**
 * Created by zthorn on 3/3/2015.
 */
public class FirstPersonCamera extends FirstPersonCameraController {
    private final Camera camera;
    private final IntIntMap keys = new IntIntMap();
    private int STRAFE_LEFT = Input.Keys.A;
    private int STRAFE_RIGHT = Input.Keys.D;
    private int FORWARD = Input.Keys.W;
    private int BACKWARD = Input.Keys.S;
    private int UP = Input.Keys.Q;
    private int DOWN = Input.Keys.E;
    private int SAVE = Input.Keys.K;
    private int LOAD = Input.Keys.L;
    private float velocity = 5;
    private float degreesPerPixel = 0.5f;
    private final Vector3 tmp = new Vector3();
    public FirstPersonCamera(Camera camera){
        super(camera);
        this.camera = camera;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY){
        float deltaX = -Gdx.input.getDeltaX() * degreesPerPixel;
        float deltaY = -Gdx.input.getDeltaY() * degreesPerPixel;
        camera.direction.rotate(camera.up, deltaX);
        tmp.set(camera.direction).crs(camera.up).nor();
        camera.direction.rotate(tmp, deltaY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        float deltaX = -Gdx.input.getDeltaX() * degreesPerPixel;
        float deltaY = -Gdx.input.getDeltaY() * degreesPerPixel;
        camera.direction.rotate(camera.up, deltaX);
        tmp.set(camera.direction).crs(camera.up).nor();
        camera.direction.rotate(tmp, deltaY);
        return true;
    }
    @Override
    public void update (float deltaTime) {
        super.update(deltaTime);
        camera.position.y = 0;
        camera.update(true);
        if(Gdx.input.isKeyPressed(SAVE)) {
        	save();
        }
        if(Gdx.input.isKeyPressed(LOAD)) {
        	load();
        }
    }
	public void save(){	 
		Preferences prefs = Gdx.app.getPreferences("Fructus_Victus");
		prefs.putFloat("camerax", camera.position.x);
		prefs.putFloat("cameray", camera.position.y);
		prefs.putFloat("cameraz", camera.position.z);
		prefs.flush();
	}
	public void load(){
		Preferences prefs = Gdx.app.getPreferences("Fructus_Victus");
		camera.position.x = prefs.getFloat("camerax");
		camera.position.y = prefs.getFloat("cameray");
		camera.position.z = prefs.getFloat("cameraz");
	}

}

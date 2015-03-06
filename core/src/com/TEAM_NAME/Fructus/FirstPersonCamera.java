package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;

/**
 * Created by zthorn on 3/3/2015.
 */
public class FirstPersonCamera  extends InputAdapter {
    private final Camera camera;
    private final IntIntMap keys = new IntIntMap();
    private int STRAFE_LEFT = Input.Keys.A;
    private int STRAFE_RIGHT = Input.Keys.D;
    private int FORWARD = Input.Keys.W;
    private int BACKWARD = Input.Keys.S;

    public static boolean hasMoved = true;
    private int SAVE = Input.Keys.K;
    private int LOAD = Input.Keys.L;
    private float velocity = 2.5f;
    private float degreesPerPixel = 0.5f;
    private final Vector3 tmp = new Vector3();
    public FirstPersonCamera(Camera camera){
        this.camera = camera;
    }

    @Override
    public boolean keyDown (int keycode) {
        keys.put(keycode, keycode);
        return true;
    }

    @Override
    public boolean keyUp (int keycode) {
        keys.remove(keycode, 0);
        return true;
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

    public void update () {
        update(Gdx.graphics.getDeltaTime());
    }

    public void update (float deltaTime) {
        hasMoved = false;
        tmp.set(Vector3.Zero);

        if (keys.containsKey(FORWARD)) {
            if(MapChunk.map[(int)Math.floor(camera.position.x+camera.direction.x*velocity)][(int)Math.floor(camera.position.z+deltaTime*velocity)] == 0
        || MapChunk.map[(int)Math.floor(camera.position.x+camera.direction.x*velocity)+1][(int)Math.floor(camera.position.z+deltaTime*velocity)+1] == 0
        || MapChunk.map[(int)Math.floor(camera.position.x+camera.direction.x*velocity)+1][(int)Math.floor(camera.position.z+deltaTime*velocity)-1] == 0
        || MapChunk.map[(int)Math.floor(camera.position.x+camera.direction.x*velocity)-1][(int)Math.floor(camera.position.z+deltaTime*velocity)+1] == 0
        || MapChunk.map[(int)Math.floor(camera.position.x+camera.direction.x*velocity)-1][(int)Math.floor(camera.position.z+deltaTime*velocity)-1] == 0){

                tmp.set(camera.direction).nor().scl(deltaTime * velocity);
                camera.position.add(tmp);
            }
        }
        if (keys.containsKey(BACKWARD)) {
            if (MapChunk.map[(int) Math.floor(camera.position.x-camera.direction.x*velocity)][(int)Math.floor(camera.position.z)] == 0) {
                tmp.set(camera.direction).nor().scl(-deltaTime * velocity);
                camera.position.add(tmp);
            }
        }
        if (keys.containsKey(STRAFE_LEFT)) {
            if(MapChunk.map[(int)Math.floor(camera.position.x-camera.direction.x*velocity)][(int)camera.position.z] == 0) {
                tmp.set(camera.direction).crs(camera.up).nor().scl(-deltaTime * velocity);
                camera.position.add(tmp);
            }
        }
        if (keys.containsKey(STRAFE_RIGHT)) {
            if(MapChunk.map[(int)Math.floor(camera.position.x-camera.direction.x*velocity)][(int)camera.position.z] == 0) {
                tmp.set(camera.direction).crs(camera.up).nor().scl(deltaTime * velocity);
                camera.position.add(tmp);
            }
        }
        if(Gdx.input.isKeyPressed(SAVE)) {
            save();
        }
        if(Gdx.input.isKeyPressed(LOAD)) {
            load();
        }

        camera.position.y = 0;

        camera.update(true);

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

package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;

/**
 * Created by zthorn on 3/3/2015.
 */
public class FirstPersonCamera  extends InputAdapter {
	
    private final Camera camera;
    private final IntIntMap keys = new IntIntMap();
   
    public static boolean noclip = false;
    
    private float velocity = 2.5f;
    private float degreesPerPixel = 0.5f;
    
    private int lastMove;
    
    public static final Vector3 tmp = new Vector3();
    
    public FirstPersonCamera(Camera camera){
        this.camera = camera;
    }
    
    public Camera getCamera(){
    	return this.camera;
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
        tmp.set(Vector3.Zero);
        if (keys.containsKey(Controls.forwardKey) && (!Player.colliding || noclip) || (Player.colliding && lastMove == Controls.backKey)) {
                tmp.set(camera.direction).nor().scl(deltaTime * velocity);
                camera.position.add(tmp);
                lastMove = Controls.forwardKey;
        }
        
        if (keys.containsKey(Controls.backKey) && (!Player.colliding || noclip) || (Player.colliding && lastMove == Controls.forwardKey)) {
                tmp.set(camera.direction).nor().scl(-deltaTime * velocity);
                camera.position.add(tmp);  
                lastMove = Controls.backKey;
        }
        if (keys.containsKey(Controls.strafeLeft) && (!Player.colliding || noclip || (Player.colliding && lastMove == Controls.strafeRight))) {
	                tmp.set(camera.direction).crs(camera.up).nor().scl(-deltaTime * velocity);
	                camera.position.add(tmp);
	                lastMove = Controls.strafeLeft;
        }
        if (keys.containsKey(Controls.strafeRight) && (!Player.colliding || noclip || (Player.colliding && lastMove == Controls.strafeLeft))) {
                tmp.set(camera.direction).crs(camera.up).nor().scl(deltaTime * velocity);
                camera.position.add(tmp);  
                lastMove = Controls.strafeRight;
        }
        if(Gdx.input.isKeyPressed(Controls.save)) {
            save();
        }
        if(Gdx.input.isKeyPressed(Controls.load)) {
            load();
        } if(keys.containsKey(Controls.noclip)){
        	noclip = !noclip;
        	System.out.println(noclip);
        }
        
        
        Player.colliding = false;
        //This makes sure that the camera stays locked to a single plane
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

package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
    private int currentDirection;
    private int STRAFE_LEFT = Input.Keys.A;
    private int STRAFE_RIGHT = Input.Keys.D;
    private int FORWARD = Input.Keys.W;
    private int BACKWARD = Input.Keys.S;
    private int UP = Input.Keys.Q;
    private int DOWN = Input.Keys.E;
    private float velocity = 5;
    private float degreesPerPixel = 0.15f;

    public static boolean hasMoved = true;
    public static final Vector3 tmp = new Vector3();
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
            tmp.set(camera.direction).nor().scl(deltaTime * velocity);
            currentDirection = FORWARD;
            if(Player.colliding && currentDirection != FORWARD || !Player.colliding)
                camera.position.add(tmp);
            hasMoved = true;
        }
        if (keys.containsKey(BACKWARD)) {
            tmp.set(camera.direction).nor().scl(-deltaTime * velocity);
            if(Player.colliding && currentDirection != BACKWARD || !Player.colliding)
                camera.position.add(tmp);

            currentDirection = BACKWARD;
            hasMoved = true;

        }
        if (keys.containsKey(STRAFE_LEFT)) {
            tmp.set(camera.direction).crs(camera.up).nor().scl(-deltaTime * velocity);
            if(Player.colliding && currentDirection != STRAFE_LEFT || !Player.colliding)
            camera.position.add(tmp);
            currentDirection = STRAFE_LEFT;
            hasMoved = true;

        }
        if (keys.containsKey(STRAFE_RIGHT)) {
            tmp.set(camera.direction).crs(camera.up).nor().scl(deltaTime * velocity);
            if(Player.colliding && currentDirection != STRAFE_RIGHT || !Player.colliding)
            camera.position.add(tmp);
            currentDirection = STRAFE_RIGHT;
            hasMoved = true;
        }

        camera.position.y = 0;

        camera.update(true);
    }

}

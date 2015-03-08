package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;

public class GameObject extends ModelInstance {
	public final Vector3 center = new Vector3();
    public final Vector3 dimensions = new Vector3();
    public final float radius;
    private final static Vector3 position = new Vector3();

    private BoundingBox bounds;


	public GameObject(Model model) {
		super(model);
        bounds = new BoundingBox();
		calculateBoundingBox(bounds);
		bounds.getCenter(center);
		bounds.getDimensions(dimensions);
		radius = dimensions.len() / 2f;


	}

    public  BoundingBox getBoundingBox(){return bounds;}

    public float intersects(Matrix4 transform, Ray ray) {
        transform.getTranslation(position).add(center);
        if (Intersector.intersectRayBoundsFast(ray, position, dimensions)) {
            final float len = ray.direction.dot(position.x-ray.origin.x, position.y-ray.origin.y, position.z-ray.origin.z);
            return position.dst2(ray.origin.x+ray.direction.x*len, ray.origin.y+ray.direction.y*len, ray.origin.z+ray.direction.z*len);
        }
        return -1f;
    }

}

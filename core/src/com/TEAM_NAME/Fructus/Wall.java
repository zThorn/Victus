package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public class Wall {
	public float centerPositionX, centerPositionY;
	public float width, height;
	public int type;
	public Model model;
	public Rectangle bounds;
	public ModelBuilder modelBuilder;
    public btCollisionShape wallShape;
	public btCollisionObject wallCollisionShape;

	public Wall(float cX, float cY, float h, float w){
		this.centerPositionX = cX;
		this.centerPositionY = cY;
		this.height = h;
		this.width = w;
		
		modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(w, h, .5f, new Material(TextureAttribute.createNormal(Walls.groundTexture)),
				Usage.Position| Usage.Normal);
		
		bounds = new Rectangle(cX - w/2, cY - h / 2, w , h);
        wallShape = new btBoxShape(new Vector3(w/2,h/2,.25f));
        wallCollisionShape = new btCollisionObject();
        wallCollisionShape.setCollisionShape(wallShape);
	}
	

}

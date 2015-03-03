package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Rectangle;

public class Wall {
	public float centerPositionX, centerPositionY;
	public float width, height;
	public int type;
	public Model model;
	public Rectangle bounds;
	public ModelBuilder modelBuilder;
	
	public Wall(float cX, float cY, float h, float w){
		this.centerPositionX = cX;
		this.centerPositionY = cY;
		this.height = h;
		this.width = w;
		
		modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(w, h, .5f, new Material(TextureAttribute.createNormal(Walls.groundTexture)),
				Usage.Position| Usage.Normal);
		
		bounds = new Rectangle(cX - w/2, cY - h / 2, w , h);
	}
	

}

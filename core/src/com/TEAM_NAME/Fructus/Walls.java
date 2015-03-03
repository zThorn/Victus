package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


//Walls is going to act as a manager class for Wall,
//While wall contains data required to figure out the position
//Of where the wall should be rendered, this class will add created
//walls to the array, as well as will handle which textures get loaded
public class Walls {
	static Texture watermelonTexture;
	static Texture redAppleTexture;
	static Texture greenAppleTexture;
	static Texture raspberryTexture;
    static Texture groundTexture;
	ModelBatch modelBatch;
	Model model;
	ModelInstance instance;

    private static Array<ModelInstance> walls = new Array<ModelInstance>();
    
    
    
    public void loadTextures(){
    	raspberryTexture = new Texture(Gdx.files.internal("game_textures/seamless/raspberry.png"));
		watermelonTexture = new Texture(Gdx.files.internal("game_textures/seamless/watermelon.png"));
		redAppleTexture = new Texture(Gdx.files.internal("game_textures/seamless/red_apple.png"));
		greenAppleTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        groundTexture = new Texture(Gdx.files.internal("game_textures/wood.png"));
    }
    
    public static Array<ModelInstance> getWalls(){ return walls;}

    public void generateWorld(){
    	 ModelBuilder modelBuilder = new ModelBuilder();
  		 
    	for(int y=0; y< MapChunk.mapHeight; y++){
    		for(int x=0; x < MapChunk.mapWidth; x++){
    			
    			switch(MapChunk.map[x][y]){
	    			case 1:
	    				model = modelBuilder.createBox(1f, 2f, 1f, new Material(TextureAttribute.createDiffuse(Walls.raspberryTexture)),
	    		  				Usage.Position| Usage.Normal | Usage.TextureCoordinates);
	    		         instance = new ModelInstance(model);
	    		         instance.transform.setTranslation(new Vector3(x,0,y));
	    		         instance.calculateTransforms();
	    		         walls.add(instance);
	    				break;
	    			case 2:
	    				model = modelBuilder.createBox(1f, 2f, 1f, new Material(TextureAttribute.createDiffuse(Walls.watermelonTexture)),
	    		  				Usage.Position| Usage.Normal | Usage.TextureCoordinates);
	    		         instance = new ModelInstance(model);
	    		         instance.transform.setTranslation(new Vector3(x,0,y));
	    		         instance.calculateTransforms();
	    		         walls.add(instance);
	    				break;
	    			case 3:
	    				break;
	    			case 4:
	    				break;
	    			default:
    				
    			}
    		}
    	}
    }
}
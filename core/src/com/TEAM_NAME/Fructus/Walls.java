package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


//Walls is going to act as a manager class for Wall,
//While GameObject contains data required to figure out the position
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
	GameObject instance;

    private static Array<GameObject> walls = new Array<GameObject>();
    private static Array<Plane> planes = new Array<Plane>();

    
    
    
    public void loadTextures(){
    	raspberryTexture = new Texture(Gdx.files.internal("game_textures/seamless/raspberry.png"));
		watermelonTexture = new Texture(Gdx.files.internal("game_textures/seamless/watermelon.png"));
		redAppleTexture = new Texture(Gdx.files.internal("game_textures/seamless/red_apple.png"));
		greenAppleTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        groundTexture = new Texture(Gdx.files.internal("game_textures/wood.png"));
    }
    
    public static Array<GameObject> getWalls(){ return walls;}
    public static Array<Plane> getPlanes(){ return planes; }

    public void generateWorld(){
    	 ModelBuilder modelBuilder = new ModelBuilder();
    	 Plane temp;
         //decaltmp.setPosition(35,-.8f,35);
        // decaltmp.setPosition(35,1, 35);
         
      /* model = modelBuilder.createRect(0f, -10f,0f,
        							   0f,-10f,500f,
        							   500f,-10f,500f,
        							   500f,-10f,0f,
        							   Vector3.X.x,Vector3.Y.y,Vector3.Z.z,new Material(TextureAttribute.createDiffuse(Walls.redAppleTexture)), Usage.Position | Usage.Normal | Usage.TextureCoordinates);*/
    	 //instance = new GameObject(model);
    	//instance.transform.setToRotation(Vector3.Z,180f);
    	
    	 model = modelBuilder.createBox(500, 1, 500, new Material(TextureAttribute.createDiffuse(Walls.redAppleTexture)), Usage.Position | Usage.Normal | Usage.TextureCoordinates);
    	 temp = new Plane(model);
    	 temp.transform.setTranslation(new Vector3(0,-1.5f,0));
    	 planes.add(temp);

    	 model = modelBuilder.createBox(500, 1, 500, new Material(TextureAttribute.createDiffuse(Walls.redAppleTexture)), Usage.Position | Usage.Normal | Usage.TextureCoordinates);
    	 temp = new Plane(model);
    	 temp.transform.setTranslation(new Vector3(0,1f,0));
    	 planes.add(temp);
        for(int y=MapChunk.mapHeight-1; y > 0 ; y--){
    		for(int x=MapChunk.mapWidth-1; x > 0 ; x--){
                switch(MapChunk.map[x][y]){
	    			case 1:
	    				model = modelBuilder.createBox(2f, 2f, 2f, new Material(TextureAttribute.createDiffuse(Walls.redAppleTexture)),
	    		  				Usage.Position| Usage.Normal | Usage.TextureCoordinates);
	    		         instance = new GameObject(model);
	    		         instance.transform.setTranslation(new Vector3(x*2,0,y*2));
	    		         instance.calculateTransforms();
	    		         instance.getBoundingBox().set(instance.getBoundingBox().min.add(x*2,0,y*2),instance.getBoundingBox().max.add(x*2,0,y*2));
                         walls.add(instance);
	    				break;
	    			/*case 2:

                        model = modelBuilder.createBox(1f, 2f, 1f, new Material(TextureAttribute.createDiffuse(Walls.watermelonTexture)),
	    		  				Usage.Position| Usage.Normal | Usage.TextureCoordinates);
	    		         instance = new GameObject(model);
	    		         instance.transform.setTranslation(new Vector3(x,0,y));
	    		         instance.calculateTransforms();

                        walls.add(instance);

                        break;*/
                    case 0:

	    			default:



                }
            }
    	}
    }
}

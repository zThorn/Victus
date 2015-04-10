package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;


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
    static Texture abstractWallTexture;
    static Texture fruitShit2;
    static Texture planeTexture;

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
        abstractWallTexture = new Texture(Gdx.files.internal("abstractWall.png"));
        planeTexture = new Texture(Gdx.files.internal("planeTexture.png"));
    }
    
    public static Array<GameObject> getWalls(){ return walls;}
    public static Array<Plane> getPlanes(){ return planes; }

    public void generateWorld(){
    	 ModelBuilder modelBuilder = new ModelBuilder();
    	 ModelLoader loader = new G3dModelLoader(new JsonReader());

    	 Plane temp;
    	 Walls.planeTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
    	 Material temp2 = new Material(TextureAttribute.createDiffuse(Walls.planeTexture));
    	 
    	 modelBuilder.begin();
    	 MeshPartBuilder mpb = modelBuilder.part("box", GL20.GL_TRIANGLES, Usage.Normal | Usage.TextureCoordinates | Usage.Position, temp2);
    	 mpb.setUVRange(0, 0, 50, 50);
    	 mpb.box(500, 1, 500);
    	 model = modelBuilder.end();
    	 temp = new Plane(model);
    	 temp.transform.setTranslation(new Vector3(0,-1.75f,0));
    	 planes.add(temp);

    	 
    	 modelBuilder.begin();
    	 mpb = modelBuilder.part("box", GL20.GL_TRIANGLES, Usage.Position | Usage.TextureCoordinates | Usage.Normal, temp2);
    	 mpb.setUVRange(0, 0, 50, 50);
    	 mpb.box(500, 1, 500);
    	 model = modelBuilder.end();
    	 temp = new Plane(model);
    	 temp.transform.setTranslation(new Vector3(0,1.25f,0));
    	 planes.add(temp);
    	 
    	
         
        for(int y=MapChunk.mapHeight-1; y > 0 ; y--){
    		for(int x=MapChunk.mapWidth-1; x > 0 ; x--){
                switch(MapChunk.map[x][y]){
	    			case 1:
	    				model = modelBuilder.createBox(2f, 2f, 2f, new Material(TextureAttribute.createDiffuse(Walls.abstractWallTexture)),
	    		  				Usage.Position| Usage.Normal | Usage.TextureCoordinates);
	    		         instance = new GameObject(model);
	    		         instance.transform.setTranslation(new Vector3(x*2,0,y*2));
	    		         instance.calculateTransforms();
	    		         instance.getBoundingBox().set(instance.getBoundingBox().min.add(x*2,0,y*2),instance.getBoundingBox().max.add(x*2,0,y*2));
                         walls.add(instance);
	    				break;
	    			case 2:
	    				 model = loader.loadModel(Gdx.files.internal("convert.g3dj"));
	    		         instance = new GameObject(model);
	    		         instance.transform.setTranslation(new Vector3(x,0f,y));
	    		         System.out.println(x+" : "+y);
	    		         instance.calculateTransforms();
	    		         instance.getBoundingBox().set(instance.getBoundingBox().min.add(-1,0,-1),instance.getBoundingBox().max.add(1,0,1));
	    		         walls.add(instance);

                        break;
                    case 0:
	    			default:
                }
            }
    	}
    }
}

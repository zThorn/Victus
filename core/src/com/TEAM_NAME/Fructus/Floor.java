package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;

/**
 * Created by zthorn on 3/3/2015.
 */
public class Floor {
    private Array<Decal> floorDecal = new Array<Decal>();
    private Array<Decal> ceilDecal = new Array<Decal>();

    private DecalBatch decalBatch;
    public Floor(Camera camera){
        decalBatch = new DecalBatch(new CameraGroupStrategy(camera));
        Decal decaltmp;
        decaltmp = Decal.newDecal(75,75,new TextureRegion(new Texture(Gdx.files.internal("game_textures/test.png"))));
        decaltmp.setPosition(35,-.8f,35);
        decaltmp.setRotation(0, 90, 180);
        floorDecal.add(decaltmp);

        decaltmp = Decal.newDecal(75,75,new TextureRegion(new Texture(Gdx.files.internal("game_textures/test.png"))));
        decaltmp.setPosition(35,1, 35);
        decaltmp.setRotation(0,90,180);
        ceilDecal.add(decaltmp);

       // decal = Decal.newDecal(64,64,new TextureRegion(new Texture(Gdx.files.internal("game_textures/gross1.png"))));

    }

    public void render(){
        Decal decal;
        for(int i=0; i<floorDecal.size;i++) {
            decal = ceilDecal.get(i);
            decalBatch.add(decal);
            decal = floorDecal.get(i);
            decalBatch.add(decal);
        }

        decalBatch.flush();
    }

    public void generateFloor(){
        ModelBuilder modelBuilder = new ModelBuilder();

    }

}

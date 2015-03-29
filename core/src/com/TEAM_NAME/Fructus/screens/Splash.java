package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by christensena on 3/29/2015.
 */
public class Splash implements Screen{
    private Stage stage = new Stage();
    Renderer r;
    Controls c;
    FPSLogger log;
    Player p;
    BitmapFont font;
    Plane floor;
    Walls w;
    MapChunk m;
    SpriteBatch batch;
    ModelBuilder modelBuilder = new ModelBuilder();
    public Splash(){

    }
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    public void show() {
        m = new MapChunk();
        m.makeMap();

        w = new Walls();
        //Loads all of the all textures from a file
        w.loadTextures();
        //Populates the world with cubes representing the world
        w.generateWorld();

        r = new Renderer();
        log = new FPSLogger();
        font = new BitmapFont();
        batch = new SpriteBatch();
        r.create();

        //I create a box of 1x1x1 in order to automagically calculate the bounding
        //box used for collision detection
        p = new Player(modelBuilder.createBox(1f, 1f, 1f, new Material(TextureAttribute.createDiffuse(Walls.greenAppleTexture)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates));
        p.setupCamera(Renderer.getPerspectiveCamera());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        GLProfiler.enable();

        r.render();
        p.movePlayer();

        batch.begin();
        RendererUtil.renderDebug(font, batch);
        GLProfiler.reset();
        batch.end();
    }
    public void resize(int width, int height){

    }
    public void hide(){
        dispose();
    }
    public void pause(){

    }
    public void resume(){

    }
    public void dispose() {
        stage.dispose();
    }

}

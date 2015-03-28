package com.TEAM_NAME.Fructus;

/**
 * Created by christensena on 3/27/2015.
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen {
    private Stage stage = new Stage();
    private Table table = new Table();
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
    private Skin skin = new Skin(Gdx.files.internal("core/assets/skins/menuSkin.json"),
    new TextureAtlas(Gdx.files.internal("core/assets/skins/menuSkin.pack")));
    private TextButton buttonPlay = new TextButton("New Game", skin),
    buttonExit = new TextButton("Exit", skin);
    private Label title = new Label("Fructus Victus", skin);

    public MainMenu(){

    }
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    public void resize(int width, int height){

    }

    public void show(){
        Gdx.input.setCursorCatched(false);
        buttonPlay.addListener(new ClickListener() {
            public void clicked(InputEvent Event, float x, float y) {
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
                hide();
            }
        });
        buttonExit.addListener(new ClickListener(){
            public void clicked(InputEvent Event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.setSkin(skin);
        table.defaults().width(300);
        table.add(title).padBottom(40).padLeft(10).row();
        table.add(buttonPlay).size(230, 40).padBottom(10).padLeft(10).fillX().left().row();
        table.add(buttonExit).size(150, 40).padBottom(10).padLeft(10).fillX().left().row();
        table.left();
        table.setBackground("green_apple");
        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
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
        skin.dispose();
    }

}

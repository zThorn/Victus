package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DialogItem{
	Texture journalTexture;
	SpriteBatch batch;
	public void create(){
		journalTexture =  new Texture(Gdx.files.internal("Scroll_opened.png"));
		batch = new SpriteBatch();
	}
	
	public void show() {
		
	}
	

	
	public void render(BitmapFont font){
		batch.begin();
			batch.draw(journalTexture,80,-20);
			font.draw(batch,Story.storyItems.get(0),150,250);
		batch.end();
	}
	
	public void display(){
	}
	


	
}

package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DialogItem extends ScreenAdapter{

	private Stage stage;
	private Skin skin;
	public void show() {
		Gdx.input.setInputProcessor(stage = new Stage());
		skin = new Skin(Gdx.files.internal("game_textures/skins"));

		ItemDialog dia = new ItemDialog("Journal Entry Discovered!", skin);
		dia.show(stage);
	}

	public static class ItemDialog extends Dialog{
		public ItemDialog(String title, Skin skin){
			super(title,skin);
		}
		{
			text("Item Dialog goes here");
			button("Exit");
		}
		protected void result(Object object){

		}
	}

	public void render(float delta){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}
	
	public void display(){
	}
}

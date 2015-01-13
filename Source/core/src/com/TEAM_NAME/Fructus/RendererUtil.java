package com.TEAM_NAME.Fructus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class RendererUtil {
	

	public static void drawLine(ShapeRenderer shape,Color color, float x, float y1, float y2){

			shape.setColor(color);
			shape.line(x,y1,x,y2);

	}
}

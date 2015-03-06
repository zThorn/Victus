package com.TEAM_NAME.Fructus;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

import javax.xml.soap.Text;

public class RendererUtil {
	

	public static void drawLine(ShapeRenderer shape,Color color, float x, float y1, float y2){
			shape.setColor(color);
			shape.line(x,y1,x,y2);
	}

    public static int convertNormalToDegrees(double x, double y){
        return (int)((Math.atan2(y,x)*180/Math.PI)+180);
    }
	
	public static void renderDebug(BitmapFont font, SpriteBatch batch){
		font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 20, 30);   
		font.draw(batch, "draw calls: "+GLProfiler.drawCalls, 20,45);
        font.draw(batch, "objects drawn: "+Renderer.renderedobjects,20,55);
	}
}

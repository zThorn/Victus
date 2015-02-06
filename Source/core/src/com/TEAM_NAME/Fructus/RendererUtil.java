package com.TEAM_NAME.Fructus;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.profiling.GLProfiler;

public class RendererUtil {
	

	public static void drawLine(ShapeRenderer shape,Color color, float x, float y1, float y2){
			shape.setColor(color);
			shape.line(x,y1,x,y2);
	}
	
	public static void drawTextures(Renderer r, SpriteBatch batch, Texture img1, Texture img2, Texture img3, Texture img4){
		Iterator<Integer> xIt = Renderer.xBatch.iterator();
		Iterator<Integer> y1It = Renderer.y1Batch.iterator();
		Iterator<Integer> y2It = Renderer.y2Batch.iterator();
		Iterator<Integer> textureXBatch = Renderer.textureXBatch.iterator();
		Iterator<Integer> selectedTile = Renderer.selectTexture.iterator();

		while(y2It.hasNext()){
			int tempY = y2It.next();
			int tempY1 = y1It.next();
			int tileNumber = selectedTile.next();
			float height = tempY1 - tempY;
	        //batch.draw(texture, (float)left, (float)wall.top, (float)width, (float)wall.height, (int)textureX, 0, 1, texture.getHeight(), false, true);
			if(tileNumber == 1){
	        batch.draw(img1, (float)xIt.next(), (float)tempY, 3.2f,(float)height, textureXBatch.next()+50, 0, 1,64, false, true);
			} else if(tileNumber == 2) {
		       batch.draw(img2, (float)xIt.next(), (float)tempY, 3.2f,(float)height, textureXBatch.next()+50, 0, 1,64, false, true);
			} else if(tileNumber == 3){
			   batch.draw(img3, (float)xIt.next(), (float)tempY, 3.2f,(float)height, textureXBatch.next()+50, 0, 1,64, false, true);
			} else if(tileNumber == 4){
			   batch.draw(img4, (float)xIt.next(), (float)tempY, 3.2f,(float)height, textureXBatch.next()+50, 0, 1,64, false, true);
			} else{
		        batch.draw(img1, (float)xIt.next(), (float)tempY, 3.2f,(float)height, textureXBatch.next()+50, 0, 1,64, false, true);
			}
	        
		}
		Renderer.xBatch.clear();
		Renderer.y1Batch.clear();
		Renderer.y2Batch.clear();
		Renderer.selectTexture.clear();
		Renderer.textureXBatch.clear();
	}
	
	public static void renderDebug(BitmapFont font, SpriteBatch batch){
		font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 20, 30);   
		font.draw(batch, "draw calls: "+GLProfiler.drawCalls, 20,45);
	}
}

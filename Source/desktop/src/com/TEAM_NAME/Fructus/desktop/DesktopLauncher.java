package com.TEAM_NAME.Fructus.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.TEAM_NAME.Fructus.GameMain;

public class DesktopLauncher {
	static int screenHeight = 540;
	static int screenWidth = 960;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = screenHeight;
		config.width = screenWidth;
		new LwjglApplication((ApplicationListener) new GameMain(), config);
		
	}
}

//For launching the actual game
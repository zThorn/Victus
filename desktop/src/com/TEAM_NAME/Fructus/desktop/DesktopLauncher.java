package com.TEAM_NAME.Fructus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.TEAM_NAME.Fructus.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameMain(), config);
        config.vSyncEnabled = true;
        config.width = 800;
        config.height = 600;
        		
	}
}

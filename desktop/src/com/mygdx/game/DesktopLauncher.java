package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setWindowedMode(1920,1080);
		cfg.setTitle("HelloKitty");
		new Lwjgl3Application(new Drop(), cfg);
	}
}

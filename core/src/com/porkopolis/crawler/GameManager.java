package com.porkopolis.crawler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.porkopolis.crawler.items.Inventory;

public class GameManager {
	private static Game game;
	public static String title = "Corporate Crawler";

	private InventoryManager inventory;

	public static void init(Game game) {
		GameManager.game = game;
	}

	public static void setScreen(Screen screen) {
		game.setScreen(screen);
	}

	public InventoryManager getInventory() {
		return inventory;
	}

}

class InventoryManager {
	Inventory inventory;

	public InventoryManager() {
		inventory = new Inventory();
	}

}

package com.porkopolis.crawler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.porkopolis.crawler.items.Inventory;

public class GameManager {
	public static String title = "Corporate Crawler";

	private static Game game;

	public static InventoryManager inventory;

	public static DungeonManager dungeonManager;

	public static void init(Game game) {
		GameManager.game = game;
		dungeonManager = new DungeonManager();
	}

	public static void setScreen(Screen screen) {
		game.setScreen(screen);
	}

	public static InventoryManager getInventory() {
		return inventory;
	}

}

class InventoryManager {
	Inventory inventory;

	public InventoryManager() {
		inventory = new Inventory();
	}

}

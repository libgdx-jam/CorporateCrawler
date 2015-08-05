package com.porkopolis.crawler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.porkopolis.crawler.items.Inventory;

public class GameManager {
	public static String title = "Corporate Crawler";
	private static World world;

	private static Game game;

	public static InventoryManager inventory;

	public static DungeonManager dungeonManager;

	public static void init(Game game) {
		GameManager.game = game;
		world = new World(new Vector2(0, 0), true);
		dungeonManager = new DungeonManager();
	}

	public static void setScreen(Screen screen) {
		world = new World(new Vector2(0, 0), true);
		game.setScreen(screen);
	}

	public static InventoryManager getInventory() {
		return inventory;
	}

	public static World getWorld() {
		return world;
	}

}

class InventoryManager {
	Inventory inventory;

	public InventoryManager() {
		inventory = new Inventory();
	}

}

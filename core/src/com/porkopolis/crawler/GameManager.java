package com.porkopolis.crawler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.porkopolis.crawler.items.Inventory;
import com.porkopolis.crawler.utils.Room;

public class GameManager {
	private static Game game;
	public static String title = "Corporate Crawler";

	private InventoryManager inventory;

	private Array<Room> rooms = new Array<Room>();

	public static void init(Game game) {
		GameManager.game = game;
	}

	public static void setScreen(Screen screen) {
		game.setScreen(screen);
	}

	public InventoryManager getInventory() {
		return inventory;
	}

	public Array<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Array<Room> rooms) {
		this.rooms = rooms;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public void removeRoom(Room room) {
		rooms.removeValue(room, true);
	}

}

class InventoryManager {
	Inventory inventory;

	public InventoryManager() {
		inventory = new Inventory();
	}

}

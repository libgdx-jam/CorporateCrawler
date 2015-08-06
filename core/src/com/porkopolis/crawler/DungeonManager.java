package com.porkopolis.crawler;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.porkopolis.crawler.utils.Dungeon;
import com.porkopolis.crawler.utils.DungeonGenerator;
import com.porkopolis.crawler.utils.Room;
import com.porkopolis.crawler.utils.Tile;
import com.porkopolis.crawler.utils.TileSet;

public class DungeonManager {
	private static Array<Room> rooms = new Array<Room>();
	public static Dungeon dungeon;
	public static TileSet tileset = new TileSet();

	public DungeonManager() {
	}

	public static Array<Room> getRooms() {
		return rooms;
	}

	public static void setRooms(Array<Room> rooms) {
		DungeonManager.rooms = rooms;
	}

	public static void addRoom(Room room) {
		rooms.add(room);
	}

	public static void removeRoom(Room room) {
		rooms.removeValue(room, true);
	}

	public static Vector2 getFree() throws NullPointerException {
		for (Room room : rooms) {
			for (Tile tile : room.getTiles()) {
				if (dungeon.isFloor(tile.getX(), tile.getY())) {
					if (!tile.isOccupied()) {
						tile.setOccupied(true);
						return new Vector2(tile.getX(), tile.getY());
					}
				}
			}
		}

		throw new NullPointerException("Could not find a free space anywhere!");
	}

}
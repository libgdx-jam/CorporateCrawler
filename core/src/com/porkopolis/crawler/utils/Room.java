package com.porkopolis.crawler.utils;

import com.badlogic.gdx.utils.Array;

public class Room {
	private Array<Tile> tiles;

	public Room(Array<Tile> tiles) {
		this.tiles = tiles;
	}

	public Room() {
		tiles = new Array<Tile>();
	}

	public Array<Tile> getTiles() {
		return tiles;
	}

}

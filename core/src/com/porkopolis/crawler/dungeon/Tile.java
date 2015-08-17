package com.porkopolis.crawler.dungeon;

import com.badlogic.gdx.utils.Array;

public class Tile {

	private int x;
	private int y;

	private Array<TileObject> objects;

	public Tile(int x, int y, Array<TileObject> objects) {
		this(x, y, objects.items);
	}

	public Tile(int x, int y, TileObject... objects) {
		this.x = x;
		this.y = y;
		this.objects = new Array<TileObject>();
		this.objects.items = objects;
	}

	public Tile(int x, int y, int tile) {
		this(x, y, new TileObject(tile));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Array<TileObject> getObjects() {
		return objects;
	}

	public void setObjects(Array<TileObject> objects) {
		this.objects = objects;
	}

	public void addObject(TileObject object) {
		objects.add(object);
	}

	public void removeObject(TileObject object) {
		objects.removeValue(object, true);
	}
}

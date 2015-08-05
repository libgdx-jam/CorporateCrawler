package com.porkopolis.crawler.utils;

public class Tile {

	private int x;
	private int y;

	private int tileset;

	private boolean occupied = false;

	public Tile(int x, int y, int tileset) {
		this.x = x;
		this.y = y;
		this.tileset = tileset;
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

	public int getTileset() {
		return tileset;
	}

	public void setTileset(int tileset) {
		this.tileset = tileset;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

}

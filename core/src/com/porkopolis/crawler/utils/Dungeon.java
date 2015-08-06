package com.porkopolis.crawler.utils;

public class Dungeon {
	private int[] tileLayer;
	private boolean[] collisionLayer;
	private int[] entityLayer;
	private int xSize;
	private int ySize;
	private int objects;

	private int[][] rooms;

	private String tileSet;
	private Tileset t = new Tileset();

	public Dungeon(int xSize, int ySize, int objects, String tileSheet) {
		this.tileSet = tileSheet;

		if (objects < 1)
			this.objects = 10;
		else
			this.objects = objects;

		if (xSize < 3)
			this.xSize = 3;
		else
			this.xSize = xSize;

		if (ySize < 3)
			this.ySize = 3;
		else
			this.ySize = ySize;

		tileLayer = new int[xSize * ySize];
		collisionLayer = new boolean[xSize * ySize];
		entityLayer = new int[xSize * ySize];
		rooms = new int[1000][xSize * ySize];

	}

	public void setTile(int x, int y, int celltype) {
		tileLayer[x + xSize * y] = celltype;
	}

	public int getTile(int x, int y) {
		return tileLayer[x + xSize * y];
	}

	public boolean isFloor(int x, int y) {
		if (getTile(x, y) >= t.FLOOR_1_1 && getTile(x, y) <= t.FLOOR_5_10)
			return true;
		else
			return false;
	}

	public boolean isWall(int x, int y) {
		if (getTile(x, y) > t.TOP_LEFT_INSIDE && getTile(x, y) < t.BOTTOM_WALL_10)
			return true;
		else
			return false;
	}

	public void setCollision(int x, int y, boolean i) {
		collisionLayer[x + xSize * y] = i;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

	public int getObjects() {
		return objects;
	}

	public int[] getTileLayer() {
		return tileLayer;
	}

	public boolean[] getCollisionLayer() {
		return collisionLayer;
	}

	public int[] getEntityLayer() {
		return entityLayer;
	}

	public String getTileSet() {
		return tileSet;
	}

	public Tileset getTitleSet() {
		return t;
	}

}

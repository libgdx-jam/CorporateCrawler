package com.porkopolis.crawler.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Dungeon {
	private int[] tileLayer;
	private boolean[] collisionLayer;
	private int[] entityLayer;
	private int[] freeLayer;
	private int xSize;
	private int ySize;
	private int objects;

	private int[][] rooms;

	private String tileSet;
	private TileSet t = new TileSet();

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
		freeLayer = new int[xSize * ySize];
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
		if (getTile(x, y) > t.TOP_LEFT_INSIDE
				&& getTile(x, y) < t.BOTTOM_WALL_10)
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

	public TileSet getTitleSet() {
		return t;
	}

	public void setFree(int x, int y, boolean isFree) {
		if (isFree)
			freeLayer[x + xSize * y] = 1;
		else
			freeLayer[x + xSize * y] = 0;
	}

	public boolean isFree(int x, int y) {

		if (freeLayer[x + xSize * y] == 1) {
			return true;
		}
		return false;

	}

	/**
	 * This does not take into consideration your circle's origin is in the
	 * center not the bottom left. You may need to offset this.
	 * */
	public Vector2 getRandomFree() {
		int tmpx = MathUtils.random(1, xSize - 1);
		int tmpy = MathUtils.random(1, ySize - 1);
		while (!isFree(tmpx, tmpy)) {
			tmpx = MathUtils.random(1, xSize - 1);
			tmpy = MathUtils.random(1, ySize - 1);
		}

		if (isFree(tmpx, tmpy)) {
			return new Vector2(tmpx, tmpy);
		} else {
			System.out.print("FUCK");
			return new Vector2(tmpx, tmpy);
		}
	}

}

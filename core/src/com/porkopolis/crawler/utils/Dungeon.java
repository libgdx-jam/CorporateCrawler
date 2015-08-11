package com.porkopolis.crawler.utils;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Dungeon {
	private int[] tileLayer;
	private boolean[] collisionLayer;
	private int[] entityLayer;
	private int width;
	private int height;
	private int objects;

	private String tileSet;
	private Tileset t = new Tileset();

	public static ArrayList<Vector2> doors = new ArrayList<Vector2>(100);
	public static ArrayList<Vector2> center = new ArrayList<Vector2>;

	public Dungeon(int xSize, int ySize, int objects, String tileSheet) {
		this.tileSet = tileSheet;

		if (objects < 1)
			this.objects = 10;
		else
			this.objects = objects;

		if (xSize < 3)
			this.width = 3;
		else
			this.width = xSize;

		if (ySize < 3)
			this.height = 3;
		else
			this.height = ySize;

		tileLayer = new int[xSize * ySize];
		collisionLayer = new boolean[xSize * ySize];
		entityLayer = new int[xSize * ySize];

	}

	public void setTile(int x, int y, int celltype) {
		if (coverTo1d(x, y) < width * height) {
			tileLayer[coverTo1d(x, y)] = celltype;
		} else {
			System.out.println("Dungeon.setTile, Array out of bounds: " + coverTo1d(x, y) + " x: " + x + " y: " + y);
		}
	}

	public int coverTo1d(int x, int y) {
		return x + (width * y);
	}

	public boolean isFloor(int x, int y) {
		if ((x > 0 && x < width) && (y > 0 && y < height)) {
			if (getTile(x, y) >= t.FLOOR_1_1 && getTile(x, y) <= t.FLOOR_5_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isWall(int x, int y) {
		if ((x > 0 && x < width) && (y > 0 && y < height)) {
			if (getTile(x, y) >= t.TOP_LEFT_INSIDE && getTile(x, y) <= t.BOTTOM_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isTopWall(int x, int y) {
		if ((x > 0 && x < width) && (y > 0 && y < height)) {
			if (getTile(x, y) >= t.TOP_WALL_1 && getTile(x, y) <= t.TOP_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isBottomWall(int x, int y) {
		if ((x > 0 && x < width) && (y > 0 && y < height)) {
			if (getTile(x, y) >= t.BOTTOM_WALL_1 && getTile(x, y) <= t.BOTTOM_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isLeftWall(int x, int y) {
		if ((x > 0 && x < width) && (y > 0 && y < height)) {
			if (getTile(x, y) >= t.LEFT_WALL_1 && getTile(x, y) <= t.LEFT_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isRightWall(int x, int y) {
		if ((x > 0 && x < width) && (y > 0 && y < height)) {
			if (getTile(x, y) >= t.RIGHT_WALL_1 && getTile(x, y) <= t.RIGHT_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isVoid(int x, int y) {
		if ((x > 0 && x < width) && (y > 0 && y < height)) {
			if (getTile(x, y) == t.VOID_1 || getTile(x, y) == t.VOID_2 || getTile(x, y) == 0)
				return true;
			else
				return false;
		}
		return false;
	}

	public void setCollision(int x, int y, boolean i) {
		collisionLayer[x + width * y] = i;
	}

	public int getTile(int x, int y) {
		return tileLayer[x + width * y];
	}

	public Tileset getTileSheet() {
		return t;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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

	public void printTileLayer() {
		for (int i = 0; i < tileLayer.length; i++) {
			if (tileLayer[i] < 10)
				System.out.print(".");
			else
				System.out.print("1");

			if (i % 100 == 0)
				System.out.print("\n");
		}
	}

	public void autoTile() {
		int x, y = 0;

		// check for wall
		for (int i = 0; i < tileLayer.length; i++) {
			x = i % width;
			if (x == 0)
				y++;
			if (isFloor(x, y)) {// left wall
				if (isVoid(x - 1, y) || x - 1 < 0) {
					setTile(x, y, t.getLeftWall());
				}
			}
			if (isFloor(x, y)) {// right wall
				if (isVoid(x + 1, y) || x + 1 > width) {
					setTile(x, y, t.getRightWall());
				}
			}
			if (isFloor(x, y)) { // top wall
				if (isVoid(x, y - 1) || y - 1 < 0) {
					setTile(x, y, t.getTopWall());
				}
			}
			if (isFloor(x, y)) {// bottom wall
				if (isVoid(x, y + 1) || y + 1 > height) {
					setTile(x, y, t.getBottomWall());
				}
			}
		}

		x = 0;
		y = 0;
		// check for inside corrners
		for (int i = 0; i < tileLayer.length; i++) {
			x = i % width;
			if (x == 0)
				y++;
			if (isLeftWall(x, y)) {
				if (isTopWall(x + 1, y)) {
					setTile(x, y, t.TOP_LEFT_INSIDE);
				}
			}
			if (isLeftWall(x, y)) {
				if (isBottomWall(x + 1, y)) {
					setTile(x, y, t.BOTTOM_LEFT_INSIDE);
				}
			}
			if (isRightWall(x, y)) {
				if (isTopWall(x - 1, y)) {
					setTile(x, y, t.TOP_RIGHT_INSIDE);
				}
			}
			if (isRightWall(x, y)) {
				if (isBottomWall(x - 1, y)) {
					setTile(x, y, t.BOTTOM_RIGHT_INSIDE);
				}
			}

		}

		//Collision
		y = 0;
		for (int i = 0; i < tileLayer.length; i++) {
			x = i % width;
			if(x == 0)
				y++;
			if(isWall(x, y))
				setCollision(x, y, true);
		}

		for (Vector2 room : doors) {
			setTile((int) room.x, (int) room.y, t.DOOR);
		}

		if (y == 0) {
			setTile(x, y, t.VOID_2);
		} else if (y == getHeight() - 1) {
			setTile(x, y, t.VOID_2);

		} else if (x == 0) {
			setTile(x, y, t.VOID_2);
		} else if (x == getWidth() - 1) {
			setTile(x, y, t.VOID_2);
		}
	}
}

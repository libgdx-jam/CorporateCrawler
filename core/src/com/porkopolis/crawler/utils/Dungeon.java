package com.porkopolis.crawler.utils;

public class Dungeon {
	private int[] tileLayer;
	private boolean[] collisionLayer;
	private int[] entityLayer;
	private int xSize;
	private int ySize;
	private int objects;

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

	}

	public void setTile(int x, int y, int celltype) {
		if (coverTo1d(x, y) < xSize * ySize) {
			tileLayer[coverTo1d(x, y)] = celltype;
		} else {
			System.out.println("Dungeon.setTile, Array out of bounds: " + coverTo1d(x, y) + " x: " + x + " y: " + y);
		}
	}

	public int coverTo1d(int x, int y) {
		return x + (xSize * y);
	}

	public boolean isFloor(int x, int y) {
		if ((x > 0 && x < xSize) && (y > 0 && y < ySize)) {
			if (getTile(x, y) >= t.FLOOR_1_1 && getTile(x, y) <= t.FLOOR_5_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isWall(int x, int y) {
		if ((x > 0 && x < xSize) && (y > 0 && y < ySize)) {
			if (getTile(x, y) >= t.TOP_LEFT_INSIDE && getTile(x, y) <= t.BOTTOM_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isTopWall(int x, int y) {
		if ((x > 0 && x < xSize) && (y > 0 && y < ySize)) {
			if (getTile(x, y) >= t.TOP_WALL_1 && getTile(x, y) <= t.TOP_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isBottomWall(int x, int y) {
		if ((x > 0 && x < xSize) && (y > 0 && y < ySize)) {
			if (getTile(x, y) >= t.BOTTOM_WALL_1 && getTile(x, y) <= t.BOTTOM_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isLeftWall(int x, int y) {
		if ((x > 0 && x < xSize) && (y > 0 && y < ySize)) {
			if (getTile(x, y) >= t.LEFT_WALL_1 && getTile(x, y) <= t.LEFT_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isRightWall(int x, int y) {
		if ((x > 0 && x < xSize) && (y > 0 && y < ySize)) {
			if (getTile(x, y) >= t.RIGHT_WALL_1 && getTile(x, y) <= t.RIGHT_WALL_10)
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean isVoid(int x, int y) {
		if ((x > 0 && x < xSize) && (y > 0 && y < ySize)) {
			if (getTile(x, y) == t.VOID_1 || getTile(x, y) == t.VOID_2 || getTile(x, y) == 0)
				return true;
			else
				return false;
		}
		return false;
	}

	public void setCollision(int x, int y, boolean i) {
		collisionLayer[x + xSize * y] = i;
	}

	public int getTile(int x, int y) {
		return tileLayer[x + xSize * y];
	}

	public Tileset getTileSheet() {
		return t;
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
			x = i % xSize;
			if (x == 0)
				y++;
			if (isFloor(x, y)) {// left wall
				if (isVoid(x - 1, y) || x - 1 < 0) {
					setTile(x, y, t.getLeftWall());
				}
			}
			if (isFloor(x, y)) {// right wall
				if (isVoid(x + 1, y) || x + 1 > xSize) {
					setTile(x, y, t.getRightWall());
				}
			}
			if (isFloor(x, y)) { // top wall
				if (isVoid(x, y - 1) || y - 1 < 0) {
					setTile(x, y, t.getTopWall());
				}
			}
			if (isFloor(x, y)) {// bottom wall
				if (isVoid(x, y + 1) || y + 1 > ySize) {
					setTile(x, y, t.getBottomWall());
				}
			}
		}

		x = 0;
		y = 0;
		// check for inside corrners
		for (int i = 0; i < tileLayer.length; i++) {
			x = i % xSize;
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
		// check for outside corrners
		for (int i = 0; i < tileLayer.length; i++) {
			x = i % xSize;
			if (x == 0)
				y++;
			if (isFloor(x, y)) {
				if (isTopWall(x, y - 1) && isLeftWall(x - 1, y)) {
					setTile(x, y, t.TOP_LEFT_OUTSIDE);
				}
			}
			if (isFloor(x, y)) {
				if (isTopWall(x, y - 1) && isRightWall(x + 1, y)) {
					setTile(x, y, t.TOP_RIGHT_OUTSIDE);
				}
			}
			if (isFloor(x, y)) {
				if (isBottomWall(x, y + 1) && isLeftWall(x - 1, y)) {
					setTile(x, y, t.BOTTOM_LEFT_OUTSIDE);
				}
			}
			if (isFloor(x, y)) {
				if (isTopWall(x, y + 1) && isRightWall(x + 1, y)) {
					setTile(x, y, t.BOTTOM_RIGHT_OUTSIDE);
				}
			}

		}
	}

}

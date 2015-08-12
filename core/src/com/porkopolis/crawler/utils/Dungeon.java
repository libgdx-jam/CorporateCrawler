package com.porkopolis.crawler.utils;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Dungeon {
	private int[][] tileLayer;
	private boolean[][]collisionLayer;
	private int[][] entityLayer;
	private int width;
	private int height;
	private int objects;

	private String tileSet;
	private Tileset t = new Tileset();

	public static ArrayList<Vector2> doors = new ArrayList<Vector2>(100);
	public static ArrayList<Vector2> center = new ArrayList<Vector2>();

	public Dungeon(int width, int height, int objects, String tileSheet) {
		this.tileSet = tileSheet;

		if (objects < 1)
			this.objects = 10;
		else
			this.objects = objects;

		if (width < 3)
			this.width = 3;
		else
			this.width = width;

		if (height < 3)
			this.height = 3;
		else
			this.height = height;

		tileLayer = new int[height][width];
		collisionLayer = new boolean[height][width];
		entityLayer = new int[height][width];

	}

	public void setTile(int x, int y, int celltype) {
		if (x < width || y < height) {
			tileLayer[y][x] = celltype;
		} else {
			System.out.println("Dungeon.setTile, Array out of bounds");
		}
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

	public int getTile(int x, int y) {
		return tileLayer[y][x];
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

	public int[][] getTileLayer() {
		return tileLayer;
	}

	public boolean getCollision(int x, int y) {
		return collisionLayer[y][x];
	}
	public void setCollision(int x, int y, boolean i) {
		 collisionLayer[y][x] = i;
	}

	public int[][] getEntityLayer() {
		return entityLayer;
	}

	public String getTileSet() {
		return tileSet;
	}

	public Tileset getTitleSet() {
		return t;
	}

	public void printTileLayer() {
		for (int y = 0; y < tileLayer.length; y++) {
			for (int x = 0; x < tileLayer.length; x++) {
				if (tileLayer[y][x] < 10)
					System.out.print(".");
				else
					System.out.print("1");	
			}
			System.out.print("\n");
		}
	}

	public void autoTile() {
		int x, y = 0;
		System.out.println("balls");

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



		//Doors
		for (Vector2 room : doors) {
			setTile((int) room.x, (int) room.y, t.DOOR);
			
			if(isWall((int)room.x-1, (int)room.y) && isWall((int)room.x + 1, (int)room.y)){
				setTile((int) room.x-1, (int) room.y, t.getFloor1());
				setTile((int) room.x + 1, (int) room.y, t.getFloor1());
				
			}
			
			// if(isWall((int)room.x + 1, (int)room.y)){
			// 	setTile((int) room.x + 1, (int) room.y, t.getFloor1());
			// }
			
			if(isWall((int)room.x, (int)room.y + 1) && isWall((int)room.x, (int)room.y - 1)){
				setTile((int) room.x, (int) room.y + 1, t.getFloor1());
				setTile((int) room.x, (int) room.y - 1, t.getFloor1());

			}
			
			// if(isWall((int)room.x, (int)room.y - 1)){
			// 	setTile((int) room.x, (int) room.y - 1, t.getFloor1());
			// }
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
		
		//Collision
		y = 0;
		for (int i = 0; i < tileLayer.length; i++) {
			x = i % width;
			if(x == 0)
				y++;
			if(isWall(x, y))
				setCollision(x, y, true);
		}
		//test for center of rooms
		for(Vector2 c: center){
			//	setTile((int)c.x, (int)c.y, t.VOID_2);
		}
	}
}

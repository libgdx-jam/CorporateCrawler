package com.porkopolis.crawler.utils;

import com.badlogic.gdx.math.MathUtils;

public class DungeonGenerator2 {

	private static Tileset t;

	private static Dungeon dungeon;

	public static void createDungeon(Dungeon dungeon) {
		DungeonGenerator2.dungeon = dungeon;
		t = dungeon.getTitleSet();

		int newx = 0;
		int xmod = 0;
		int newy = 0;
		int ymod = 0;
		int validTile = -1;
		int currentFeatures = 0;

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				if (y == 0)
					dungeon.setTile(x, y, t.VOID_2);
				else if (y == dungeon.getySize() - 1)
					dungeon.setTile(x, y, t.VOID_2);
				else if (x == 0)
					dungeon.setTile(x, y, t.VOID_2);
				else if (x == dungeon.getxSize() - 1)
					dungeon.setTile(x, y, t.VOID_2);
				else
					dungeon.setTile(x, y, t.VOID_1);
			}
		}

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				dungeon.setCollision(x, y, false);
			}
		}

		DungeonGenerator2.makeRoom(dungeon.getxSize() / 2, dungeon.getySize() / 2, 8, 6, MathUtils.random(0, 3));

		currentFeatures = 1;

		for (int countingTries = 0; countingTries < 1000; countingTries++) {
			if (currentFeatures == dungeon.getObjects()) {
				break;
			}

			newx = 0;
			xmod = 0;
			newy = 0;
			ymod = 0;
			validTile = -1;

			for (int testing = 0; testing < 1000; testing++) {

				newx = MathUtils.random(1, dungeon.getxSize() - 1);
				newy = MathUtils.random(1, dungeon.getySize() - 1);

				validTile = -1;

				if (dungeon.isWall(newx, newy)) {
					if (dungeon.isFloor(newx, newy + 1)) {
						validTile = 0; //
						xmod = 0;
						ymod = -1;
					} else if (dungeon.isFloor(newx - 1, newy)) {
						validTile = 1; //
						xmod = +1;
						ymod = 0;
					} else if (dungeon.isFloor(newx, newy - 1)) {
						validTile = 2; //
						xmod = 0;
						ymod = +1;
					} else if (dungeon.isFloor(newx + 1, newy)) {
						validTile = 3; //
						xmod = -1;
						ymod = 0;
					}
					if (validTile > -1) {
						if (dungeon.getTile(newx, newy + 1) == t.DOOR) // north
							validTile = -1;
						else if (dungeon.getTile(newx - 1, newy) == t.DOOR) // east
							validTile = -1;
						else if (dungeon.getTile(newx, newy - 1) == t.DOOR) // south
							validTile = -1;
						else if (dungeon.getTile(newx + 1, newy) == t.DOOR) // west
							validTile = -1;
					}
					if (validTile > -1)
						break;
				}
			}

			if (validTile > -1) {
				if (makeRoom((newx + xmod), (newy + ymod), MathUtils.random(6, 15), MathUtils.random(6, 15), validTile)) {
					currentFeatures++; // add to our quota

					switch (validTile) {
					case 0:// north
						dungeon.setTile(newx, newy, t.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx, newy - 1, t.DOOR);
						dungeon.setCollision(newx, newy - 1, false);
						if (dungeon.getTile(newx - 1, newy) == t.TOP_LEFT_INSIDE)
							dungeon.setTile(newx - 1, newy, t.getLeftWall());
						else
							dungeon.setTile(newx - 1, newy, t.BOTTOM_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy) == t.TOP_RIGHT_INSIDE)
							dungeon.setTile(newx + 1, newy, t.getRightWall());
						else
							dungeon.setTile(newx + 1, newy, t.BOTTOM_LEFT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx - 1, newy - 1) == t.BOTTOM_LEFT_INSIDE)
							dungeon.setTile(newx - 1, newy - 1, t.getLeftWall());
						else
							dungeon.setTile(newx - 1, newy - 1, t.TOP_LEFT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy - 1) == t.BOTTOM_RIGHT_INSIDE)
							dungeon.setTile(newx + 1, newy - 1, t.getRightWall());
						else
							dungeon.setTile(newx + 1, newy - 1, t.TOP_RIGHT_OUTSIDE);
						break;
					case 1:// east
						dungeon.setTile(newx, newy, t.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx + 1, newy, t.DOOR);
						dungeon.setCollision(newx + 1, newy, false);
						if (dungeon.getTile(newx, newy - 1) == t.TOP_RIGHT_INSIDE)
							dungeon.setTile(newx, newy - 1, t.getTopWall());
						else
							dungeon.setTile(newx, newy - 1, t.BOTTOM_LEFT_OUTSIDE);

						if (dungeon.getTile(newx, newy + 1) == t.BOTTOM_RIGHT_INSIDE)
							dungeon.setTile(newx, newy + 1, t.getBottomWall());
						else
							dungeon.setTile(newx, newy + 1, t.TOP_RIGHT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx + 1, newy - 1) == t.TOP_LEFT_INSIDE)
							dungeon.setTile(newx + 1, newy - 1, t.getTopWall());
						else
							dungeon.setTile(newx + 1, newy - 1, t.BOTTOM_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy + 1) == t.BOTTOM_LEFT_INSIDE)
							dungeon.setTile(newx + 1, newy + 1, t.getBottomWall());
						else
							dungeon.setTile(newx + 1, newy + 1, t.TOP_LEFT_OUTSIDE);

						break;
					case 2:
						dungeon.setTile(newx, newy, t.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx, newy + 1, t.DOOR);
						dungeon.setCollision(newx, newy + 1, false);
						if (dungeon.getTile(newx + 1, newy) == t.BOTTOM_RIGHT_INSIDE) // RIGHT
							dungeon.setTile(newx + 1, newy, t.getRightWall());
						else
							dungeon.setTile(newx + 1, newy, t.TOP_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx - 1, newy) == t.BOTTOM_LEFT_INSIDE) // LEFT
							dungeon.setTile(newx - 1, newy, t.getLeftWall());
						else
							dungeon.setTile(newx - 1, newy, t.TOP_LEFT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx - 1, newy + 1) == t.TOP_RIGHT_INSIDE) // RIGHT
							dungeon.setTile(newx - 1, newy + 1, t.getRightWall());
						else
							dungeon.setTile(newx - 1, newy + 1, t.BOTTOM_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy + 1) == t.TOP_LEFT_INSIDE) // LEFT
							dungeon.setTile(newx + 1, newy + 1, t.getLeftWall());
						else
							dungeon.setTile(newx + 1, newy + 1, t.BOTTOM_LEFT_OUTSIDE);
						break;
					case 3:// west
						dungeon.setTile(newx, newy, t.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx - 1, newy, t.DOOR);
						dungeon.setCollision(newx - 1, newy, false);
						if (dungeon.getTile(newx, newy + 1) == t.BOTTOM_LEFT_INSIDE)
							dungeon.setTile(newx, newy + 1, t.getBottomWall());
						else
							dungeon.setTile(newx, newy + 1, t.TOP_LEFT_OUTSIDE);

						if (dungeon.getTile(newx, newy - 1) == t.TOP_LEFT_INSIDE)
							dungeon.setTile(newx, newy - 1, t.getTopWall());
						else
							dungeon.setTile(newx, newy - 1, t.BOTTOM_RIGHT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx - 1, newy + 1) == t.TOP_RIGHT_INSIDE)
							dungeon.setTile(newx - 1, newy + 1, t.getTopWall());
						else
							dungeon.setTile(newx - 1, newy + 1, t.TOP_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx - 1, newy - 1) == t.BOTTOM_RIGHT_INSIDE)
							dungeon.setTile(newx - 1, newy - 1, t.getBottomWall());
						else
							dungeon.setTile(newx - 1, newy - 1, t.BOTTOM_LEFT_OUTSIDE);

						break;
					}
				}
			}
		}
	}

	private static boolean makeRoom(int xStart, int yStart, int xLength, int yLength, int direction) {
		System.out.println("balls");
		// set the size of the room
		int xLen = MathUtils.random(6, xLength);
		int yLen = MathUtils.random(6, yLength);

		if (xStart + xLen > dungeon.getxSize())
			xLen = xLen - (dungeon.getxSize() - (xStart + xLen));
		System.out.println("x");
		if (yStart + yLen < dungeon.getySize())
			yLen = yLen - (dungeon.getySize() - (yStart + yLen));
		System.out.println("y");

		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				if (y == 0)
					dungeon.setTile(xStart + x, yStart + y, t.getTopWall());
				else if (y == yLength)
					dungeon.setTile(xStart + x, yStart + y, t.getBottomWall());
				else if (x == 0)
					dungeon.setTile(xStart + x, yStart + y, t.getLeftWall());
				else if (x == 0)
					dungeon.setTile(xStart + x, yStart + y, t.getRightWall());
				else
					dungeon.setTile(xStart + x, yStart + y, t.getFloor1());
			}
		}

		return false;
	}

	public static Tileset getTileSet() {
		return t;
	}

}

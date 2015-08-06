package com.porkopolis.crawler.utils;

import com.badlogic.gdx.math.MathUtils;

public class DungeonGenerator {

	private Dungeon dungeon;
	private Tileset tileset = new Tileset();

	public void createDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
		tileset = dungeon.getTitleSet();

		int newx = 0;
		int xmod = 0;
		int newy = 0;
		int ymod = 0;
		int validTile = -1;
		int currentFeatures = 0;

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				if (y == 0)
					dungeon.setTile(x, y, tileset.VOID_2);
				else if (y == dungeon.getySize() - 1)
					dungeon.setTile(x, y, tileset.VOID_2);
				else if (x == 0)
					dungeon.setTile(x, y, tileset.VOID_2);
				else if (x == dungeon.getxSize() - 1)
					dungeon.setTile(x, y, tileset.VOID_2);
				else
					dungeon.setTile(x, y, tileset.VOID_1);
			}
		}

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				dungeon.setCollision(x, y, false);
			}
		}

		makeRoom(dungeon.getxSize() / 2, dungeon.getySize() / 2, 8, 6, MathUtils.random(0, 3));

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
						if (dungeon.getTile(newx, newy + 1) == tileset.DOOR) // north
							validTile = -1;
						else if (dungeon.getTile(newx - 1, newy) == tileset.DOOR) // east
							validTile = -1;
						else if (dungeon.getTile(newx, newy - 1) == tileset.DOOR) // south
							validTile = -1;
						else if (dungeon.getTile(newx + 1, newy) == tileset.DOOR) // west
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
						dungeon.setTile(newx, newy, tileset.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx, newy - 1, tileset.DOOR);
						dungeon.setCollision(newx, newy - 1, false);
						if (dungeon.getTile(newx - 1, newy) == tileset.TOP_LEFT_INSIDE)
							dungeon.setTile(newx - 1, newy, tileset.getLeftWall());
						else
							dungeon.setTile(newx - 1, newy, tileset.BOTTOM_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy) == tileset.TOP_RIGHT_INSIDE)
							dungeon.setTile(newx + 1, newy, tileset.getRightWall());
						else
							dungeon.setTile(newx + 1, newy, tileset.BOTTOM_LEFT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx - 1, newy - 1) == tileset.BOTTOM_LEFT_INSIDE)
							dungeon.setTile(newx - 1, newy - 1, tileset.getLeftWall());
						else
							dungeon.setTile(newx - 1, newy - 1, tileset.TOP_LEFT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy - 1) == tileset.BOTTOM_RIGHT_INSIDE)
							dungeon.setTile(newx + 1, newy - 1, tileset.getRightWall());
						else
							dungeon.setTile(newx + 1, newy - 1, tileset.TOP_RIGHT_OUTSIDE);
						break;
					case 1:// east
						dungeon.setTile(newx, newy, tileset.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx + 1, newy, tileset.DOOR);
						dungeon.setCollision(newx + 1, newy, false);
						if (dungeon.getTile(newx, newy - 1) == tileset.TOP_RIGHT_INSIDE)
							dungeon.setTile(newx, newy - 1, tileset.getTopWall());
						else
							dungeon.setTile(newx, newy - 1, tileset.BOTTOM_LEFT_OUTSIDE);

						if (dungeon.getTile(newx, newy + 1) == tileset.BOTTOM_RIGHT_INSIDE)
							dungeon.setTile(newx, newy + 1, tileset.getBottomWall());
						else
							dungeon.setTile(newx, newy + 1, tileset.TOP_RIGHT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx + 1, newy - 1) == tileset.TOP_LEFT_INSIDE)
							dungeon.setTile(newx + 1, newy - 1, tileset.getTopWall());
						else
							dungeon.setTile(newx + 1, newy - 1, tileset.BOTTOM_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy + 1) == tileset.BOTTOM_LEFT_INSIDE)
							dungeon.setTile(newx + 1, newy + 1, tileset.getBottomWall());
						else
							dungeon.setTile(newx + 1, newy + 1, tileset.TOP_LEFT_OUTSIDE);

						break;
					case 2:
						dungeon.setTile(newx, newy, tileset.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx, newy + 1, tileset.DOOR);
						dungeon.setCollision(newx, newy + 1, false);
						if (dungeon.getTile(newx + 1, newy) == tileset.BOTTOM_RIGHT_INSIDE) // RIGHT
							dungeon.setTile(newx + 1, newy, tileset.getRightWall());
						else
							dungeon.setTile(newx + 1, newy, tileset.TOP_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx - 1, newy) == tileset.BOTTOM_LEFT_INSIDE) // LEFT
							dungeon.setTile(newx - 1, newy, tileset.getLeftWall());
						else
							dungeon.setTile(newx - 1, newy, tileset.TOP_LEFT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx - 1, newy + 1) == tileset.TOP_RIGHT_INSIDE) // RIGHT
							dungeon.setTile(newx - 1, newy + 1, tileset.getRightWall());
						else
							dungeon.setTile(newx - 1, newy + 1, tileset.BOTTOM_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx + 1, newy + 1) == tileset.TOP_LEFT_INSIDE) // LEFT
							dungeon.setTile(newx + 1, newy + 1, tileset.getLeftWall());
						else
							dungeon.setTile(newx + 1, newy + 1, tileset.BOTTOM_LEFT_OUTSIDE);
						break;
					case 3:// west
						dungeon.setTile(newx, newy, tileset.DOOR);
						dungeon.setCollision(newx, newy, false);
						dungeon.setTile(newx - 1, newy, tileset.DOOR);
						dungeon.setCollision(newx - 1, newy, false);
						if (dungeon.getTile(newx, newy + 1) == tileset.BOTTOM_LEFT_INSIDE)
							dungeon.setTile(newx, newy + 1, tileset.getBottomWall());
						else
							dungeon.setTile(newx, newy + 1, tileset.TOP_LEFT_OUTSIDE);

						if (dungeon.getTile(newx, newy - 1) == tileset.TOP_LEFT_INSIDE)
							dungeon.setTile(newx, newy - 1, tileset.getTopWall());
						else
							dungeon.setTile(newx, newy - 1, tileset.BOTTOM_RIGHT_OUTSIDE);
						// Other side
						if (dungeon.getTile(newx - 1, newy + 1) == tileset.TOP_RIGHT_INSIDE)
							dungeon.setTile(newx - 1, newy + 1, tileset.getTopWall());
						else
							dungeon.setTile(newx - 1, newy + 1, tileset.TOP_RIGHT_OUTSIDE);

						if (dungeon.getTile(newx - 1, newy - 1) == tileset.BOTTOM_RIGHT_INSIDE)
							dungeon.setTile(newx - 1, newy - 1, tileset.getBottomWall());
						else
							dungeon.setTile(newx - 1, newy - 1, tileset.BOTTOM_LEFT_OUTSIDE);

						break;
					}
				}
			}
		}
	}

	private boolean makeRoom(int x, int y, int xlength, int ylength, int direction) {
		int xlen = MathUtils.random(6, xlength);
		int ylen = MathUtils.random(6, ylength);
		Room room = new Room();

		int dir = 0;
		if (direction > 0 && direction < 4)
			dir = direction;

		switch (dir) {
		case 0: // north
			for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
				if (ytemp < 0 || ytemp > dungeon.getySize())
					return false;
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp < 0 || xtemp > dungeon.getxSize())
						return false;
					if (dungeon.getTile(xtemp, ytemp) != tileset.VOID_1)
						return false;
				}
			}

			// At this point we will be making the room
			room = new Room();

			for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp == (x - xlen / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getLeftWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == (y - (ylen - 1)))
							dungeon.setTile(xtemp, ytemp, tileset.TOP_LEFT_INSIDE);
						if (ytemp == y)
							dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_LEFT_INSIDE);
					} else if (xtemp == (x + (xlen - 1) / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getRightWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == (y - (ylen - 1)))
							dungeon.setTile(xtemp, ytemp, tileset.TOP_RIGHT_INSIDE);
						if (ytemp == y)
							dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_RIGHT_INSIDE);
					} else if (ytemp == y) {
						dungeon.setTile(xtemp, ytemp, tileset.getBottomWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else if (ytemp == (y - ylen + 1)) {
						dungeon.setTile(xtemp, ytemp, tileset.getTopWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else {
						dungeon.setTile(xtemp, ytemp, tileset.getFloor1());
						room.addTile(xtemp, ytemp, tileset.getFloor1());
					}
				}
			}
			break;

		case 1: // east
			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				if (ytemp < 0 || ytemp > dungeon.getySize())
					return false;
				for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
					if (xtemp < 0 || xtemp > dungeon.getxSize())
						return false;
					if (dungeon.getTile(xtemp, ytemp) != tileset.VOID_1)
						return false;
				}
			}

			// At this point we will be making the room
			room = new Room();

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
					if (xtemp == x) {
						dungeon.setTile(xtemp, ytemp, tileset.getLeftWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == y - ylen / 2)
							dungeon.setTile(xtemp, ytemp, tileset.TOP_LEFT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_LEFT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_LEFT_INSIDE);
						}
					} else if (xtemp == (x + xlen - 1)) {
						dungeon.setTile(xtemp, ytemp, tileset.getRightWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == y - ylen / 2)
							dungeon.setTile(xtemp, ytemp, tileset.TOP_RIGHT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_RIGHT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_RIGHT_INSIDE);
						}
					} else if (ytemp == (y - ylen / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getTopWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else if (ytemp == (y + (ylen - 1) / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getBottomWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else {
						dungeon.setTile(xtemp, ytemp, tileset.getFloor2());
						room.addTile(xtemp, ytemp, tileset.getFloor2());
					}
				}
			}

			break;

		case 2: // south
			for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
				if (ytemp < 0 || ytemp > dungeon.getySize())
					return false;
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp < 0 || xtemp > dungeon.getxSize())
						return false;
					if (dungeon.getTile(xtemp, ytemp) != tileset.VOID_1)
						return false;
				}
			}

			// At this point we will be making the room
			room = new Room();

			for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp == (x - xlen / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getLeftWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == y)
							dungeon.setTile(xtemp, ytemp, tileset.TOP_LEFT_INSIDE);
						if (ytemp == (y + (ylen - 1)))
							dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_LEFT_INSIDE);
					} else if (xtemp == (x + (xlen - 1) / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getRightWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == y)
							dungeon.setTile(xtemp, ytemp, tileset.TOP_RIGHT_INSIDE);
						if (ytemp == (y + (ylen - 1)))
							dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_RIGHT_INSIDE);
					} else if (ytemp == y) {
						dungeon.setTile(xtemp, ytemp, tileset.getTopWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else if (ytemp == (y + ylen - 1)) {
						dungeon.setTile(xtemp, ytemp, tileset.getBottomWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else {
						dungeon.setTile(xtemp, ytemp, tileset.getFloor3());
						room.addTile(xtemp, ytemp, tileset.getFloor3());
					}
				}
			}

			break;

		case 3: // west

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				if (ytemp < 0 || ytemp > dungeon.getySize())
					return false;
				for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
					if (xtemp < 0 || xtemp > dungeon.getxSize())
						return false;
					if (dungeon.getTile(xtemp, ytemp) != tileset.VOID_1)
						return false;
				}
			}

			// At this point we will be making the room
			room = new Room();

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
					if (xtemp == x) {
						dungeon.setTile(xtemp, ytemp, tileset.getRightWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == y - ylen / 2)
							dungeon.setTile(xtemp, ytemp, tileset.TOP_RIGHT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_RIGHT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_RIGHT_INSIDE);
						}
					} else if (xtemp == (x - xlen + 1)) {
						dungeon.setTile(xtemp, ytemp, tileset.getLeftWall());
						dungeon.setCollision(xtemp, ytemp, true);
						if (ytemp == y - ylen / 2)
							dungeon.setTile(xtemp, ytemp, tileset.TOP_LEFT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_LEFT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								dungeon.setTile(xtemp, ytemp, tileset.BOTTOM_LEFT_INSIDE);
						}
					} else if (ytemp == (y - ylen / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getTopWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else if (ytemp == (y + (ylen - 1) / 2)) {
						dungeon.setTile(xtemp, ytemp, tileset.getBottomWall());
						dungeon.setCollision(xtemp, ytemp, true);
					} else
						dungeon.setTile(xtemp, ytemp, tileset.getFloor4());
					room.addTile(xtemp, ytemp, tileset.getFloor4());

				}
			}

			break;
		}
		return true;
	}

}

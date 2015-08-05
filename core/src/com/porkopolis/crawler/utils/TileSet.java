package com.porkopolis.crawler.utils;

import com.badlogic.gdx.math.MathUtils;

public class TileSet {
	// a list over tile types we're using
	final public int VOID_1 = 1;
	final public int VOID_2 = 2;
	final public int TOP_LEFT_INSIDE = 3;
	final public int TOP_RIGHT_INSIDE = 4;
	final public int BOTTOM_LEFT_INSIDE = 5;
	final public int BOTTOM_RIGHT_INSIDE = 6;
	final public int TOP_RIGHT_OUTSIDE = 7;
	final public int TOP_LEFT_OUTSIDE = 8;
	final public int BOTTOM_LEFT_OUTSIDE = 9;
	final public int BOTTOM_RIGHT_OUTSIDE = 10;

	final public int LEFT_WALL_1 = 11;
	final public int LEFT_WALL_2 = 12;
	final public int LEFT_WALL_3 = 13;
	final public int LEFT_WALL_4 = 14;
	final public int LEFT_WALL_5 = 15;
	final public int LEFT_WALL_6 = 16;
	final public int LEFT_WALL_7 = 17;
	final public int LEFT_WALL_8 = 18;
	final public int LEFT_WALL_9 = 19;
	final public int LEFT_WALL_10 = 20;

	final public int RIGHT_WALL_1 = 21;
	final public int RIGHT_WALL_2 = 22;
	final public int RIGHT_WALL_3 = 23;
	final public int RIGHT_WALL_4 = 24;
	final public int RIGHT_WALL_5 = 25;
	final public int RIGHT_WALL_6 = 26;
	final public int RIGHT_WALL_7 = 27;
	final public int RIGHT_WALL_8 = 28;
	final public int RIGHT_WALL_9 = 29;
	final public int RIGHT_WALL_10 = 30;

	final public int TOP_WALL_1 = 31;
	final public int TOP_WALL_2 = 32;
	final public int TOP_WALL_3 = 33;
	final public int TOP_WALL_4 = 34;
	final public int TOP_WALL_5 = 35;
	final public int TOP_WALL_6 = 36;
	final public int TOP_WALL_7 = 37;
	final public int TOP_WALL_8 = 38;
	final public int TOP_WALL_9 = 39;
	final public int TOP_WALL_10 = 40;

	final public int BOTTOM_WALL_1 = 41;
	final public int BOTTOM_WALL_2 = 42;
	final public int BOTTOM_WALL_3 = 43;
	final public int BOTTOM_WALL_4 = 44;
	final public int BOTTOM_WALL_5 = 45;
	final public int BOTTOM_WALL_6 = 46;
	final public int BOTTOM_WALL_7 = 47;
	final public int BOTTOM_WALL_8 = 48;
	final public int BOTTOM_WALL_9 = 49;
	final public int BOTTOM_WALL_10 = 50;

	final public int FLOOR_1_1 = 51;
	final public int FLOOR_1_2 = 52;
	final public int FLOOR_1_3 = 53;
	final public int FLOOR_1_4 = 54;
	final public int FLOOR_1_5 = 55;
	final public int FLOOR_1_6 = 56;
	final public int FLOOR_1_7 = 57;
	final public int FLOOR_1_8 = 58;
	final public int FLOOR_1_9 = 59;
	final public int FLOOR_1_10 = 60;

	final public int FLOOR_2_1 = 61;
	final public int FLOOR_2_2 = 62;
	final public int FLOOR_2_3 = 63;
	final public int FLOOR_2_4 = 64;
	final public int FLOOR_2_5 = 65;
	final public int FLOOR_2_6 = 66;
	final public int FLOOR_2_7 = 67;
	final public int FLOOR_2_8 = 68;
	final public int FLOOR_2_9 = 69;
	final public int FLOOR_2_10 = 70;

	final public int FLOOR_3_1 = 71;
	final public int FLOOR_3_2 = 72;
	final public int FLOOR_3_3 = 73;
	final public int FLOOR_3_4 = 74;
	final public int FLOOR_3_5 = 75;
	final public int FLOOR_3_6 = 76;
	final public int FLOOR_3_7 = 77;
	final public int FLOOR_3_8 = 78;
	final public int FLOOR_3_9 = 79;
	final public int FLOOR_3_10 = 80;

	final public int FLOOR_4_1 = 61;
	final public int FLOOR_4_2 = 62;
	final public int FLOOR_4_3 = 63;
	final public int FLOOR_4_4 = 64;
	final public int FLOOR_4_5 = 65;
	final public int FLOOR_4_6 = 66;
	final public int FLOOR_4_7 = 67;
	final public int FLOOR_4_8 = 68;
	final public int FLOOR_4_9 = 69;
	final public int FLOOR_4_10 = 70;

	final public int FLOOR_5_1 = 71;
	final public int FLOOR_5_2 = 72;
	final public int FLOOR_5_3 = 73;
	final public int FLOOR_5_4 = 74;
	final public int FLOOR_5_5 = 75;
	final public int FLOOR_5_6 = 76;
	final public int FLOOR_5_7 = 77;
	final public int FLOOR_5_8 = 78;
	final public int FLOOR_5_9 = 79;
	final public int FLOOR_5_10 = 80;

	// Fill the blanks in backward.
	final public int BLANK_1 = 81;
	final public int BLANK_2 = 82;
	final public int BLANK_3 = 83;
	final public int BLANK_4 = 84;
	final public int BLANK_5 = 85;
	final public int BLANK_6 = 86;
	final public int BLANK_7 = 87;
	final public int BLANK_8 = 88;
	final public int BLANK_9 = 89;
	final public int BLANK_10 = 91;
	final public int BLANK_11 = 92;
	final public int BLANK_12 = 93;
	final public int BLANK_13 = 94;
	final public int BLANK_14 = 95;
	final public int BLANK_15 = 96;
	final public int BLANK_16 = 97;
	final public int BLANK_17 = 98;
	final public int BLANK_18 = 99;

	final public int DOOR = 100;

	public int getRightWall() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return RIGHT_WALL_2;
		case 1:
			return RIGHT_WALL_3;
		case 2:
			return RIGHT_WALL_4;
		case 3:
			return RIGHT_WALL_5;
		case 4:
			return RIGHT_WALL_6;
		case 5:
			return RIGHT_WALL_7;
		case 6:
			return RIGHT_WALL_8;
		case 7:
			return RIGHT_WALL_9;
		case 8:
			return RIGHT_WALL_10;
		default:
			return RIGHT_WALL_1;
		}
	}

	public int getLeftWall() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return LEFT_WALL_2;
		case 1:
			return LEFT_WALL_3;
		case 2:
			return LEFT_WALL_4;
		case 3:
			return LEFT_WALL_5;
		case 4:
			return LEFT_WALL_6;
		case 5:
			return LEFT_WALL_7;
		case 6:
			return LEFT_WALL_8;
		case 7:
			return LEFT_WALL_9;
		case 8:
			return LEFT_WALL_10;
		default:
			return LEFT_WALL_1;
		}
	}

	public int getTopWall() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return TOP_WALL_2;
		case 1:
			return TOP_WALL_3;
		case 2:
			return TOP_WALL_4;
		case 3:
			return TOP_WALL_5;
		case 4:
			return TOP_WALL_6;
		case 5:
			return TOP_WALL_7;
		case 6:
			return TOP_WALL_8;
		case 7:
			return TOP_WALL_9;
		case 8:
			return TOP_WALL_10;
		default:
			return TOP_WALL_1;
		}
	}

	public int getBottomWall() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return BOTTOM_WALL_2;
		case 1:
			return BOTTOM_WALL_3;
		case 2:
			return BOTTOM_WALL_4;
		case 3:
			return BOTTOM_WALL_5;
		case 4:
			return BOTTOM_WALL_6;
		case 5:
			return BOTTOM_WALL_7;
		case 6:
			return BOTTOM_WALL_8;
		case 7:
			return BOTTOM_WALL_9;
		case 8:
			return BOTTOM_WALL_10;
		default:
			return BOTTOM_WALL_1;
		}
	}

	public int getFloor1() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return FLOOR_1_2;
		case 1:
			return FLOOR_1_3;
		case 2:
			return FLOOR_1_4;
		case 3:
			return FLOOR_1_5;
		case 4:
			return FLOOR_1_6;
		case 5:
			return FLOOR_1_7;
		case 6:
			return FLOOR_1_8;
		case 7:
			return FLOOR_1_9;
		case 8:
			return FLOOR_1_10;
		default:
			return FLOOR_1_1;
		}
	}

	public int getFloor2() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return FLOOR_2_2;
		case 1:
			return FLOOR_2_3;
		case 2:
			return FLOOR_2_4;
		case 3:
			return FLOOR_2_5;
		case 4:
			return FLOOR_2_6;
		case 5:
			return FLOOR_2_7;
		case 6:
			return FLOOR_2_8;
		case 7:
			return FLOOR_2_9;
		case 8:
			return FLOOR_2_10;
		default:
			return FLOOR_2_1;
		}
	}

	public int getFloor3() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return FLOOR_3_2;
		case 1:
			return FLOOR_3_3;
		case 2:
			return FLOOR_3_4;
		case 3:
			return FLOOR_3_5;
		case 4:
			return FLOOR_3_6;
		case 5:
			return FLOOR_3_7;
		case 6:
			return FLOOR_3_8;
		case 7:
			return FLOOR_3_9;
		case 8:
			return FLOOR_3_10;
		default:
			return FLOOR_3_1;
		}
	}

	public int getFloor4() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return FLOOR_4_2;
		case 1:
			return FLOOR_4_3;
		case 2:
			return FLOOR_4_4;
		case 3:
			return FLOOR_4_5;
		case 4:
			return FLOOR_4_6;
		case 5:
			return FLOOR_4_7;
		case 6:
			return FLOOR_4_8;
		case 7:
			return FLOOR_4_9;
		case 8:
			return FLOOR_4_10;
		default:
			return FLOOR_4_1;
		}
	}

	public int getFloor5() {
		switch (MathUtils.random(0, 99)) {
		case 0:
			return FLOOR_5_2;
		case 1:
			return FLOOR_5_3;
		case 2:
			return FLOOR_5_4;
		case 3:
			return FLOOR_5_5;
		case 4:
			return FLOOR_5_6;
		case 5:
			return FLOOR_5_7;
		case 6:
			return FLOOR_5_8;
		case 7:
			return FLOOR_5_9;
		case 8:
			return FLOOR_5_10;
		default:
			return FLOOR_5_1;
		}
	}
}

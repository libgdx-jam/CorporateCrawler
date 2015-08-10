package com.porkopolis.crawler.generators;

import java.util.Random;

public class Leaf {

	private static int MIN_SIZE = 5;
	private static Random rnd = new Random();

	int x;
	int y;
	int width;
	int height;

	Leaf leftChild;
	Leaf rightChild;
	Leaf room;

	public Leaf(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean split() {
		if (leftChild != null) // if already split, bail out
			return false;
		boolean horizontal = rnd.nextBoolean(); // direction of split
		int max = (horizontal ? height : width) - MIN_SIZE; // maximum
															// height/width we
															// can split off
		if (max <= MIN_SIZE) // area too small to split, bail out
			return false;
		int split = rnd.nextInt(max); // generate split point
		if (split < MIN_SIZE) // adjust split point so there's at least MIN_SIZE
								// in both partitions
			split = MIN_SIZE;
		if (horizontal) { // populate child areas
			leftChild = new Leaf(x, y, width, split);
			rightChild = new Leaf(x + split, y, width, height - split);
		} else {
			leftChild = new Leaf(x, y, split, height);
			rightChild = new Leaf(x, y + split, width - split, height);
		}
		return true; // split successful
	}

	public void generateDungeon() {
		// if current are has child areas, propagate
		// the call
		if (leftChild != null) {
			leftChild.generateDungeon();
			rightChild.generateDungeon();
		} else { // if leaf node, create a dungeon within the minimum size
					// constraints
			int dungeonTop = (height - MIN_SIZE <= 0) ? 0 : rnd.nextInt(height - MIN_SIZE);
			int dungeonLeft = (width - MIN_SIZE <= 0) ? 0 : rnd.nextInt(width - MIN_SIZE);
			int dungeonHeight = Math.max(rnd.nextInt(height - dungeonTop), MIN_SIZE);

			int dungeonWidth = Math.max(rnd.nextInt(width - dungeonLeft), MIN_SIZE);

			room = new Leaf(x + dungeonTop, y + dungeonLeft, dungeonWidth, dungeonHeight);
		}
	}

	@Override
	public String toString() {
		return x + "  " + y + " " + width + " " + height + " room == null: " + (room == null);
	}

}
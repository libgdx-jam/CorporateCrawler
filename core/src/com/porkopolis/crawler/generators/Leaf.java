package com.porkopolis.crawler.generators;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.porkopolis.crawler.utils.Dungeon;

public class Leaf {

	private static int MIN_SIZE = 12;

	int x;
	int y;
	int width;
	int height;

	Leaf leftChild;
	Leaf rightChild;
	Leaf room;

	boolean isLinked = false;

	public Leaf(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean split() {
		if (leftChild != null) // if already split, bail out
			return false;
		boolean horizontal = MathUtils.randomBoolean(); // direction of split
		int max = (horizontal ? height : width) - MIN_SIZE; // maximum
															// height/width we
															// can split off
		if (max <= MIN_SIZE) // area too small to split, bail out
			return false;
		int split = MathUtils.random(max); // generate split point
		if (split < MIN_SIZE) // adjust split point so there's at least MIN_SIZE
								// in both partitions
			split = MIN_SIZE;
		if (horizontal) { // populate child areas
			leftChild = new Leaf(x, y, width, split);
			rightChild = new Leaf(x + split, y, width, height - split);
			Dungeon.doors.add(new Vector2(x + MathUtils.random(1, split - 1), y));
		} else {
			leftChild = new Leaf(x, y, split, height);
			rightChild = new Leaf(x, y + split, width - split, height);
			Dungeon.doors.add(new Vector2(x, y + MathUtils.random(1, split - 2)));
		}
		Dungeon.center.add(new Vector2(x+split/2, y+split/2));
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

			boolean fillLeaf = true;

			if (!fillLeaf) {
				int dungeonTop = (height - MIN_SIZE <= 0) ? 0 : MathUtils.random(height - MIN_SIZE);
				int dungeonLeft = (width - MIN_SIZE <= 0) ? 0 : MathUtils.random(width - MIN_SIZE);
				int dungeonHeight = Math.max(MathUtils.random(height - dungeonTop), MIN_SIZE);

				int dungeonWidth = Math.max(MathUtils.random(width - dungeonLeft), MIN_SIZE);

				room = new Leaf(x + dungeonTop, y + dungeonLeft, dungeonWidth, dungeonHeight);
			} else {
				room = new Leaf(x, y, width, height);
			}
		}
	}

	@Override
	public String toString() {
		return x + "  " + y + " " + width + " " + height + " room == null: " + (room == null);
	}

}
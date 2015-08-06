package com.porkopolis.crawler.utils;

import java.util.ArrayList;
import java.util.Random;

import com.porkopolis.crawler.DungeonManager;

public class GenerateDungeon {

	private static Random rnd = new Random();

	private static void convert(Dungeon dungeon, ArrayList<Leaf> rectangles) {

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				if (y == 0)
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);
				else if (y == dungeon.getySize() - 1)
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);
				else if (x == 0)
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);
				else if (x == dungeon.getxSize() - 1)
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);
				else
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_1);
			}
		}

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				dungeon.setCollision(x, y, false);
			}
		}

		for (Leaf leaf : rectangles) {
			if (leaf.room == null)
				continue;
			Leaf d = leaf.room;

			for (int i = d.x; i < d.height; i++) {
				for (int j = d.y; j < d.width; j++) {
					DungeonManager.dungeon.setTile(i, j, DungeonManager.tileset.getFloor1());
				}
			}
		}

	}

	public void generateDungeon(Dungeon dungeon) {
		ArrayList<Leaf> rectangles = new ArrayList<Leaf>(); // flat rectangle
		// store to help
		// pick a random one
		Leaf root = new Leaf(0, 0, 30, 60); //
		rectangles.add(root); // populate rectangle store with root area
		while (rectangles.size() < 38) { // this will give us 20? leaf areas
			int splitIdx = rnd.nextInt(rectangles.size()); // choose a random
			// element
			Leaf toSplit = rectangles.get(splitIdx);
			if (toSplit.split()) { // attempt to split
				rectangles.add(toSplit.leftChild);
				rectangles.add(toSplit.rightChild);
			}

		}
		root.generateDungeon(); // generate dungeons

		convert(dungeon, rectangles);

	}

}
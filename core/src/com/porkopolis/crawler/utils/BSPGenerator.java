package com.porkopolis.crawler.utils;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.porkopolis.crawler.DungeonManager;

public class BSPGenerator {

	private static Random rnd = new Random();

	public void generateDungeon() {
		ArrayList<Leaf> rectangles = new ArrayList<Leaf>();
		Leaf root = new Leaf(0, 0, DungeonManager.dungeon.getxSize(), DungeonManager.dungeon.getySize()); //
		rectangles.add(root); // populate rectangle store with root area
		while (rectangles.size() < 19) { // this will give us 20? leaf areas
			int splitIdx = rnd.nextInt(rectangles.size()); // choose a random
			// element
			Leaf toSplit = rectangles.get(splitIdx);
			if (toSplit.split()) { // attempt to split
				rectangles.add(toSplit.leftChild);
				rectangles.add(toSplit.rightChild);
			}

		}
		root.generateDungeon(); // generate dungeons

		convert(DungeonManager.dungeon, rectangles);

	}

	private void convert(Dungeon dungeon, ArrayList<Leaf> rectangles) {

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				if (y == 0) {
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);
				} else if (y == dungeon.getySize() - 1) {
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);

				} else if (x == 0) {
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);
				} else if (x == dungeon.getxSize() - 1) {
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_2);
				} else {
					dungeon.setTile(x, y, DungeonManager.tileset.VOID_1);
				}
				dungeon.setCollision(x, y, false);
			}
		}

		Gdx.app.log(BSPGenerator.class.getSimpleName(), "Number of rooms created: " + rectangles.size());

		for (Leaf leaf : rectangles) {
			Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached 1");
			if (leaf.room == null) {
				Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached 2");
				continue;
			}
			Leaf d = leaf.room;

			for (int i = d.x; i < d.height; i++) {
				for (int j = d.y; j < d.width; j++) {
					DungeonManager.dungeon.setTile(i, j, DungeonManager.tileset.getFloor1());
					dungeon.setCollision(i, j, true);
					Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached 3");

				}
			}
		}

	}

	private static void printDungeons(ArrayList<Leaf> Leafs) {
		byte[][] lines = new byte[60][];
		for (int i = 0; i < 60; i++) {
			lines[i] = new byte[120];
			for (int j = 0; j < 120; j++)
				lines[i][j] = -1;
		}
		byte dungeonCount = -1;
		for (Leaf r : Leafs) {
			if (r.room == null)
				continue;
			Leaf d = r.room;
			dungeonCount++;
			for (int i = 0; i < d.height; i++) {
				for (int j = 0; j < d.width; j++)

					lines[d.y + i][d.x + j] = dungeonCount;
			}
		}
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 120; j++) {
				if (lines[i][j] == -1)
					System.out.print('.');
				else
					System.out.print(lines[i][j]);
			}
			System.out.println();
		}
	}

}
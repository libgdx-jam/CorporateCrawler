package com.porkopolis.crawler.utils;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class BSPGenerator {

	private Random rnd = new Random();
	private Dungeon dungeon;
	Tileset tileset = new Tileset();

	public BSPGenerator(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void generateDungeon() {
		ArrayList<Leaf> rectangles = new ArrayList<Leaf>();
		Leaf root = new Leaf(0, 0, dungeon.getxSize(), dungeon.getySize()); //
		rectangles.add(root); // populate rectangle store with root area
		while (rectangles.size() < 19) { // this will give us 10? leaf areas
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
		printDungeons(rectangles);

	}

	private void convert(Dungeon dungeon, ArrayList<Leaf> rectangles) {

		for (int y = 0; y < dungeon.getySize(); y++) {
			for (int x = 0; x < dungeon.getxSize(); x++) {
				if (y == 0) {
					dungeon.setTile(x, y, tileset.VOID_2);
				} else if (y == dungeon.getySize() - 1) {
					dungeon.setTile(x, y, tileset.VOID_2);

				} else if (x == 0) {
					dungeon.setTile(x, y, tileset.VOID_2);
				} else if (x == dungeon.getxSize() - 1) {
					dungeon.setTile(x, y, tileset.VOID_2);
				} else {
					dungeon.setTile(x, y, tileset.VOID_1);
				}
				dungeon.setCollision(x, y, false);
			}
		}

		Gdx.app.log(BSPGenerator.class.getSimpleName(), "Number of leafs created: " + rectangles.size());
		int leafCount = 0;
		for (int l = 0; l < rectangles.size(); l++) {
			// Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached room @ "
			// + leaf.x + " " + leaf.y);
			Leaf leaf = rectangles.get(l);
			if (leaf.room == null) {
				Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached leaf " + leafCount++);
				continue;
			}
			Leaf roomLeaf = leaf.room;
			Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached 3 @ " + roomLeaf.toString());

			for (int leafHeight = roomLeaf.y; leafHeight < roomLeaf.height; leafHeight++) {
				Gdx.app.log(BSPGenerator.class.getSimpleName(), "test");

				for (int leafWidth = roomLeaf.x; leafWidth < roomLeaf.width; leafWidth++) {
					Gdx.app.log(BSPGenerator.class.getSimpleName(), "test");

					Gdx.app.log(BSPGenerator.class.getSimpleName(), leafHeight + " " + leafWidth + " " + roomLeaf.toString());
					dungeon.setTile(leafWidth, leafHeight, tileset.getFloor1());
					dungeon.setCollision(leafWidth, leafHeight, true);
					Gdx.app.log(BSPGenerator.class.getSimpleName(), "Set collision @" + leafWidth + " " + leafHeight);

				}
			}

		}

	}

	private void printDungeons(ArrayList<Leaf> Leafs) {
		System.out.print(dungeon.getTileLayer().toString());
	}

}
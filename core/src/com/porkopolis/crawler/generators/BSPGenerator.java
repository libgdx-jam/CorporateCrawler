package com.porkopolis.crawler.generators;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.porkopolis.crawler.utils.Dungeon;
import com.porkopolis.crawler.utils.Tileset;

public class BSPGenerator {

	private Random rnd = new Random();
	private Dungeon dungeon;
	Tileset tileset = new Tileset();

	public BSPGenerator(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void generateDungeon() {
		ArrayList<Leaf> Leafs = new ArrayList<Leaf>(); // flat Leaf store to
														// help pick a random
														// one
		Leaf root = new Leaf(0, 0, 99, 99); //
		Leafs.add(root); // populate Leaf store with root area
		while (Leafs.size() < 19) { // this will give us 10 leaf areas
			int splitIdx = rnd.nextInt(Leafs.size()); // choose a random element
			Leaf toSplit = Leafs.get(splitIdx);
			if (toSplit.split()) { // attempt to split
				Leafs.add(toSplit.leftChild);
				Leafs.add(toSplit.rightChild);
			}

		}
		root.generateDungeon(); // generate dungeons
		convert(Leafs);
		printDungeons(Leafs); // this is just to test the output

	}

	private void debug(Dungeon dungeon, ArrayList<Leaf> Leafs) {
		Gdx.app.log(BSPGenerator.class.getSimpleName(), "Number of leafs created: " + Leafs.size());
		int leafCount = 0;
		for (int l = 0; l < Leafs.size(); l++) {
			// Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached room @ "
			// + leaf.x + " " + leaf.y);
			Leaf leaf = Leafs.get(l);
			if (leaf.room != null) {
				Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached leaf " + leafCount++);
				continue;
			}
			Leaf roomLeaf = leaf.room;
			Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached 3 @ " + roomLeaf.toString());
			for (int leafHeight = roomLeaf.y; leafHeight < roomLeaf.height + roomLeaf.y; leafHeight++) {
				Gdx.app.log(BSPGenerator.class.getSimpleName(), "test");

				for (int leafWidth = roomLeaf.x; leafWidth < roomLeaf.width + roomLeaf.x; leafWidth++) {
					Gdx.app.log(BSPGenerator.class.getSimpleName(), "test");
					Gdx.app.log(BSPGenerator.class.getSimpleName(), leafHeight + " " + leafWidth + " " + roomLeaf.toString());
					Gdx.app.log(BSPGenerator.class.getSimpleName(), "Set collision @" + leafWidth + " " + leafHeight);

				}
			}

		}

	}

	private void convert(ArrayList<Leaf> leafs) {
		// clear dungeon
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
		// Convert BSP to TMX
		for (Leaf r : leafs) {
			if (r.room == null)
				continue;
			Leaf d = r.room;
			for (int j = 1; j < d.width; j++) {
				for (int i = 1; i < d.height; i++) {
					dungeon.setTile(d.x + i, d.y + j, dungeon.getTileSheet().getFloor1());
				}
			}
		}
	}

	private void printDungeons(ArrayList<Leaf> leafs) {
		byte[][] lines = new byte[100][];
		for (int i = 0; i < 100; i++) {
			lines[i] = new byte[100];
			for (int j = 0; j < 100; j++)
				lines[i][j] = -1;
		}
		byte dungeonCount = -1;
		for (Leaf r : leafs) {
			if (r.room == null)
				continue;
			Leaf d = r.room;
			dungeonCount++;

			for (int j = 0; j < d.width; j++) {
				for (int i = 0; i < d.height; i++) {
					System.out.println(d.x + i + " " + d.y + j);
					lines[d.y + j][d.x + i] = dungeonCount;
				}
			}
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (lines[i][j] == -1)
					System.out.print('.');
				else
					System.out.print(lines[i][j]);
			}
			System.out.println();
		}
	}

}
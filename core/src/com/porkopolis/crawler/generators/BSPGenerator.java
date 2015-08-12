package com.porkopolis.crawler.generators;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.porkopolis.crawler.utils.Dungeon;
import com.porkopolis.crawler.utils.Tileset;
import com.badlogic.gdx.math.Vector2;

public class BSPGenerator {

	private Random rnd = new Random();
	private Dungeon dungeon;
	Tileset tileset = new Tileset();
	public ArrayList<Leaf> Leafs;

	public BSPGenerator(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void generateDungeon() {
		Leafs = new ArrayList<Leaf>(); // flat Leaf store to
														// help pick a random
														// one
		Leaf root = new Leaf(1, 1, 98, 98); //
		Leafs.add(root); // populate Leaf store with root area
		for (int x = 0; x < 156; x++) { // this will give us 10 leaf areas
			int splitIdx = rnd.nextInt(Leafs.size()); // choose a random element
			Leaf toSplit = Leafs.get(splitIdx);
			if (toSplit.split()) { // attempt to split
				Leafs.add(toSplit.leftChild);
				Leafs.add(toSplit.rightChild);
			}

		}
		root.generateDungeon(); // generate dungeons
		convert(Leafs);
		//dungeon.printTileLayer();
		//printDungeons(Leafs); // this is just to test the output

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
		for (int y = 0; y < dungeon.getHeight(); y++) {
			for (int x = 0; x < dungeon.getWidth(); x++) {
				dungeon.setTile(x, y, tileset.VOID_1);
				dungeon.setCollision(x, y, false);
			}
		}
		// Convert BSP to TMX
		for (Leaf r : leafs) {
			if (r.room == null)
				continue;
			Leaf d = r.room;
			dungeon.center.add(new Vector2(d.x, d.y));
			for (int y = 1; y < d.width; y++) {
				for (int x = 1; x < d.height; x++) {
					dungeon.setTile(d.y + y, d.x + x, dungeon.getTileSheet().getFloor1());
					
				}
			}
		
		}
	}

	private void printDungeons(ArrayList<Leaf> leafs) {
		byte[][] lines = new byte[dungeon.getHeight()][];
		for (int y = 0; y < dungeon.getHeight(); y++) {
			lines[y] = new byte[dungeon.getWidth()];
			for (int x = 0; x < lines[y].length; x++)
				lines[y][x] = -1;
		}
		byte dungeonCount = -1;
		for (Leaf r : leafs) {
			if (r.room == null)
				continue;
			Leaf d = r.room;
			dungeonCount++;

			for (int y = 0; y < d.width; y++) {
				for (int x = 0; x < d.height; x++) {
					System.out.println(d.x + x + " " + d.y + y);
					lines[d.y + y][d.x + x] = dungeonCount;
				}
			}
		}
		for (int y = 0; y < dungeon.getHeight(); y++) {
			for (int x = 0; x < dungeon.getHeight(); x++) {
				if (lines[y][x] == -1)
					System.out.print("++");
				else
					System.out.print(String.format("%03d", lines[y][x]));
			}
			System.out.println();
		}
	}

}
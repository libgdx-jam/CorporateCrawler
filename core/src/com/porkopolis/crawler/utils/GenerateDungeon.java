package com.porkopolis.crawler.utils;

import java.util.ArrayList;
import java.util.Random;

public class GenerateDungeon {

	private static Random rnd = new Random();

	public static void main(String[] args) {
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

		printDungeons(rectangles); // this is just to test the output

	}

	private static void printDungeons(ArrayList<Leaf> rectangles) {
		byte[][] lines = new byte[60][];
		for (int i = 0; i < 60; i++) {
			lines[i] = new byte[120];
			for (int j = 0; j < 120; j++)
				lines[i][j] = -1;
		}
		byte dungeonCount = -1;
		for (Leaf r : rectangles) {
			if (r.dungeon == null)
				continue;
			Leaf d = r.dungeon;
			dungeonCount++;
			for (int i = 0; i < d.height; i++) {
				for (int j = 0; j < d.width; j++)

					lines[d.x + i][d.y + j] = dungeonCount;
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
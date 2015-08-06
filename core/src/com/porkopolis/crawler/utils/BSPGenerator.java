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

	//	convert(dungeon, rectangles);
		printDungeons(dungeon, rectangles);

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

			for (int leafHeight = roomLeaf.y; leafHeight < roomLeaf.height + roomLeaf.y; leafHeight++) {
				Gdx.app.log(BSPGenerator.class.getSimpleName(), "test");

				for (int leafWidth = roomLeaf.x; leafWidth < roomLeaf.width + roomLeaf.x; leafWidth++) {
					Gdx.app.log(BSPGenerator.class.getSimpleName(), "test");

					Gdx.app.log(BSPGenerator.class.getSimpleName(), leafHeight + " " + leafWidth + " " + roomLeaf.toString());
					dungeon.setTile(leafWidth, leafHeight, tileset.getFloor1());
					dungeon.setCollision(leafWidth, leafHeight, true);
					Gdx.app.log(BSPGenerator.class.getSimpleName(), "Set collision @" + leafWidth + " " + leafHeight);

				}
			}

		}

	}

private void printDungeons(Dungeon dungeon, ArrayList<Leaf> rectangles) {
        byte [][] lines = new byte[dungeon.getxSize()][];
        for( int i = 0; i < dungeon.getxSize(); i++ ) {
            lines[ i ] = new byte[dungeon.getySize()];
            for( int j = 0; j < dungeon.getySize(); j++ )
                lines[ i ][ j ] =  -1;
        }
        byte dungeonCount = -1;
        for( Leaf r : rectangles ) {
            if( r.room == null )
                continue;
            Leaf d = r.room;
            dungeonCount++;
            for( int i = 0; i < d.height; i++ ) {
                for( int j = 0; j < d.width; j++ )

                    lines[ d.x + i ][ d.y + j ] = dungeonCount;
            }
        }
        for( int i = 0; i < dungeon.getxSize(); i++ ) {
            for( int j = 0; j < dungeon.getySize(); j++ ) {
                if( lines[ i ][ j ] == -1 ){
                    System.out.print( '.');
					dungeon.setTile(i, j, dungeon.getTileSheet().VOID_1);
                }
                else{
                    System.out.print( lines[ i ][ j ] );
                   dungeon.setTile(i, j, dungeon.getTileSheet().FLOOR_1_1);
                   dungeon.setCollision(leafWidth, leafHeight, true);

             	}
            }
            System.out.println();
        }
    }

}
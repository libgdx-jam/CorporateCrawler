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
	       ArrayList<Leaf> Leafs = new ArrayList<Leaf>(); // flat Leaf store to help pick a random one
	        Leaf root = new Leaf( 0, 0, 60, 120 ); //
	        Leafs.add( root  ); //populate Leaf store with root area
	        while( Leafs.size() < 19 ) { // this will give us 10 leaf areas
	            int splitIdx = rnd.nextInt( Leafs.size() ); // choose a random element
	            Leaf toSplit = Leafs.get( splitIdx); 
	            if( toSplit.split() ) { //attempt to split
	                Leafs.add( toSplit.leftChild );
	                Leafs.add( toSplit.rightChild );
	            } 

	        }
	        root.generateDungeon(); //generate dungeons

	        printDungeons(Leafs); //this is just to test the output
	}

	private void convert(Dungeon dungeon, ArrayList<Leaf> Leafs) {

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

		Gdx.app.log(BSPGenerator.class.getSimpleName(), "Number of leafs created: " + Leafs.size());
		int leafCount = 0;
		for (int l = 0; l < Leafs.size(); l++) {
			// Gdx.app.log(BSPGenerator.class.getSimpleName(), "Reached room @ "
			// + leaf.x + " " + leaf.y);
			Leaf leaf = Leafs.get(l);
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

	private void printDungeons(ArrayList<Leaf> leafs) {
		for(Leaf l: leafs){
			if(l.room != null){
				for(int y = 0; y < l.height; y++){
					for (int x = 0; x < l.width; x++){
						dungeon.setTile(l.x+x, l.y+y, dungeon.getTileSheet().getFloor2());
					}	
				}
			}
		}
	}

}
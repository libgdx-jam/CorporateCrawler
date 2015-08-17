package com.porkopolis.crawler.generators;

import java.util.HashMap;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.porkopolis.crawler.dungeon.Tile;
import com.porkopolis.crawler.utils.Location2;

public class BSPGenerator implements DungeonGenerator {

	private HashMap<Location2, Tile> tiles = new HashMap<Location2, Tile>();

	@Override
	public void createDungeon(int width, int height) {
		Array<Node> nodes = new Array<Node>();

		Node root = new Node(0, 0, width, height);
		nodes.add(root);

		for (int x = 0; x++ < 20;) {
			Node toSplit = nodes.get(MathUtils.random(nodes.size));
			if (toSplit.split()) {
				nodes.addAll(toSplit.getLeftChild(), toSplit.getRightChild());
			}
		}

		for (Node node : nodes) {

		}
	}

	@Override
	public HashMap<Location2, Tile> returnDungeon() {
		if (tiles != null)
			return tiles;
		else
			throw new NullPointerException();
	}

}

package com.porkopolis.crawler.generators;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.porkopolis.crawler.dungeon.Tile;
import com.porkopolis.crawler.dungeon.Tiles;

public class Node {

	private int minSize = 3;

	private int x;
	private int y;
	private int width;
	private int height;

	private Node leftChild;
	private Node rightChild;

	public Node(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean split() {
		// Already split this node
		if (leftChild != null || rightChild != null) {
			return false;
		}

		// Too small to split
		if (width < minSize * 2 || height < minSize * 2) {
			return false;
		}

		boolean horizontal = MathUtils.randomBoolean();

		// Pick a length between minimum size of one node and maximum
		int split = horizontal ? MathUtils.random(minSize, width - minSize)
				: MathUtils.random(minSize, height - minSize);

		if (horizontal) {
			// Split vertically
			leftChild = new Node(x, y, split, height);
			rightChild = new Node(x + split, y, width - split, height);
		} else {
			// Split horizontally
			leftChild = new Node(x, y, width, split);
			rightChild = new Node(x, y + split, width, height - split);
		}

		return true;
	}

	public Array<Tile> generate() {
		if (leftChild != null || rightChild != null) {
			Array<Tile> tiles = new Array<Tile>();
			tiles.addAll(leftChild.generate());
			tiles.addAll(rightChild.generate());
			return null;
		} else {

			Array<Tile> tiles = new Array<Tile>();

			for (int x2 = x; x2 < x + width; x2++) {
				for (int y2 = y; y2 < y + height; y2++) {
					tiles.add(new Tile(x2, y2, Tiles.VOID));
				}
			}
			return tiles;
		}
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

}

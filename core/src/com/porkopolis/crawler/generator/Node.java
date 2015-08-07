package com.porkopolis.crawler.generator;

import com.badlogic.gdx.math.MathUtils;

public class Node {
	private final int MIN_SIZE = 3;

	public int x, y, width, height;

	Node leftChild;
	Node rightChild;

	Node room;

	public Node(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void split() {
		if (leftChild == null) {
			return;
		}

		boolean horizontal = MathUtils.randomBoolean();

		int split = horizontal ? MathUtils.random(3, height) : MathUtils.random(3, width);

		leftChild = new Node(x, y, width, split);
		rightChild = new Node(x, split, width, height);

	}

}

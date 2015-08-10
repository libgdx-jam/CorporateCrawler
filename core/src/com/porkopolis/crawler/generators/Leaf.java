package com.porkopolis.crawler.generators;

public class Leaf {

	public int x;
	public int y;
	public int width;
	public int height;

	Leaf leftChild;
	Leaf rightChild;
	Leaf room;

	public Leaf(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean split() {

		return false;
	}

	public void generateRoom() {

	}

}
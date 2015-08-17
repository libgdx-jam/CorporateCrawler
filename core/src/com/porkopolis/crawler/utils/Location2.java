package com.porkopolis.crawler.utils;

public class Location2 {

	private int x;
	private int y;

	public Location2() {
		this(0, 0);
	}

	public Location2(Location2 location) {
		this(location.x, location.y);
	}

	public Location2(int x, int y) {
		this.x = x;
		this.y = y;
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

}

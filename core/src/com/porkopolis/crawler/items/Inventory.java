package com.porkopolis.crawler.items;

import java.util.HashMap;

public class Inventory {

	private HashMap<Integer, Item> items = new HashMap<Integer, Item>();

	public Inventory() {

	}

	public Item getItem(int id) {
		return items.get(id);

	}

	public void removeItem(int id) {
		items.remove(id);
	}

	public HashMap<Integer, Item> getItems() {
		return items;
	}

}

package com.porkopolis.crawler.generators;

import java.util.HashMap;

import com.porkopolis.crawler.dungeon.Tile;
import com.porkopolis.crawler.utils.Location2;

public interface DungeonGenerator {

	public void createDungeon(int width, int height);

	public HashMap<Location2, Tile> returnDungeon();

}

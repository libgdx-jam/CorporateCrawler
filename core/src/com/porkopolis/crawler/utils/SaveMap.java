package com.porkopolis.crawler.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class SaveMap {
	public static void saveDungeon(Dungeon dungeon, String fileName) {
		dungeon.printTileLayer();
		CreatePolyline q = new CreatePolyline();
		CreatePolyline.createPolyline(dungeon);
		String dungeonFile = "" + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "	<map version=\"1.0\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"100\" height=\"100\" tilewidth=\"32\" tileheight=\"32\" nextobjectid=\"22\">\n"
				+ "		<tileset firstgid=\"1\" name=\"Office01\" tilewidth=\"32\" tileheight=\"32\">\n" + "			<image source=\"tilesets/" + dungeon.getTileSet() + "\" trans=\"00ff7f\" width=\"320\" height=\"320\"/>\n"
				+ "		 </tileset>\n" + "	<layer name=\"tileLayer\" width=\"100\" height=\"100\">\n" + "		<data encoding=\"csv\">\n";

		for (int i = 0; i < dungeon.getTileLayer().length; i++) {
			if (i == 0)
				dungeonFile += "			";
			if (i % 100 == 0 && i != 0)
				dungeonFile += "\n 			";

			dungeonFile += dungeon.getTileLayer()[i];

			if (i != dungeon.getTileLayer().length - 1)
				dungeonFile += ",";
		}
		dungeonFile += "\n		</data>\n" + "	</layer>\n";
		int objectID = 0;
		dungeonFile += "	<objectgroup name=\"collisionLayer\">\n";
		Array<String> polyLines = CreatePolyline.createPolyline(dungeon);
		for (int i = 0; i < polyLines.size; i++) {
			dungeonFile += polyLines.get(i);
		}
		dungeonFile += "	</objectgroup>\n";

		dungeonFile += "</map>";
		FileHandle file = Gdx.files.local("Maps/" + fileName);
		file.writeString(dungeonFile, false);
	}
}

package com.porkopolis.crawler.utils;

import com.badlogic.gdx.utils.Array;

public class CreatePolyline {
	public static Array<String> createPolyline(Dungeon dungeon) {
		Array<String> polyLines = new Array<String>();
		boolean last = false;
		int count = 0, x = 0, y = 0, poyStartX = 0, poyStartY = 0, id = 1;

		for (int i = 0; i < dungeon.getCollisionLayer().length; i++) {
			x = i % dungeon.getWidth();
			if (x == 0)
				y++;

			if (dungeon.getCollisionLayer()[i] == false && last == false) {
				last = false;
			} else if (dungeon.getCollisionLayer()[i] == true && last == true) {
				last = true;
				count++;
			} else if (dungeon.getCollisionLayer()[i] == true && last == false) {
				last = true;
				poyStartX = x * 32;
				poyStartY = (y - 1) * 32;
				count = 1;
			} else if (dungeon.getCollisionLayer()[i] == false && last == true) {
				last = false;
				polyLines
						.add("" + "		 <object id=\"" + id + "\" x=\"" + poyStartX + "\" y=\"" + poyStartY + "\">\n" + "			<polyline points=\"0,0 " + count * 32 + ",0 " + count * 32 + ",32 0,32 0,0\"/>\n" + "		  </object>\n");
				id++;
			}
		}
		return polyLines;
	}
}

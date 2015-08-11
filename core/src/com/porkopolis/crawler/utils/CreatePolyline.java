package com.porkopolis.crawler.utils;

import com.badlogic.gdx.utils.Array;

public class CreatePolyline {
	public static Array<String> createPolyline(Dungeon dungeon) {
		Array<String> polyLines = new Array<String>();
		boolean last = false;
		int count = 0, x = 0, y = 0, poyStartX = 0, poyStartY = 0, id = 1;

		for (int yCurrent = 0; yCurrent < dungeon.getHeight(); yCurrent++) {
			for (int xCurrent = 0; x < dungeon.getWidth(); xCurrent++) {
				System.out.println(yCurrent+" "+xCurrent);
				if (dungeon.getCollision(yCurrent, xCurrent) == false && last == false) {
					last = false; 
				} else if (dungeon.getCollision(yCurrent, xCurrent) == true && last == true) {
					last = true;
					count++;
				} else if (dungeon.getCollision(yCurrent, xCurrent) == true && last == false) {
					last = true;
					poyStartX = x * 32;
					poyStartY = (y - 1) * 32;
					count = 1;
				} else if (dungeon.getCollision(yCurrent, xCurrent) == false && last == true) {
					last = false;
					polyLines
							.add("" + "		 <object id=\"" + id + "\" x=\"" + poyStartX + "\" y=\"" + poyStartY + "\">\n" + "			<polyline points=\"0,0 " + count * 32 + ",0 " + count * 32 + ",32 0,32 0,0\"/>\n" + "		  </object>\n");
					id++;
				}
			}
		}
		return polyLines;
	}
}

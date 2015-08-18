package com.porkopolis.crawler;

import com.badlogic.gdx.Game;
import com.porkopolis.crawler.screens.GameScreen2;

public class Main extends Game {

	@Override
	public void create() {
		Assets.load();
		Assets.create();
		GameManager.init(this);
		GameManager.setScreen(new GameScreen2());
	}

}

package com.porkopolis.crawler.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class GUI {

	private Stage stage;
	private Table table;
	private Skin skin;
	public boolean debug = true;
	private Label fpsLabel;
	private int fps = 0;

	public GUI() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		/** Begin table */

		table = new Table(skin);
		table.setFillParent(true);
		table.debug();

		fpsLabel = new Label("fps" + fps, skin);

		TextButton button = new TextButton("1", skin);
		TextButton button2 = new TextButton("2", skin);
		TextButton button3 = new TextButton("3", skin);
		TextButton button4 = new TextButton("4", skin);

		table.add(fpsLabel).expand(30, 10).align(Align.bottom).fill(0.8f, 0);
		table.add(button).expand(30, 10).align(Align.bottom).fill(0.8f, 0);
		table.add(button2).expand(30, 10).align(Align.bottom).fill(0.8f, 0);
		table.add(button3).expand(30, 10).align(Align.bottom).fill(0.8f, 0);
		table.add(button4).expand(30, 10).align(Align.bottom).fill(0.8f, 0);
		table.add("").expand(30, 10).align(Align.bottom).fill(0.8f, 0);

		/** End table */

		stage.addActor(table);
	}

	public void update(float delta) {
		if (debug) {
			table.debug();
		} else {
			table.setDebug(false);
		}
		stage.act();
		stage.draw();
		fpsLabel.setText("fps: " + Gdx.graphics.getFramesPerSecond());
	}

	public Stage getStage() {
		return stage;
	}

	public void resize(int width, int height) {
		// stage.getViewport().setScreenSize(width, height);
	}

	public void dispose() {
		stage.dispose();
	}

}

package com.porkopolis.crawler.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin.TintedDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.porkopolis.crawler.Assets;

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
		skin.addRegions(Assets.getAtlas());

		/** Begin table */

		table = new Table(skin);
		table.setFillParent(true);
		table.debug();

		fpsLabel = new Label("fps" + fps, skin);

		ImageButtonStyle imgBtnStyle = new ImageButtonStyle();
		imgBtnStyle.up = skin.getDrawable("button");
		imgBtnStyle.down = skin.getDrawable("button");

		ImageButton button = new ImageButton(imgBtnStyle);
		ImageButton button2 = new ImageButton(imgBtnStyle);
		ImageButton button3 = new ImageButton(imgBtnStyle);
		ImageButton button4 = new ImageButton(imgBtnStyle);

		table.defaults().fill(0.8f, 0f).expand(30, 20);

		table.add(fpsLabel).align(Align.bottom);
		table.add(button).align(Align.bottom);
		table.add(button2).align(Align.bottom);
		table.add(button3).align(Align.bottom);
		table.add(button4).align(Align.bottom);
		table.add("").align(Align.bottom);

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
		if (debug)
			fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
		else
			fpsLabel.setText("       ");
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

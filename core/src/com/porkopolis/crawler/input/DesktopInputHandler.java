package com.porkopolis.crawler.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.porkopolis.crawler.entitys.player.Player;
import com.porkopolis.crawler.gui.GUI;

public class DesktopInputHandler implements InputProcessor {

	Player player;
	GUI gui;
	OrthographicCamera camera;

	public DesktopInputHandler(GUI gui, Player player, OrthographicCamera camera) {
		this.player = player;
		this.gui = gui;
		this.camera = camera;

	}

	/** For polling */
	public void update() {
		// Begin debug
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			gui.debug = !gui.debug;
		}

		if (Gdx.input.isKeyJustPressed(Keys.G)) {
			for (int i = 0; i < player.getBody().getFixtureList().size; i++) {
				player.getBody()
						.getFixtureList()
						.get(i)
						.setSensor(
								!player.getBody().getFixtureList().get(i)
										.isSensor());
			}
		}
		// End debug

		// Begin player controls
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.getBody().applyForce(
					new Vector2(MathUtils.cos(player.getRotation()),
							MathUtils.sin(player.getRotation())),
					player.getBody().getWorldCenter(), true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.getBody().applyForce(
					new Vector2(-MathUtils.cos(player.getRotation()),
							-MathUtils.sin(player.getRotation())),
					player.getBody().getWorldCenter(), true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.getBody().applyForce(
					new Vector2(-MathUtils.cos(player.getRotation())
							+ MathUtils.PI / 4, -MathUtils.sin(player
							.getRotation())),
					player.getBody().getWorldCenter(), true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.getBody().applyForce(
					new Vector2(-MathUtils.cos(player.getRotation())
							- MathUtils.PI / 4, -MathUtils.sin(player
							.getRotation())),
					player.getBody().getWorldCenter(), true);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			player.setSpeed(player.getBaseSpeed() * 2);
			
		}
		
//		else {
//			player.setSpeed(player.getBaseSpeed());
//		}

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			player.getBody().applyForce(
					new Vector2(MathUtils.cos(player.getRotation()),
							MathUtils.sin(player.getRotation())),
					player.getBody().getWorldCenter(), true);
		}

		// End player controls
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector3 mouse = camera.unproject(new Vector3(screenX, screenY, 0));
		player.setRotation(MathUtils.atan2(mouse.y
				- player.getBody().getPosition().y, mouse.x
				- player.getBody().getPosition().x));
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}

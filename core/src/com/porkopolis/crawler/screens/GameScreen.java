package com.porkopolis.crawler.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.porkopolis.crawler.Assets;
import com.porkopolis.crawler.EntityManager;
import com.porkopolis.crawler.GameManager;
import com.porkopolis.crawler.entitys.player.Player;
import com.porkopolis.crawler.generators.BSPGenerator;
import com.porkopolis.crawler.gui.GUI;
import com.porkopolis.crawler.input.DesktopInputHandler;
import com.porkopolis.crawler.utils.Dungeon2;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class GameScreen implements Screen {
	private OrthographicCamera camera;

	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	private EntityManager entityManager = new EntityManager();

	private SpriteBatch batch;

	private Box2DDebugRenderer renderer;

	private GUI gui = new GUI();

	private Player player;

	private InputMultiplexer input;
	private DesktopInputHandler desktop;

	RayHandler rayHandler;

	@Override
	public void show() {
		float w = 32;
		float h = 20;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.translate(50, 50);
		camera.update();

		BSPGenerator generator = new BSPGenerator();
		generator.createDungeon(100, 100);
		Dungeon2 dungeon = new Dungeon2(generator.returnDungeon());

		Vector2 start = new Vector2(50f, 50f);
		player = new Player(start, GameManager.getWorld());

		Vector2 pos = new Vector2();

		entityManager.getEntitys().add(player);

		batch = new SpriteBatch(100);

		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);

		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);

		rayHandler = new RayHandler(GameManager.getWorld());

		rayHandler.setAmbientLight(0.5f, 0.5f, 0.5f, 0.2f);
		rayHandler.setShadows(true);
		ConeLight light = new ConeLight(rayHandler, 128, null, 16f, 0, 0, 0f, player.getRotation());
		light.attachToBody(player.getBody(), 0, 0, 0);
		new PointLight(rayHandler, 20, new Color(1, 1, 1, 1), 50, 10, 10);

		input = new InputMultiplexer();
		input.addProcessor(gui.getStage());
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			desktop = new DesktopInputHandler(gui, player, camera);
			input.addProcessor(desktop);
		}
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(10000f, 1000f, 1000f, 1000f);

		if (Gdx.app.getType() == ApplicationType.Desktop) {
			desktop.update(delta);
		}

		camera.position.set(player.getBody().getPosition(), 0);
		camera.update();

		GameManager.getWorld().step(1 / 60f, 6, 2);

		player.update(delta);
		entityManager.update(delta);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(Assets.player.reg, player.getBody().getPosition().x - 0.5f, player.getBody().getPosition().y - 0.5f, 0.5f, 0.5f, 1.78125f, 0.875f, 1, 1, (player.getRotation()) * MathUtils.radDeg);
		batch.end();

		rayHandler.setCombinedMatrix(camera);
		rayHandler.render();

		if (gui.debug == true)
			renderer.render(GameManager.getWorld(), camera.combined);

		gui.update(delta, player);

	}

	@Override
	public void resize(int width, int height) {
		gui.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		tiledMap.dispose();
		gui.dispose();
		rayHandler.dispose();
	}

}

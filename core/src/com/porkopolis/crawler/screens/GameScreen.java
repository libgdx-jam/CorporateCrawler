package com.porkopolis.crawler.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.porkopolis.crawler.Assets;
import com.porkopolis.crawler.DungeonManager;
import com.porkopolis.crawler.EntityManager;
import com.porkopolis.crawler.entitys.Entity;
import com.porkopolis.crawler.entitys.StaticEntity;
import com.porkopolis.crawler.entitys.player.Player;
import com.porkopolis.crawler.gui.GUI;
import com.porkopolis.crawler.input.DesktopInputHandler;
import com.porkopolis.crawler.utils.Dungeon;
import com.porkopolis.crawler.utils.DungeonGenerator;
import com.porkopolis.crawler.utils.MapBodyBuilder;
import com.porkopolis.crawler.utils.SaveMap;

public class GameScreen implements Screen {
	private OrthographicCamera camera;

	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	private World world;
	private Body body;

	private EntityManager entityManager = new EntityManager();

	private SpriteBatch batch;

	private Box2DDebugRenderer renderer;

	private GUI gui = new GUI();

	private Player player;

	private InputMultiplexer input;
	private DesktopInputHandler desktop;

	@Override
	public void show() {
		float w = 32;
		float h = 20;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.translate(50, 50);
		camera.update();

		DungeonManager.dungeon = new Dungeon(100, 100, MathUtils.random(150,
				200), "Office01.png");
		DungeonGenerator.createDungeon(DungeonManager.dungeon);
		SaveMap.saveDungeon(DungeonManager.dungeon, "test.tmx");

		tiledMap = new TmxMapLoader().load("Maps/test.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 0.03125f);

		world = new World(new Vector2(0, 0), true);

		Array<Body> bodies = MapBodyBuilder.buildShapes(tiledMap, 32, world);
		Vector2 start = DungeonManager.getFree();
		start.add(0.5f, 0.5f);
		player = new Player(start, world);
		entityManager.getEntitys().add(player);

		// StaticEntity entity2 = new StaticEntity(start, world);

		for (int x = 0; x < 100; x++) {
			Vector2 c = DungeonManager.getFree();
			c.add(0.5f, 0.5f);

			StaticEntity entity = new StaticEntity(c, world);
			entityManager.getEntitys().add(entity);

			System.out.println(c.toString());
		}

		batch = new SpriteBatch(100);

		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);

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
		Gdx.gl.glClearColor(1, 0, 0, 1);

		if (Gdx.app.getType() == ApplicationType.Desktop) {
			desktop.update(delta);
		}

		camera.position.set(player.getBody().getPosition(), 0);
		camera.update();

		world.step(1 / 60f, 6, 2);
		player.update(delta);
		for (Entity e : entityManager.getEntitys()) {
			if (!(e instanceof Player)) {
				e.update(delta);
			}
		}

		tiledMapRenderer.setView(camera);
		if (gui.debug != true)
			tiledMapRenderer.render();
		if (gui.debug == false) {
			batch.setProjectionMatrix(camera.combined);
			batch.begin();

			batch.draw(Assets.player.reg,
					player.getBody().getPosition().x - 0.5f, player.getBody()
							.getPosition().y - 0.5f, 0.5f, 0.5f, 1.78125f,
					0.875f, 1, 1, (player.getRotation()) * MathUtils.radDeg);
			batch.end();
		}
		if (gui.debug == true)
			renderer.render(world, camera.combined);

		gui.update(delta);

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
		gui.dispose();
	}

}

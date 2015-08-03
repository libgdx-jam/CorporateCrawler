package com.porkopolis.crawler;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

	public static final String TAG = Assets.class.getSimpleName();

	public static boolean rebuildAtlas = true;
	public static boolean drawDebugOutline = false;

	public static boolean loaded = false;

	public static AssetManager manager;
	public static AssetSquare square;
	public static AssetPlayer player;

	private static TextureAtlas atlas;

	public static AssetManager getManager() {
		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}

	public static final String TEXTURE_ATLAS_OBJECTS = "assets.atlas";
	public static final String SKIN = "skin/uiskin.json";

	public static Skin skin;

	public static void load() {
		getManager(); // Insure the manager exists
		manager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		manager.load(SKIN, Skin.class);
		manager.finishLoading();
		loaded = true;
	}

	public static void create() {
		atlas = manager.get(TEXTURE_ATLAS_OBJECTS);
		square = new AssetSquare(atlas);
		player = new AssetPlayer(atlas);
		skin = manager.get(SKIN);

	}

	public static class AssetSquare {
		public AtlasRegion reg;

		public AssetSquare(TextureAtlas atlas) {
			reg = atlas.findRegion("square");
		}
	}

	public static class AssetPlayer {
		public AtlasRegion reg;

		public AssetPlayer(TextureAtlas atlas) {
			reg = atlas.findRegion("body");
		}
	}
	
	public static class AssetLegs {
		public AtlasRegion reg;

		public AssetLegs(TextureAtlas atlas) {
			reg = atlas.findRegion("body");
		}
	}

	@Override
	public void dispose() {
		manager.dispose();
	}

	public static TextureAtlas getAtlas() {
		return atlas;
	}
}
package com.porkopolis.crawler.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.porkopolis.crawler.entitys.player.Player;

// https://www.youtube.com/watch?v=CqCz13qWd7A
public class RayCast {

	Vector2 start = new Vector2();
	Vector2 end = new Vector2();
	Vector2 collision = new Vector2();
	Vector2 normal = new Vector2();

	World world;

	public RayCast(World world) {
		this.world = world;
	}

	RayCastCallback callback = new RayCastCallback() {
		@Override
		public float reportRayFixture(Fixture fixture, Vector2 point,
				Vector2 normal, float fraction) {

			if (fixture.getUserData() instanceof Player) {
				Gdx.app.log(this.getClass().getSimpleName(),
						"Eat that mothafucker!");
			}

			return 0;
		}
	};

	public void rayCast(Vector2 start, Vector2 end) {
		this.start = start;
		this.end = end;
		world.rayCast(callback, start, end);

	}

	public void rayCast(Body origin, Body target) {
		rayCast(origin.getPosition(), target.getPosition());
	}

}

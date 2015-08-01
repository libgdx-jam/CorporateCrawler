package com.porkopolis.crawler.entitys.player;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.porkopolis.crawler.entitys.Entity;

public class Player implements Entity {
	private Vector2 position;
	private Body body;
	private float baseSpeed = 1f;
	private float speed = 1f;
	private float rotation = 0;

	public Player(Vector2 startPosition, World world) {
		this.position = startPosition;
		BodyDef def = new BodyDef();
		def.position.set(startPosition);
		def.type = BodyType.DynamicBody;
		def.angularDamping = 1.0f;
		def.linearDamping = 1.0f;

		body = world.createBody(def);

		CircleShape cir = new CircleShape();
		cir.setRadius(0.3f);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = cir;
		fDef.density = 0.5f;
		fDef.friction = 0.0f;
		fDef.restitution = 0.01f;

		Fixture fixture = body.createFixture(fDef);
	}

	@Override
	public void update(float delta) {

	}

	public Body getBody() {
		return body;
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	public float getSpeed() {
		return speed;
	}

	public float getBaseSpeed() {
		return baseSpeed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getRotation() {
		return body.getAngle();
	}

	public void setRotation(float rotation) {
		body.setTransform(body.getPosition().x, body.getPosition().y, rotation);
	}

	@Override
	public void dispose() {
		for (Fixture fixture : body.getFixtureList()) {
			body.destroyFixture(fixture);
		}
	}

	public void move(Vector2 vector) {
		getBody().applyForce(vector, getBody().getWorldCenter(), true);
	}

}

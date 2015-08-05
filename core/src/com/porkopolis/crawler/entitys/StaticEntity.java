package com.porkopolis.crawler.entitys;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class StaticEntity implements Entity {

	private Vector2 position;
	private Body body;

	public StaticEntity(Vector2 startPosition, World world) {
		this.position = startPosition;
		BodyDef def = new BodyDef();
		def.position.set(startPosition);
		def.type = BodyType.StaticBody;
		def.angularDamping = 0f;
		def.linearDamping = 0f;

		body = world.createBody(def);

		CircleShape cir = new CircleShape();
		cir.setRadius(0.5f);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = cir;
		fDef.density = 1f;
		fDef.friction = 0.0f;
		fDef.restitution = 0.0f;

		Fixture fixture = body.createFixture(fDef);
		body.setUserData(this);
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		for (Fixture fixture : body.getFixtureList()) {
			body.destroyFixture(fixture);
		}
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAngle(float f) {
		// TODO Auto-generated method stub
		
	}

}

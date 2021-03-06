package com.porkopolis.crawler;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.utils.Array;
import com.porkopolis.crawler.entitys.Entity;

public class EntityManager {
	private Array<Entity> entities = new Array<Entity>();

	public Array<Entity> getEntitys() {
		return entities;
	}

	public void update(float delta) {
		for (Entity e : entities) {
			// if (!(e instanceof Player)) Why? -nate 8/4/2015
			e.update(delta);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public Entity getEnityt(int index) {
		return entities.get(index);
	}

	public void removeEntity(int index) {
		entities.removeIndex(index);
	}

	public int findEntity(Entity entityToFind) {
		for (int i = 0; i < entities.size; i++) {
			if (entityToFind == entities.get(i)) {
				return i;
			}
		}
		return -1;
	}

	// Save this for later -nate 8/4/2015
	public void interpolate(float alpha) {
		for (Entity entity : entities) {
			Transform transform = entity.getBody().getTransform();
			Vector2 bodyPosition = transform.getPosition();
			Vector2 position = entity.getPosition();
			float angle = entity.getBody().getAngle();
			float bodyAngle = transform.getRotation();

			position.x = bodyPosition.x * alpha + position.x * (1.0f - alpha);
			position.y = bodyPosition.y * alpha + position.x * (1.0f - alpha);
			entity.setAngle(bodyAngle * alpha + angle * (1.0f - alpha));
		}
	}
}

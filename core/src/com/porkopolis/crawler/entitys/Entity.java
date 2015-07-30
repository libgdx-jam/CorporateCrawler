package com.porkopolis.crawler.entitys;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public interface Entity extends Disposable {

	public Vector2 getPosition();

	public void update(float delta);

	@Override
	public void dispose();

}

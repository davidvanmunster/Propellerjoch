package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;
import propellerjoch.tiles.FloorTile;

public class Spin extends Monsters implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Propellerjoch pj;

	public Spin(Sprite sprite, Propellerjoch pj, float beginPunt, float eindPunt, float speed) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("zombie.png")), pj, beginPunt, eindPunt, speed);
		float gravity = 0.15f;
		setGravity(gravity);
		this.pj = pj;
	}

	@Override
	public void update() {
		final float speed = 2f;
		if (x < 710) {
			setDirectionSpeed(90, speed);
		}
		if (x > 1000) {
			setDirectionSpeed(270, speed);
		}
	}
}
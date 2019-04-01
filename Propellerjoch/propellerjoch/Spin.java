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
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("spin.png")), pj, beginPunt, eindPunt, speed);
		this.pj = pj;
	}
	
	@Override
	public void update() {	
		if (y < beginPunt) {
			setDirectionSpeed(180, speed);
		}
		if (y > eindPunt) {
			setDirectionSpeed(0, speed);
		}
	}
}
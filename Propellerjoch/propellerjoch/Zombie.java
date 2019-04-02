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

public class Zombie extends Monsters implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Propellerjoch pj;

	public Zombie(Sprite sprite, Propellerjoch pj, float beginPunt, float eindPunt, float speed, Player player) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("zombie.png")), pj, beginPunt, eindPunt, speed, player);
		float gravity = 0.15f;
		setGravity(gravity);
		this.pj = pj;
	}
}
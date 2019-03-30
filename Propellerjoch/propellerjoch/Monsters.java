package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;
import propellerjoch.tiles.FloorTile;

public class Monsters extends SpriteObject {
	private Propellerjoch pj;
	
	public Monsters(Sprite sprite) {
		super(sprite);
		this.pj = pj;
	}

	@Override
	public void update() {
		
	}
}

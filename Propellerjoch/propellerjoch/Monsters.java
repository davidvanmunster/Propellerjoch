package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;
import processing.core.PVector;
import propellerjoch.tiles.FloorTile;

public class Monsters extends SpriteObject {
	private Propellerjoch pj;
	private Player player;
	
	public Monsters(Sprite sprite, Propellerjoch pj) {
		super(sprite);
		this.pj = pj;
	}

	@Override
	public void update() {
		
	}
	
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.getTile() instanceof FloorTile) {
				try {
					vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
					this.setY(vector.y - getHeight());
				} catch (TileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go: collidedGameObjects) {
			if (go instanceof Player) {		
				if ((go.getY()+go.getHeight()) <= this.getY()) {
					pj.deleteGameObject(this);
				}
				else {
					System.out.println("Speler dood g");
				}
			}
		}
	}
}

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

public abstract class Monster extends SpriteObject {
	private Propellerjoch pj;
	protected Player player;
	
	protected float speed;
	protected float beginPunt;
	protected float eindPunt;
	
	/**
	 * Constructor
	 * 
	 * @param sprite		- De sprite van desbetreffende monster
	 * @param pj			- Referentie naar de wereld
	 * @param beginPunt		- Het beginpunt van het loopgebied (x voor zombies, y voor spinnen)
	 * @param eindPunt		- Het eindpunt van het loopgebied
	 * @param speed			- De snelheid van het monster
	 * @param player		- Referentie naar de speler
	 */
	public Monster(Sprite sprite, Propellerjoch pj, float beginPunt, float eindPunt, float speed, Player player) {
		super(sprite);
		this.pj = pj;
		this.player = player;
		this.beginPunt = beginPunt;
		this.eindPunt = eindPunt;
		this.speed = speed;
	}

	public void update() {	
		if (x < beginPunt) {
			setDirectionSpeed(90, speed);
		}
		if (x > eindPunt) {
			setDirectionSpeed(270, speed);
		}
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
	
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {/*
		for (GameObject go: collidedGameObjects) {
			if (go instanceof Player) {		
				if ((go.getY()+go.getHeight()) <= this.getY()) {
					pj.deleteGameObject(this);
					player.springen();
				}
				else {
					System.out.println("Speler dood g");
				}
			}
		}*/
	}
}

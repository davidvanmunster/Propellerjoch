package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import processing.core.PVector;
import propellerjoch.tiles.FloorTile;

public class Player extends SpriteObject implements ICollidableWithTiles {
	private Propellerjoch pj;

	private boolean springen = false, vallen = false;

	// Springsnelheid
	private float springSnelheid = 5;
	private float huidigeSpringSnelheid = springSnelheid;

	public Player(Propellerjoch pj) {
		// Met `.concat()` plak je 2 strings aan elkaar.
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("player.png")));
		float gravity = 0.15f;
		setGravity(gravity);
		this.pj = pj;
	}

	@Override
	public void update() {
		if (springen) {
			vallen = true;
			setDirectionSpeed(0, huidigeSpringSnelheid);

			if (getySpeed() <= 0.01) {
				springen = false;
				huidigeSpringSnelheid = springSnelheid;
			}
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		final int speed = 3;
		if (keyCode == pj.LEFT) {
			setDirectionSpeed(270, speed);
		}
		if (keyCode == pj.RIGHT) {
			setDirectionSpeed(90, speed);
		}
		if (keyCode == pj.UP && !vallen) {
			springen = true;
		}
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		final int speed = 0;
		if (keyCode == pj.LEFT) {
			setDirectionSpeed(270, speed);
		}
		if (keyCode == pj.RIGHT) {
			setDirectionSpeed(90, speed);
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.getTile() instanceof FloorTile) {
				if (CollisionSide.TOP.equals(ct.getCollisionSide())) {
					try {
						vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
						setY(vector.y - getHeight());
						vallen = false;
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
				}
				if (CollisionSide.LEFT.equals(ct.getCollisionSide())) {
					try {
						vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
						setX(vector.x - getWidth());
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
				}
				if (CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
					try {
						vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
						setX(vector.x + getWidth());
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
				}

			}
		}

	}
}

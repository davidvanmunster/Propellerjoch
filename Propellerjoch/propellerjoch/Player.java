package propellerjoch;

import java.util.ArrayList;
import java.util.List;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import processing.core.PVector;
import propellerjoch.tiles.FloorTile;

public class Player extends SpriteObject implements ICollidableWithTiles {
	private Propellerjoch pj;

	private boolean springen = false, collisionWithFloor = false;

	ArrayList<Toets> toets = new ArrayList<Toets>();

	// Springsnelheid
	private float springSnelheid = 10;
	Toets keyUp = new Toets(38);
	Toets keyDown = new Toets(40);
	Toets keyLeft = new Toets(37);
	Toets keyRight = new Toets(39);
	float gravity = 3.5f;

	public Player(Propellerjoch pj) {
		// Met `.concat()` plak je 2 strings aan elkaar.
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("player.png")));
		this.pj = pj;

		toets.add(keyUp);
		toets.add(keyDown);
		toets.add(keyLeft);
		toets.add(keyRight);
	}

	@Override
	public void update() {
		final int speed = 3;
		final int stop = 0;

		setGravity(gravity);

		while (springen) {

		}

		System.out.println(collisionWithFloor);

		if (keyUp.getIngedrukt() && collisionWithFloor) {
			collisionWithFloor = true;
//			setDirectionSpeed(0, springSnelheid);
			setY(getY() - 15);
//			if (getySpeed() <= 0.01) {
//				setGravity(3.5f);
//				springen = false;
//			}
		}
		if (keyRight.getIngedrukt()) {
			setDirectionSpeed(90, speed);
		}
//		else if (keyDown.getIngedrukt()) {
//			setDirectionSpeed(180, speed);
//		}
		if (keyLeft.getIngedrukt()) {
			setDirectionSpeed(270, speed);
		}
		if (!keyLeft.getIngedrukt() ^ keyRight.getIngedrukt()) {
			setDirectionSpeed(0, stop);
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		for (Toets t : toets) {
			if (keyCode == t.getKeyCode()) {
				t.setIngedrukt(true);
			}
//			System.out.println(t.getIngedrukt());
		}
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		for (Toets t : toets) {
			if (keyCode == t.getKeyCode()) {
				t.setIngedrukt(false);
			}
//			System.out.println(t.getIngedrukt());
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
//			collisionWithFloor = false;
			if (ct.getTile() instanceof FloorTile) {
				try {
					vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
					setY(vector.y - getHeight());
					collisionWithFloor = true;
				} catch (TileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				collisionWithFloor = false;
			}
		}

	}
}
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
	private boolean raaktGrondAan = false;

	ArrayList<Toets> toets = new ArrayList<Toets>();

	Toets keyUp = new Toets(38);
	Toets keyDown = new Toets(40);
	Toets keyLeft = new Toets(37);
	Toets keyRight = new Toets(39);

	public Player(Propellerjoch pj) {
		// Met `.concat()` plak je 2 strings aan elkaar.
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("player.png")));
		this.pj = pj;

		float gravity = 2.5f;
		setGravity(gravity);
		toets.add(keyUp);
		toets.add(keyDown);
		toets.add(keyLeft);
		toets.add(keyRight);
	}

	@Override
	public void update() {
		final int speed = 3;
		final int springspeed = 50;
		final int stop = 0;

		if (keyUp.getIngedrukt() && raaktGrondAan) {
			setDirectionSpeed(0, springspeed);
			raaktGrondAan = false;
		} else if (keyRight.getIngedrukt()) {
			setDirectionSpeed(90, speed);
		}
//		else if (keyDown.getIngedrukt()) {
//			setDirectionSpeed(180, speed);
//		}
		else if (keyLeft.getIngedrukt()) {
			setDirectionSpeed(270, speed);
		} else if (keyLeft.getIngedrukt() == false || keyRight.getIngedrukt() == false) {
			setDirectionSpeed(0, stop);
		}
		if ((!keyLeft.getIngedrukt() ^ keyRight.getIngedrukt()) && !keyUp.getIngedrukt()) {
			setDirectionSpeed(0, stop);
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		for (Toets t : toets) {
			if (keyCode == t.getKeyCode()) {
				t.setIngedrukt(true);
			}
			System.out.println(t.getIngedrukt());
		}
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		for (Toets t : toets) {
			if (keyCode == t.getKeyCode()) {
				t.setIngedrukt(false);
			}
			System.out.println(t.getIngedrukt());
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.getTile() instanceof FloorTile) {
				try {
					vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
					setY(vector.y - getHeight());
					raaktGrondAan = true;
				} catch (TileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
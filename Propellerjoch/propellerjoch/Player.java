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

	private boolean springen = false, vallen = false;
	
	ArrayList<Toets> toets = new ArrayList<Toets>();

	// Springsnelheid
	private float springSnelheid = 5;
	private float huidigeSpringSnelheid = springSnelheid;
	Toets keyUp = new Toets(38);
	Toets keyDown = new Toets(40);
	Toets keyLeft = new Toets(37);
	Toets keyRight = new Toets(39);

	
	public Player(Propellerjoch pj) {
		// Met `.concat()` plak je 2 strings aan elkaar.
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("player.png")));
		float gravity = 0.5f;
		setGravity(gravity);
		this.pj = pj;
		setSpeed(1);
		
		toets.add(keyUp);
		toets.add(keyDown);
		toets.add(keyLeft);
		toets.add(keyRight);
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
		
		
		if (keyRight.getIngedrukt()) {
			setDirection(90);
		}
		if (keyDown.getIngedrukt()) {
			setDirection(180);
		}
		if (keyLeft.getIngedrukt()) {
			setDirection(270);
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
					vallen = false;
				} catch (TileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}
}

package propellerjoch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import processing.core.PVector;
import propellerjoch.tiles.FloorTile;
import propellerjoch.tiles.PlatformTile;
import propellerjoch.tiles.SpikesTile;
import propellerjoch.tiles.PowerupTile;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {
	private Propellerjoch pj;
	private Checkpoint cp;
	private Random random;
	private Powerup harnas;
	private float gravity = 3f;
	
	public int laatsteKnop = 90;
	public boolean schietVuurbal = false;
	
	protected boolean onsterfelijkheid = false;
	private boolean raaktGrondAan = true;
	private boolean heeftPowerup = false;
	
	ArrayList<Toets> toets = new ArrayList<Toets>();

	Toets keyUp = new Toets(38);
	Toets keyDown = new Toets(40);
	Toets keyLeft = new Toets(37);
	Toets keyRight = new Toets(39);
	Toets S = new Toets(83);

	/**
	 * Constructor
	 * 
	 * @param pj - Referentie naar de wereld
	 * @param cp - Referentie naar de checkpoint class
	 */
	public Player(Propellerjoch pj, Checkpoint cp, Powerup harnas) {
		// Met `.concat()` plak je 2 strings aan elkaar.	// huidige frame index, 0 = player, 1 = harnas, 2 = vuurbal, 3 = onsterfelijk
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("player.png")), 4);
		random = new Random();
		this.pj = pj;
		this.cp = cp;
		this.harnas = harnas;

		setGravity(gravity);
		toets.add(keyUp);
		toets.add(keyDown);
		toets.add(keyLeft);
		toets.add(keyRight);
		toets.add(S);
	}

	@Override
	public void update() {
		final int speed = 3;
		final int stop = 0;

		if (keyUp.getIngedrukt() && raaktGrondAan) {
			springen();
		} else if (keyRight.getIngedrukt()) {
			setDirectionSpeed(90, speed);
			laatsteKnop = 90;
		} else if (keyDown.getIngedrukt()) {
			setDirectionSpeed(180, speed);
		} else if (keyLeft.getIngedrukt()) {
			setDirectionSpeed(270, speed);
			laatsteKnop = 270;
		} else if (!keyLeft.getIngedrukt() || !keyRight.getIngedrukt()) {
			setDirectionSpeed(0, stop);
		} else if (!keyUp.getIngedrukt()) {
			if (getGravity() == 0.3f) {
				setGravity(3f);
			}
		}
		if ((!keyLeft.getIngedrukt() ^ keyRight.getIngedrukt()) && !keyUp.getIngedrukt() && !keyDown.getIngedrukt()) {
			setDirectionSpeed(0, stop);
		}
		
		// Schiet een vuurbal af wanneer de speler bezit over de Vuurbal-powerup
		if (S.getIngedrukt() && getCurrentFrameIndex() == 2) {
			schietVuurbal = true;			
		} else {
			schietVuurbal = false;
		}
		
		if (y > 1200) {
			dood(cp);
		}
	}

	/**
	 * Teleporteert de speler naar de checkpoint.
	 * @param cp De checkpoint waar de speler naar wordt geteleporteerd.
	 */
	public void gaNaarCp(Checkpoint cp) {
		setX(cp.checkpointX);
		setY(cp.checkpointY);
	}

	/**
	 * Laat de speler springen.
	 */
	public void springen() {
		final int springspeed = 75;
		setDirectionSpeed(0, springspeed);
		raaktGrondAan = false;
		if (!raaktGrondAan && getySpeed() == 3f) {
			vliegen();
		}
	}
	
	
	/**
	 * Laat de speler vliegen/zweven.
	 */
	private void vliegen() {
		setGravity(0.3f);
	}

	/**
	 * Er wordt eerst gecheckt of de speler een harnas draagt, is dit het geval gaat het harnas kapot in plaats van dat de speler sterft.
	 * Dit gebeurt er als de speler dood gaat.
	 * @param cp De laatste checkpoint.
	 */
	public void dood(Checkpoint cp) {
		if (getCurrentFrameIndex() == 1 && onsterfelijkheid == false) {
			setCurrentFrameIndex(3);
			onsterfelijkheid = true;
		} else if (onsterfelijkheid == false) {
			gaNaarCp(cp);
			pj.dood.rewind();
			pj.dood.play();
		}
	}
	
	/**
	 * Hier start je weer bij het begin van de game.
	 */
	public void opnieuwLevel() {
		setX(pj.spawnX);
		setY(pj.spawnY);
		cp.setCheckpoint(pj.spawnX, pj.spawnY);
	}
	
	@Override
	public void keyPressed(int keyCode, char key) {
		for (Toets t : toets) {
			if (keyCode == t.getKeyCode()) {
				t.setIngedrukt(true);
			}
		}
		if (keyCode == 82) {
			opnieuwLevel();
		}
		if (keyCode == 67) {
			gaNaarCp(cp);
		}
//		System.out.println(keyCode);
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		for (Toets t : toets) {
			if (keyCode == t.getKeyCode()) {
				t.setIngedrukt(false);
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.getTile() instanceof FloorTile || ct.getTile() instanceof PlatformTile) {
				try {
					vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
					setY(vector.y - getHeight());
					raaktGrondAan = true;
					if (getGravity() == 0.3f) {
						setGravity(3f);
					}
				} catch (TileNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (ct.getTile() instanceof SpikesTile) {
				try {
					dood(cp);
				} catch (TileNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (ct.getTile() instanceof PowerupTile) {
				try {
					geefRandomPowerup();
					heeftPowerup = true;
					
				} catch (TileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void geefRandomPowerup() {
		if (heeftPowerup == false) {
			int powerupType = random.nextInt(2) + 1;
			System.out.println(powerupType);
			if (powerupType == 1) {
				// Geef de speler de powerup: Harnas
				System.out.println("Powerup gekregen: Harnas");
				setCurrentFrameIndex(1);
			}
			
			if (powerupType == 2) {
				// Geef de speler de powerup: Vuurbal
				System.out.println("Powerup gekregen: Vuurbal");
				setCurrentFrameIndex(2);
			}
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go: collidedGameObjects) {
			if (go instanceof Monster) {		
				if ((this.getY()+this.getHeight()) -5 <= go.getY()) {
					pj.deleteGameObject(go);
					springen();
				}
				else {
					System.out.println("Speler dood");
					dood(cp);
				}
			}
		}
		
	}
}

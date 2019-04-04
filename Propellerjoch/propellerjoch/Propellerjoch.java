package propellerjoch;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.EdgeFollowingViewport;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;
import propellerjoch.tiles.FloorTile;
import propellerjoch.tiles.PlatformTile;
import propellerjoch.tiles.SpikesTile;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

/**
 * @author David van Munster & Hidde Koomen
 * Een spel waar je een parkour moet afleggen door monsters te verslaan.
 */

public class Propellerjoch extends GameEngine {

	protected Sound achtergrond;
	protected Sound dood;
	protected Sound gameover;
	
	private Player player;
	private Prinses prinses;
	private Monster zombie;
	private Monster spin;
	private ArrayList<Monster> monsters = new ArrayList<>();
	private ZombieSpawner spawner;
	private TextObject text;
	private Checkpoint cp;
	private Dashboard db;

	protected float spawnX = 200;
	protected float spawnY = 700;
	
	public static String MEDIA_URL = "Propellerjoch/propellerjoch/media/";

	public static void main(String[] args) {
		Propellerjoch pj = new Propellerjoch();

		pj.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 2880;
		int worldHeight = 1200;
		
		initializeSound();
		createObjects();
		initializeTileMap();

		View view = new View(worldWidth, worldHeight);

		setView(view);
		size(worldWidth, worldHeight);
		createViewWithViewport(worldWidth, worldHeight, 1200, 800, 1.2f);
	}
	
	/**
	 * 	Initialiseerd het geluid
	 */
	private void initializeSound() {
		achtergrond = new Sound(this, Propellerjoch.MEDIA_URL.concat("backgroundMusic.mp3"));
        achtergrond.loop(-1);
        dood = new Sound(this, Propellerjoch.MEDIA_URL.concat("death.mp3"));
        gameover = new Sound(this, Propellerjoch.MEDIA_URL.concat("gameover.mp3"));
	}

	/**
	 * 	Creëert de objecten
	 */
	private void createObjects() {
		cp = new Checkpoint(this);
		addGameObject(cp, 1380, 650);
		prinses = new Prinses(this);
		addGameObject(prinses, 700, 270);
		player = new Player(this, cp);
		addGameObject(player, spawnX, spawnY);
		spawner = new ZombieSpawner(this, 1, null);
		addGameObject(spawner);
		
		
		// Voor monsters : type(null, this, beginPunt, eindPunt, speed, player)
		zombie = new Zombie(null, this, 750, 1100, 1.5f, player);
		addGameObject(zombie, 730, 650);
		zombie = new Zombie(null, this, 1800, 2200, 1.5f, player);
		addGameObject(zombie, 1780, 650);
		zombie = new Zombie(null, this, 1800, 2200, 1.5f, player);
		addGameObject(zombie, 2250, 650);
		
		spin = new Spin(null, this, 250, 600, 1f, player);
		addGameObject(spin, 800, 200);
		spin = new Spin(null, this, 500, 900, 1f, player);
		addGameObject(spin, 1200, 450);

		monsters.add(spin);
		monsters.add(zombie);
	}
	
	@Override
	public void update() {
		for (Monster m : monsters) {
			m.update();
		}
	}

	/**
	 * Laat het scherm zien als het spel voorbij is.
	 */
	public void gameOver() {
		createDashboard(1200, 800);
		dashboardText();
		draw();
	}

	
	/**
	 * Maakt een dashboard aan.
	 * @param dashboardWidth  breedte dashboard
	 * @param dashboardHeight hoogte dashboard
	 */
	private void createDashboard(int dashboardWidth, int dashboardHeight) {
		final int tekstGrootte = 10;
		Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
		text = new TextObject("", tekstGrootte);
		dashboard.addGameObject(text);
		addDashboard(dashboard);
	}
	
	/**
	 * Dit zet de text op Game Over en initialiseerd de textopties.
	 */
	private void dashboardText() {
		text.setText("Game Over!");
		text.setFontSize(100);
		text.setForeColor(80, 220, 80, 255);
		text.setX(width/3);
		text.setY(height/2);
	}

	private void createViewWithViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight,
			float zoomFactor) {
		CenterFollowingViewport viewPort = new CenterFollowingViewport(player,
				(int) Math.ceil(screenWidth / zoomFactor), (int) Math.ceil(screenHeight / zoomFactor), 0, 0);
		viewPort.setTolerance(50, 50, 50, 50);
		View view = new View(viewPort, worldWidth, worldHeight);
		setView(view);
		size(screenWidth, screenHeight);
		view.setBackground(loadImage(Propellerjoch.MEDIA_URL.concat("backgroundBergen.png")));
	}

	/**
	 * Initialiseerd de tiles.
	 */
	private void initializeTileMap() {
		Sprite floorSprite = new Sprite(Propellerjoch.MEDIA_URL.concat("platformPack_tile001.png"));
		Sprite platformSprite = new Sprite(Propellerjoch.MEDIA_URL.concat("platform.png"));
		Sprite spikesSprite = new Sprite(Propellerjoch.MEDIA_URL.concat("spikes.png"));
		TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);
		TileType<PlatformTile> platformTileType = new TileType<>(PlatformTile.class, platformSprite);
		TileType<SpikesTile> spikesTileType = new TileType<>(SpikesTile.class, spikesSprite);

		TileType[] tileTypes = { floorTileType, platformTileType, spikesTileType };
		int tileSize = 64;
		int tilesMap[][] = {
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1,  1, -1, -1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, -1, -1, -1, -1, -1,  1, -1,  1, -1, -1,  1, -1,  1,  1, -1, -1, -1, -1,  1,  1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1,  0,  0,  0,  0,  2, -1, -1,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1, -1, -1,  0,  0, -1, -1, -1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, } };
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}
}

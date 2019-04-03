package propellerjoch;

import java.util.ArrayList;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.EdgeFollowingViewport;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;
import propellerjoch.tiles.FloorTile;
import propellerjoch.tiles.SpikesTile;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Propellerjoch extends GameEngine {

	private Player player;
	private Monster zombie;
	private Monster spin;
	private ArrayList<Monster> monsters = new ArrayList<>();
	private Checkpoint cp;
	private Prinses prinses;
	
	public static String MEDIA_URL = "Propellerjoch/propellerjoch/media/";

	public static void main(String[] args) {
		Propellerjoch pj = new Propellerjoch();

		pj.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 2880;
		int worldHeight = 1200;

		createObjects();
		initializeTileMap();

		View view = new View(worldWidth, worldHeight);

		setView(view);
		size(worldWidth, worldHeight);
		createViewWithViewport(worldWidth, worldHeight, 1200, 800, 1.2f);
	}

	private void createObjects() {
		cp = new Checkpoint(this, 200, 500);
		addGameObject(cp, 1600, 500);
		player = new Player(this, cp);
		addGameObject(player, 200, 700);
		// Voor monsters : type(null, this, beginPunt, eindPunt, speed, player)
		zombie = new Zombie(null, this, 750, 1100, 1.5f, player);
		spin = new Spin(null, this, 250, 600, 1f, player);
		
		monsters.add(spin);
		monsters.add(spin);
		monsters.add(spin);
		monsters.add(zombie);
		addGameObject(zombie, 730, 650);
		addGameObject(spin, 800, 200);
		spin = new Spin(null, this, 500, 900, 1f, player);
		addGameObject(spin, 1200, 450);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for (Monster m : monsters) {
			m.update();
		}

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

	private void initializeTileMap() {
		// Load Sprites
		Sprite floorSprite = new Sprite(Propellerjoch.MEDIA_URL.concat("platformPack_tile001.png"));
		Sprite spikesSprite = new Sprite(Propellerjoch.MEDIA_URL.concat("platformPack_tile001.png"));
		// Create tile types with the right Tile class and sprite
		TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);
		TileType<SpikesTile> spikeTileType = new TileType<>(SpikesTile.class, spikesSprite);
		
		TileType[] tileTypes = { floorTileType, spikeTileType };
		int tileSize = 64;
		int tilesMap[][] = { 
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } };
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}
}

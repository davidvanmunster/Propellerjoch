package propellerjoch;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.EdgeFollowingViewport;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;
import propellerjoch.tiles.FloorTile;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Propellerjoch extends GameEngine {
	
	private Player player;
	
	public static String MEDIA_URL = "Propellerjoch/propellerjoch/media/";

	public static void main(String[] args) {
		Propellerjoch pj = new Propellerjoch();
		
		pj.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 800;
		int worldHeight = 600;
		
		createObjects();
		initializeTileMap();
		
		View view = new View(worldWidth, worldHeight);
		
		setView(view);
		size(worldWidth, worldHeight);
		createViewWithViewport(worldWidth, worldHeight, 1200, 800, 1.5f);
	}

	private void createObjects() {
		player = new Player(this);
		addGameObject(player, 200, 0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
    private void createViewWithViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {
        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(player, (int) Math.ceil(screenWidth / zoomFactor), (int) Math.ceil(screenHeight / zoomFactor), 0, 0);
        viewPort.setTolerance(50, 50, 50, 50);
        View view = new View(viewPort, worldWidth, worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
        view.setBackground(loadImage(Propellerjoch.MEDIA_URL.concat("background.png")));
    }
    
    private void initializeTileMap() {
        // Load Sprites
        Sprite floorSprite = new Sprite(Propellerjoch.MEDIA_URL.concat("platformPack_tile001.png"));
        // Create tile types with the right Tile class and sprite
        TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);

        TileType[] tileTypes = {floorTileType};
        int tileSize = 64;
        int tilesMap[][] = {
                {-1, -1, -1, -1, -1, -1, -1, },
                {-1, -1, -1, -1, -1, -1, -1, },
                {-1, -1, -1, -1, -1, -1, -1, },
                {-1, -1, -1, -1, -1, -1, -1, },
                {-1, -1, -1, -1, -1, -1, -1, },
                {-1, -1, -1, -1, 0, 0, 0, },
                {0, 0, 0, 0, 0, 0, 0, },
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
}

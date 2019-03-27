package propellerjoch;

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
	
	public Player(Propellerjoch pj) {
		// Met `.concat()` plak je 2 strings aan elkaar.
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("player.png")));
		float gravity = 0.15f;
		setGravity(gravity);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
		if (keyCode == pj.UP) {
			setDirectionSpeed(0, speed);
		}
	}

    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof FloorTile) {
                try {					
                    vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
                    setY(vector.y - getHeight());
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
      
    }
}

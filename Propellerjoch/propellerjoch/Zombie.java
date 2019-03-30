package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;
import propellerjoch.tiles.FloorTile;

public class Zombie extends Monsters implements ICollidableWithTiles {
    
    private Propellerjoch pj;
    
    public Zombie(Propellerjoch pj) {
        super(new Sprite(Propellerjoch.MEDIA_URL.concat("zombie.png")));
        float gravity = 0.15f;
        setGravity(gravity);
        this.pj = pj;
    }

    @Override
    public void update() {
        final float speed = 1.5f;
        if (x < 710) {
            setDirectionSpeed(90, speed);
        }
        if (x > 1200) {
            setDirectionSpeed(270, speed);
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
						this.setY(vector.y - getHeight());
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
				}
				if (CollisionSide.LEFT.equals(ct.getCollisionSide())) {
					try {
						vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
						this.setX(vector.x - getWidth());
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
				}
				if (CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
					try {
						vector = pj.getTileMap().getTilePixelLocation(ct.getTile());
						this.setX(vector.x + getWidth());
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
				}

			}
		}

	}
}
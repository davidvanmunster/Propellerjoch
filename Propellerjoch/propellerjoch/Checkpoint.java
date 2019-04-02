package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Checkpoint extends SpriteObject implements ICollidableWithGameObjects {
	Propellerjoch pj;
	
	public float checkpointX = 200;
	public float checkpointY = 700;
	
	public Checkpoint(Propellerjoch pj) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("checkpoint.png")));
		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go: collidedGameObjects) {
			if (go instanceof Player) {		
				setCheckpoint(this.x, this.y);
			}
		}
	}

	private void setCheckpoint(float x, float y) {
		checkpointX = x;
		checkpointY = y;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

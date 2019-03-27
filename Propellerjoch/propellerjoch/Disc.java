package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;

public class Disc extends SpriteObject {
	Propellerjoch pj;

	public Disc(Propellerjoch pj) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("platformPack_tile011.png")));
		this.pj = pj;
	}

	@Override
	public void update() {
		final int speed = 3;
		if (x < 0) {
			setDirectionSpeed(90, speed);
		}   
		if (x> pj.getWidth() - this.getWidth()) {
			setDirectionSpeed(270, speed);
		}
	}
	
	/*@Override*/
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go: collidedGameObjects) {
			if (go instanceof Player) {				
				pj.deleteGameObject(this);
			}
		}
	}
}

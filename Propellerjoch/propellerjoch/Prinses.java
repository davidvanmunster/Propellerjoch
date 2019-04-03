package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Prinses extends SpriteObject implements ICollidableWithGameObjects {

	private Propellerjoch pj;
	
	public Prinses(Sprite sprite,Propellerjoch pj, int x, int y) {
		super(sprite);
		this.pj = pj;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Player) {
				
			}
		}
	}
}

package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
public class Bal extends SpriteObject {
	private Propellerjoch pj;
	
	public Bal(Sprite sprite, Propellerjoch pj) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("vuurbal.png")));
		this.pj = pj;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go: collidedGameObjects) {
			if (go instanceof Monster) {		
					pj.deleteGameObject(go);
					pj.deleteGameObject(pj.bal);
					System.out.println("nigga");
			}
		}
	}

}

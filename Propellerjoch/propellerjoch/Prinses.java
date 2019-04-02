package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Prinses extends SpriteObject implements ICollidableWithGameObjects {

    private Propellerjoch pj;
    
    public Prinses(Propellerjoch pj) {
        super(new Sprite(Propellerjoch.MEDIA_URL.concat("prinses.png")));
        this.pj = pj;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject go : collidedGameObjects) {
            if (go instanceof Player) {
                pj.gameOver();
            }
        }
    }
}
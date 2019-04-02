package propellerjoch;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;

public class Spin extends Monster implements ICollidableWithGameObjects {

	private Propellerjoch pj;

	public Spin(Sprite sprite, Propellerjoch pj, float beginPunt, float eindPunt, float speed, Player player) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("spin.png")), pj, beginPunt, eindPunt, speed, player);
		this.pj = pj;
	}

	public void update() {	
		if (y < beginPunt) {
			setDirectionSpeed(180, speed);
		}
		if (y > eindPunt) {
			setDirectionSpeed(0, speed);
		}
	}
}
package propellerjoch;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.Sprite;

public class Zombie extends Monster implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Propellerjoch pj;
	private int direction = 90;
	
	/**
	 * Constructor
	 * 
	 * @param sprite		- De sprite van de zombie
	 * @param pj			- Referentie naar de wereld
	 * @param beginPunt		- Het beginpunt van het loopgebied (x voor zombies, y voor spinnen)
	 * @param eindPunt		- Het eindpunt van het loopgebied
	 * @param speed			- De snelheid van het monster
	 * @param player		- Referentie naar de speler
	 */
	public Zombie(Propellerjoch pj, float beginPunt, float eindPunt, float speed, Player player) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("zombie.png")), pj, beginPunt, eindPunt, speed, player);
		float gravity = 2.5f;
		setGravity(gravity);
		this.pj = pj;
	}
	
	public void beweeg() {
		if (x <= beginPunt) {
			direction = 90;
		} else if (x >= eindPunt) {
			direction = 270;
		} 
		setDirectionSpeed(direction, speed);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}
package propellerjoch;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;

public class Spin extends Monster implements ICollidableWithGameObjects {

	private Propellerjoch pj;

	/**
	 * Constructor
	 * 
	 * @param sprite		- De sprite van de spin
	 * @param pj			- Referentie naar de wereld
	 * @param beginPunt		- Het beginpunt van het loopgebied (x voor zombies, y voor spinnen)
	 * @param eindPunt		- Het eindpunt van het loopgebied
	 * @param speed			- De snelheid van het monster
	 * @param player		- Referentie naar de speler
	 */
	public Spin(Propellerjoch pj, float beginPunt, float eindPunt, float speed, Player player) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("spin.png")), pj, beginPunt, eindPunt, speed, player);
		this.pj = pj;
	}

	public void beweeg() {	
		if (y < beginPunt) {
			setDirectionSpeed(180, speed);
		}
		if (y > eindPunt) {
			setDirectionSpeed(0, speed);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
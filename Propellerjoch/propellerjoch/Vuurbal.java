package propellerjoch;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Vuurbal extends SpriteObject implements Powerup {
	Propellerjoch pj;
	Bal bal;
	private Player player;

	float x = 200;
	float y = 700;
	
	public Vuurbal(Bal bal, Propellerjoch pj, Player player) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("vuurbal.png")));
		this.player = player;
		this.pj = pj;
		this.bal = bal;
	}

	public void powerupFunctie() {
		
		
		if (player.schietVuurbal == true && pj.balAdded == false) {
			pj.createBal();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

package propellerjoch;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Vuurbal implements Powerup {
	Propellerjoch pj;
	Bal bal;
	private Player player;
	private int aantalKeerGeschoten = 0;

	float x = 200;
	float y = 700;
	
	public Vuurbal(Bal bal, Propellerjoch pj, Player player) {
		this.player = player;
		this.pj = pj;
		this.bal = bal;
	}

	public void powerupFunctie() {
		if (player.schietVuurbal == true && pj.balAdded == false) {
			pj.createBal();
			bal.startAlarm();
			aantalKeerGeschoten++;
		}
		if (aantalKeerGeschoten >= 5) {
			player.setCurrentFrameIndex(0);
			aantalKeerGeschoten = 0;
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}

package propellerjoch;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Vuurbal implements Powerup {
	private Propellerjoch pj;
	private Bal bal;
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
			System.out.println("x:");
			System.out.print(player.getX());
			System.out.print("y:");
			System.out.print(player.getY());
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

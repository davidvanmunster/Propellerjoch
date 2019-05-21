package propellerjoch;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Vuurbal extends SpriteObject implements Powerup {
	Propellerjoch pj;
	Bal bal;
	private Player player;

	float x = 300;
	float y = 300;
	
	public Vuurbal(Bal bal, Propellerjoch pj, Player player) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("vuurbal.png")));
		this.player = player;
		this.pj = pj;
		this.bal = bal;
	}

	public void powerupFunctie() {
		int vuurbalSnelheid = 10;
		
		if (player.schietVuurbal == true) {
//			pj.addGameObject(bal, x, y);
			System.out.println("nigga");
//			setDirectionSpeed(90, vuurbalSnelheid);
//			player.schietVuurbal = false;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

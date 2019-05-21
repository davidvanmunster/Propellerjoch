package propellerjoch;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Vuurbal extends SpriteObject implements Powerup {
	
	private Player player;

	public Vuurbal(float x, float y) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("vuurbal.png")));
		x = player.getX();
		y = player.getY();
	}

	public void powerupFunctie() {
		int vuurbalSnelheid = 10;
		if (player.schietVuurbal == true) {
			new Vuurbal(x, y);
			setDirectionSpeed(90, vuurbalSnelheid);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

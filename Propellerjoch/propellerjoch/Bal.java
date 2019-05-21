package propellerjoch;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Bal extends SpriteObject {
	float x;
	float y;
	
	public Bal(Sprite sprite, float x, float y) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("vuurbal.png")));
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	

}

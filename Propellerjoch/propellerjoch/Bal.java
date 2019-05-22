package propellerjoch;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
public class Bal extends SpriteObject implements IAlarmListener {
	private Propellerjoch pj;
	
	private int vuurbalSeconden = 1;
	
	public Bal(Sprite sprite, Propellerjoch pj) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("vuurbal.png")));
		this.pj = pj;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void startAlarm() {
		Alarm alarm = new Alarm("Hoe lang het duurt voordat de vuurbal verdwijnt", vuurbalSeconden);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		pj.deleteGameObject(this);
		pj.balAdded = false;
	}
	

}

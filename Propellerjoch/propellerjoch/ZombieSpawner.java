package propellerjoch;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class ZombieSpawner extends SpriteObject implements IAlarmListener {

	private Propellerjoch pj;
	private Player player;

	private int x;
	private int y;
	
	private float zombiesPerTienSeconden;
	private Random random;

	/**
	 * Constructor
	 *
	 * @param pj               			- Referentie naar de wereld
	 * @param zombiesPerTienSeconden    - Aantal zombies dat per 10 seconden gemaakt moet worden
	 * @param sprite					- Sprite van de zombie
	 */
	public ZombieSpawner(Propellerjoch pj, float zombiesPerTienSeconden, int x, int y) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("zwartgat.png")));
		this.zombiesPerTienSeconden = zombiesPerTienSeconden;
		this.pj = pj;
		this.x = x;
		this.y = y;
		setX(x);
		setY(y);
		random = new Random();
		startAlarm();
	}

	private void startAlarm() {
		Alarm alarm = new Alarm("New zombie", 10 / zombiesPerTienSeconden);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		float snelheid = (random.nextFloat() * 4) + 0.5f;
		Zombie z = new Zombie(pj, 1700, 2650, snelheid, player);
		pj.addGameObject(z, x + 150, y + 150);
		pj.monsters.add(z);
		startAlarm();
	}

	public void update() {
		// TODO Auto-generated method stub
	}

}

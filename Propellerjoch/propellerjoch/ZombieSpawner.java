package propellerjoch;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class ZombieSpawner extends SpriteObject implements IAlarmListener {

	private Propellerjoch pj;
	private Player player;
	private Sprite sprite;

	private float zombiesPerTienSeconden;
	private Random random;

	/**
	 * Constructor
	 *
	 * @param pj               			- Referentie naar de wereld
	 * @param zombiesPerTienSeconden    - Aantal zombies dat per 10 seconden gemaakt moet worden
	 * @param sprite					- Sprite van de zombie
	 */
	public ZombieSpawner(Propellerjoch pj, float zombiesPerTienSeconden) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("zombie.png")));
		this.zombiesPerTienSeconden = zombiesPerTienSeconden;
		this.pj = pj;
		this.sprite = sprite;
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
		int x = random.nextInt(50) + 1700;
		float snelheid = (random.nextFloat() * 4) + 0.5f;
		Zombie z = new Zombie(pj, 1800, 2650, snelheid, player);
		pj.addGameObject(z, x, 750);
		startAlarm();
	}

	public void update() {
		// TODO Auto-generated method stub
	}

}

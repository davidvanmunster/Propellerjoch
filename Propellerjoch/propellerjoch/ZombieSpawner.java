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

	private float zombiesPerVijfSeconden;
	private Random random;

	/**
	 * Constructor
	 *
	 * @param world            Referentie naar de wereld
	 * @param popSound         Geluid dat moet klinken als een bel knapt
	 * @param bubblesPerSecond Aantal bellen dat per seconden gemaakt moet worden
	 */
	public ZombieSpawner(Propellerjoch pj, float zombiesPerVijfSeconden, Sprite sprite) {
		super(new Sprite(Propellerjoch.MEDIA_URL.concat("zombie.png")));
		this.zombiesPerVijfSeconden = zombiesPerVijfSeconden;
		this.pj = pj;
		this.sprite = sprite;
		random = new Random();
		startAlarm();
	}

	private void startAlarm() {
		Alarm alarm = new Alarm("New zombie", 5 / zombiesPerVijfSeconden);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		int x = random.nextInt(50) + 1749;
		float snelheid = (random.nextFloat() * 5) + 0.5f;
		Zombie z = new Zombie(sprite, pj, 1800, 2200, snelheid, player);
		pj.addGameObject(z, x, 650);
		startAlarm();
	}

	public void update() {
		// TODO Auto-generated method stub
	}

}

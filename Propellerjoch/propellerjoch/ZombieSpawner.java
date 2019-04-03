package propellerjoch;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;

public class ZombieSpawner implements IAlarmListener {
    
	private Propellerjoch pj;
	private Player player;
    
    private float zombiesPerVijfSeconden;
    private Random random;

    /**
     * Constructor
     *
     * @param world            Referentie naar de wereld
     * @param popSound         Geluid dat moet klinken als een bel knapt
     * @param bubblesPerSecond Aantal bellen dat per seconden gemaakt moet worden
     */
    public ZombieSpawner(Propellerjoch pj, float zombiesPerVijfSeconden) {
        this.zombiesPerVijfSeconden = zombiesPerVijfSeconden;
        this.pj = pj;
        random = new Random();
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("New zombie", 10 / zombiesPerVijfSeconden);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override // HOE MOET IK DIT EEN SPRITE MEEGEVEN? KAN DAT UBERHAUPT? HELP
    public void triggerAlarm(String alarmName) {
        int x = random.nextInt(10) + 10;
        float snelheid = (random.nextFloat()*5) + 0.5f;
        Zombie z = new Zombie(sprite, pj, 1800, 2200, snelheid, player);
        pj.addGameObject(z, x, 650);
        startAlarm();
    }

}

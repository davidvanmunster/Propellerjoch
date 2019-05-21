package propellerjoch;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class Harnas implements Powerup, IAlarmListener {
	
	public int lengteOnsterfelijkheid = 1;
	
	private Player player;
	private Checkpoint cp;

	public Harnas(int lengteOnsterfelijkheid) {
		this.lengteOnsterfelijkheid = lengteOnsterfelijkheid;
	}

	@Override
	public void powerupFunctie() {
//		if ()
		startAlarm();
	}
	
	private void startAlarm() {
		Alarm alarm = new Alarm("Onsterfelijkheid", lengteOnsterfelijkheid);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		
	}

}

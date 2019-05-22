package propellerjoch;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class Harnas implements Powerup, IAlarmListener {
	Propellerjoch pj;	
	private Player player;
	private Checkpoint cp;
	
	public int lengteOnsterfelijkheid = 2;

	public Harnas(Propellerjoch pj, Player player) {
		this.pj = pj;
		this.player = player;
	}

	@Override
	public void powerupFunctie() {
		if (player.onsterfelijkheid == true) {
			System.out.println("Onsterfelijk!");
			startAlarm();
		}
	}
	
	private void startAlarm() {
		Alarm alarm = new Alarm("Onsterfelijkheid", lengteOnsterfelijkheid);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		System.out.println("Onsterfelijkheid beëindigd");
		player.onsterfelijkheid = false;
		player.setCurrentFrameIndex(0);
	}

}

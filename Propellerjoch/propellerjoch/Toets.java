package propellerjoch;

public class Toets {
	int keyCode;
	boolean ingedrukt;
	
	public Toets(int keyCode) {
		this.keyCode = keyCode;
	}
	
	public int getKeyCode() {
		return keyCode;
	}
	
	public boolean getIngedrukt() {
		return ingedrukt;
	}
	
	public void setIngedrukt(boolean ingedrukt) {
		this.ingedrukt = ingedrukt;
	}
}

package propellerjoch;

public class Toets {
	private int keyCode;
	private boolean ingedrukt;
	
	/**
	 * Constructor
	 * 
	 * @param keyCode	- De Toets code van de ingedrukte toets
	 */
	public Toets(int keyCode) {
		this.keyCode = keyCode;
	}
	
	/**
	 * Geeft de Toets code van de ingedrukte toets.
	 * @return keyCode int
	 */
	public int getKeyCode() {
		return keyCode;
	}
	
	/**
	 * Geeft terug of de toets is ingedrukt.
	 * @return ingedrukt boolean
	 */
	public boolean getIngedrukt() {
		return ingedrukt;
	}
	
	/** 
	 * Hiermee kan je een knop aanzetten.
	 * @param ingedrukt
	 */
	public void setIngedrukt(boolean ingedrukt) {
		this.ingedrukt = ingedrukt;
	}
}

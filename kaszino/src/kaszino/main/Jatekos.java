package kaszino.main;

public abstract class Jatekos {
	protected Asztal asztal;
	static public int azon = 0;
	public int sajatAzon;
	
	public Jatekos() {
		sajatAzon = azon;
		azon+=1;
	}
	
	public void lep() {
		
	}
	
	public void setAsztal(Asztal a) {
		asztal = a;
	}
}

package kaszino.main;

public class Robot extends Jatekos {

	public String toString() {
		return "Robot "+sajatAzon;
	}
	
	public void lep() {
		System.out.println("Típusom: "+this);
		System.out.println("Kör száma: "+asztal.getKor());	
	}
}

package kaszino.main;

public class Kezdo extends Jatekos {
	
	public String toString() {
		return "Kezdo "+sajatAzon;
	}
	
	public void lep() {
		int kor = asztal.getKor();
		System.out.println("Típusom: "+this);
		System.out.println("Kör száma: "+kor);
		
		if (kor % 2 == 0) {
			asztal.emel(1);
		}
	}
}

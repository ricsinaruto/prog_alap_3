package kaszino.main;

public class Robot extends Jatekos {

	public String toString() {
		return "Robot "+sajatAzon;
	}
	
	public void lep() {
		System.out.println("T�pusom: "+this);
		System.out.println("K�r sz�ma: "+asztal.getKor());	
	}
}

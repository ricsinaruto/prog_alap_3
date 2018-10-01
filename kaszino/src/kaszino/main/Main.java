package kaszino.main;

public class Main {
	public static Asztal asztal = new Asztal();

	static public void main(String[] args) {
		// Elsõ feladat.
		//elso();
		
		// Második feladat.
		masodik();
		
	}
	
	static public void masodik() {
		asztal.addJatekos(new Kezdo());
		asztal.addJatekos(new Kezdo());
		asztal.addJatekos(new Robot());
		
		for (int i = 0; i < 3; i++) {
			asztal.kor();
		}
	}
	
	/*
	static public void elso() {
		for (int i = 0; i < 3; i++) {
			asztal.addJatekos(new Jatekos());
		}
		
		for (int i = 0; i < 3; i++) {
			asztal.kor();
		}
	}*/
}

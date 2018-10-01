package kaszino.main;

import java.util.ArrayList;
import java.util.List;

public class Asztal {
	private List<Jatekos> jatekosok = new ArrayList<Jatekos>();
	private double tet;
	private int korok;
	
	public void ujJatek() {
		tet = 0;
		korok = 0;
	}
	
	public void addJatekos(Jatekos j) {
		jatekosok.add(j);
		j.setAsztal(this);
	}
	
	public int getKor() {
		return korok;
	}
	
	public void emel(double d) {
		tet+=d;
	}
	
	public void kor() {
		korok += 1;
		for (Jatekos j : jatekosok) {
			j.lep();
		}
		
		System.out.println(tet);
	}
}

package beerRegister.main;

import java.io.Serializable;

public class Beer implements Serializable{
	String name;
	String style;
	Double strength;
	public Beer(String name1, String style1, Double strength1) {
		name = name1;
		style = style1;
		strength = strength1;
	}
	
	public void print() {
		System.out.println(name + " " + style + " " + strength);
	}
}
package beerRegister.main;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer>{
	@Override
	public int compare(Beer x, Beer y) {
		return (int) Math.round(y.strength-x.strength);
	}
}

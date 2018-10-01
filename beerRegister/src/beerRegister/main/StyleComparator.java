package beerRegister.main;

import java.util.Comparator;

public class StyleComparator implements Comparator<Beer>{
	@Override
	public int compare(Beer x, Beer y) {
		return x.style.compareTo(y.style);
	}
}

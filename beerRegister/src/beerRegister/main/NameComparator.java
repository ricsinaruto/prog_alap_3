package beerRegister.main;

import java.util.Comparator;

public class NameComparator implements Comparator<Beer>{
	@Override
	public int compare(Beer x, Beer y) {
		return x.name.compareTo(y.name);
	}
}

package labor_5.main;

import java.util.ArrayList;
import java.util.List;


public class Fifo {
	static public List<String> str_list = new ArrayList<String>();
	
	synchronized void put(String string) throws InterruptedException {
		if (Thread.holdsLock(this)) {
			if (str_list.size() == 10) {
				this.wait(Long.MAX_VALUE);		
			}
			this.notify();
			str_list.add(string);
		}
	}
	
	synchronized String get() throws InterruptedException {
		if (Thread.holdsLock(this)) {
			if (str_list.size() == 0) {
				this.wait(Long.MAX_VALUE);
			}
			this.notify();
			return str_list.remove(0);
		}
		return null;
	}
}

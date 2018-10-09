package labor_5.main;

import java.util.ArrayList;
import java.util.List;


public class Fifo {
	static public List<String> str_list = new ArrayList<String>();
	
	synchronized void put(String string) throws InterruptedException {
		System.out.println("Fifo.put " + Long.toString(Thread.currentThread().getId()));
		if (Thread.holdsLock(this)) {
			if (str_list.size() == 10) {
				this.wait(Long.MAX_VALUE);		
			}
			str_list.add(string);
			this.notifyAll();
		}
	}
	
	synchronized String get() throws InterruptedException {
		System.out.println("Fifo.get " + Long.toString(Thread.currentThread().getId()));
		if (Thread.holdsLock(this)) {
			while (true) {
				if (str_list.size() == 0) {
					this.wait(Long.MAX_VALUE);
				}
				else {
					break;
				}
			}
			String element = str_list.remove(0);
			this.notifyAll();
			return element;
		}
		return null;
	}
}

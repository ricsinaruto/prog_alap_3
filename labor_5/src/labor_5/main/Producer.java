package labor_5.main;

import java.util.Objects;

public class Producer implements Runnable {
	String my_string;
	Fifo my_fifo;
	int sleep_time;
	
	public Producer(Fifo fifo, String string, int n) {
		my_string = string;
		my_fifo = fifo;
		sleep_time = n;
	}
	
	public void run() {
		int i = 0;
		while (true) {
			try {
				my_fifo.put(my_string + i);
				String time = Objects.toString(System.currentTimeMillis(), null);
				System.out.println("produced " + my_string + " " + i + " " + time.substring(time.length() - 5, time.length()));
				
				i += 1;
				try {
					Thread.sleep(sleep_time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

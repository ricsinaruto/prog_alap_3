package labor_5.main;

import java.util.Objects;

public class Consumer extends Thread{
	Fifo fifo;
	String string;
	int sleep_time;
	
	public Consumer(Fifo f, String s, int n) {
		fifo = f;
		string = s;
		sleep_time = n;
	}
	
	public void run() {
		while (true) {
			String read_string = null;
			try {
				read_string = fifo.get();
				String time = Objects.toString(System.currentTimeMillis(), null);
				System.out.println("consumed " + string + " " + read_string + " " + time.substring(time.length() - 5, time.length()));
				try {
					sleep(sleep_time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

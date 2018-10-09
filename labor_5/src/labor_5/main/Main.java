package labor_5.main;

public class Main {
	static public void main(String[] args) throws InterruptedException {
		Fifo fifo = new Fifo();
		Producer prod1 = new Producer(fifo, "p1", 1000);
		Producer prod2 = new Producer(fifo, "p2", 1000);
		Producer prod3 = new Producer(fifo, "p3", 1000);
		Thread t1 = new Thread(prod1);
		Thread t2 = new Thread(prod2);
		Thread t3 = new Thread(prod3);
		Consumer cons1 = new Consumer(fifo, "c1", 100);
		Consumer cons2 = new Consumer(fifo, "c2", 100);
		Consumer cons3 = new Consumer(fifo, "c3", 100);
		Consumer cons4 = new Consumer(fifo, "c4", 100);
		
		t1.start();
		t2.start();
		t3.start();
		cons1.start();
		cons2.start();
		cons3.start();
		cons4.start();
	}
}

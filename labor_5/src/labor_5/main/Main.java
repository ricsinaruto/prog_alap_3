package labor_5.main;

public class Main {
	static public void main(String[] args) throws InterruptedException {
		Fifo fifo = new Fifo();
		Producer prod = new Producer(fifo, "producer", 1000);
		Consumer cons = new Consumer(fifo, "consumer", 3000);
		prod.start();
		cons.start();
	}
}

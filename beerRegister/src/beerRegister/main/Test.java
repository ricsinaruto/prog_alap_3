package beerRegister.main;

public class Test {
	public void main() throws EmptyQueueException {
		PQueue<String> queue = new PQueue<String>();
		
		queue.push("b");
		queue.push("c");
		queue.push("a");
		queue.push("d");
		System.out.println(queue.top());
		System.out.println(queue.pop());
		System.out.println(queue.size());
		queue.clear();
		System.out.println(queue.size());
		
		PQueue<Integer> s = new PQueue<Integer>();
		s.push(1); s.push(2); s.push(3); s.push(4);
		
		for (Integer i : s) {
			System.out.println(i);
		}
	}
}

package beerRegister.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PQueue<T extends Comparable> implements Iterable<T> {
	public List<T> queue;
	public PQueue() {
		queue = new ArrayList<T>();
	}
	
	public void push(T t) {
		queue.add(t);
	}
	
	public T pop() throws EmptyQueueException {
		if (queue.size() == 0) {
			throw new EmptyQueueException("The queue is empty.");
		}
		else {
			T element = (T) Collections.max(queue);
			queue.remove(element);
			return element;
		}
	}
	
	public T top() throws EmptyQueueException {
		if (queue.size() == 0) {
			throw new EmptyQueueException("The queue is empty.");
		}
		else {
			return (T) Collections.max(queue);
		}
	}
	
	public int size() {
		return queue.size();
	}
	
	public void clear() {
		queue.clear();
	}

	@Override
	public Iterator<T> iterator() {
		List <T> sorted = queue;
		Collections.sort(sorted, Collections.reverseOrder());
		return sorted.iterator();
	}
}

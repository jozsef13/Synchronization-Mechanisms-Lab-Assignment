package monitors;

import java.util.LinkedList;
import java.util.Queue;

import producerConsumer.PCQueue;

public class PCQueueMonitors implements PCQueue{
	// The class is an implementation of the PCQueue interface with monitors

	private Queue<Integer> queue = new LinkedList<Integer>(); // The queue of integer elements

	public synchronized void enqueue(int number, int capacity) {
		// Producer produces a number, which means that the producer thread adds a number to the queue
		// While the list is full, the producer will wait until the consumer notifies it that the queue is not full anymore
		while (queue.size() >= capacity) {
			try {
				wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		queue.add(number);
		notifyAll(); // Notify the consumer that the producer finished it's job and not wait anymore
	}

	public synchronized int dequeue() {
		// Consumer consumes a number, which means that it takes it and prints it
		int temp = 0;
		// While the list is empty, the consumer will wait until the producer notifies it that the queue is not empty anymore
		while (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Remove the head of the queue and give it to a temporary variable such that the consumer could consume it
		temp = queue.remove();
		notifyAll(); // Notify the producer that the consumer finished it's job and not wait anymore
		return temp;
	}

}

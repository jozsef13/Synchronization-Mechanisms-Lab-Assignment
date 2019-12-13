package semaphores;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import producerConsumer.PCQueue;

public class PCQueueSemaphores implements PCQueue {
	// This class is an implementation of the PCQueue interface with semaphores
	
	private Queue<Integer> queue = new LinkedList<Integer>(); // The queue of integer elements
	private Semaphore producerSemaphore = new Semaphore(1); // Initialization of the first semaphore with 1 permit
	private Semaphore consumerSemaphore = new Semaphore(0); // Initialization of the second semaphore with no permit

	public void enqueue(int number, int capacity) {
		// Producer produces a number, which means that the producer thread adds a number to the queue
		// Acquire a permit to the producer semaphore, if it is possible, or blocking until one is available
		try {
			producerSemaphore.acquire();
			// If the queue size is less than the maximum capacity, adds the number given by parameter to the queue(produces it)
			if (queue.size() < capacity) {
				queue.add(number);
			}
		} catch (Exception e) {
			System.out.println("Thread intreruppted - append!");
		}
		// After the producer produce the item(number), it releases the consumer semaphore to notify the consumer
		consumerSemaphore.release();
	}

	public int dequeue() {
		// Consumer consumes a number, which means that it takes it and prints it
		int temp = 0;
		// Acquire a permit to the consumer semaphore, if it is possible, or blocking until one is available
		try {
			consumerSemaphore.acquire();
			// If the queue is not empty, save the element from the head of the queue in a temporary variable which will be consumed afterwards
			if (!queue.isEmpty()) {
				temp = queue.remove();
			}
		} catch (Exception e) {
			System.out.println("Thread intreruppted - take!");
		}
		// After the consumer consume the item(number), it releases the producer semaphore to notify the producer
		producerSemaphore.release();

		return temp;

	}
}

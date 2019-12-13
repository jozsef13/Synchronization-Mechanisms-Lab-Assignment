package locks;

import java.util.Random;

import producerConsumer.Consumer;
import producerConsumer.PCQueue;
import producerConsumer.Producer;

public class ApplicationLocks {
	// Main class for the implementation of the Producer-Consumer problem with locks

	public static void main(String[] args) {
		// Initialization of the queue
		PCQueue queue = new PCQueueLocks();
		// Initialize the capacity randomly
		int capacity = randomNumberGenerator();
		// Initialization of the two threads - Consumer and Producer
		Producer producer = new Producer(queue, capacity);
		Consumer consumer = new Consumer(queue, capacity);
		System.out.println("For the capacity equal with " + capacity + " we have the upcoming output:");
		// Start the two threads
		System.out.println("Producer - Consumer started!");
		producer.start();
		consumer.start();

		// Join the two threads
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Producer produced " + producer.getCount() + " elements!");
		System.out.println("Consumer consumed " + consumer.getCount() + " elements!");
		System.out.println("Producer - Consumer finished!");
	}

	private static int randomNumberGenerator() {
		// The function for generating random inputs 
		Random rand = new Random();
		return rand.nextInt(500) + 1;
	}
}

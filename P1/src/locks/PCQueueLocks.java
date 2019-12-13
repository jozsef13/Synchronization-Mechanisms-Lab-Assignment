package locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import producerConsumer.PCQueue;

public class PCQueueLocks implements PCQueue{
	// This class is an implementation of the PCQueue interface with locks
	
	private Queue<Integer> queue = new LinkedList<>(); // The queue of integer elements

	// Initialization of the lock and condition
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void enqueue(int number, int capacity) {
		// Producer produces a number, which means that the producer thread adds a number to the queue
		lock.lock(); // Acquires the lock and disables the other consumer from the thread scheduler until the unlock
		try {
			// Producer waits until the size of the queue is lower than the maximum capacity given as input
			while (queue.size() >= capacity) {
				condition.await();
			}

			// Producer adds(produces) the number to the queue and signals through the condition to wake up the awaiting thread(Consumer) - the list is not empty anymore
			queue.add(number);
			condition.signal();
		} catch (InterruptedException e) {
			System.out.println("Thread intreruppted - append!");
		}

		lock.unlock(); // Enables the consumer
	}

	public int dequeue() {
		// Consumer consumes a number, which means that it takes it and prints it
		int temp = 0;
		lock.lock(); // Acquire lock such that another the producer to be disabled from thread scheduling
		try {
			// Awaits while the queue is empty
			while (queue.isEmpty()) {
				condition.await();
			}

			// Consumer saves the last number added to a temporary variable and removes it from the queue
			temp = queue.remove();
			condition.signal(); // Signals the awaiting producer that the queue is no more full
			lock.unlock(); // Enables the producer
		} catch (InterruptedException e) {
			System.out.println("Thread intreruppted - take!");
		}

		return temp;
	}
}
package producerConsumer;

public class Consumer extends Thread {
	// The consumer class that extends Thread class
	
	private PCQueue queue; // The queue from where the consumer will consume a number
	private int maxSize; // The maximum size of the queue
	private int count; // Counter for how many numbers were consumed

	public Consumer(PCQueue queue, int maxSize) {
		// A constructor with fields
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {
		for (int i = 1; i <= maxSize; i++) {
			// Consume the elements from the queue
			System.out.println("Consumer consumed: " + queue.dequeue());
			count++; // Increase the counter
		}
	}
	
	public int getCount() {
		return count;
	}
}

package producerConsumer;

public class Producer extends Thread {
	// The producer class that extends Thread class
	
	private PCQueue queue; // The queue from where the consumer will consume a number
	private int maxSize; // The maximum size of the queue
	private int count; // Counter for how many numbers were produced

	public Producer(PCQueue queue, int maxSize) {
		// A constructor with fields
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {

		for (int i = 1; i <= maxSize; i++) {
			// Produces a number - adds it to the queue
			System.out.println("Producer produce: " + i);
			queue.enqueue(i, maxSize);
			count++;
		}
	}
	
	public int getCount() {
		return count;
	}
}

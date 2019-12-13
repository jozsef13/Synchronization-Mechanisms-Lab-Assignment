package producerConsumer;

public interface PCQueue {
	// Interface for the queue operations
	
	// The queue append operations(enqueue)
	void enqueue(int number, int capacity);
	
	// The queue take and remove operation(dequeue)
	int dequeue();
}

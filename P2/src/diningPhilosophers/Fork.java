package diningPhilosophers;

public interface Fork {
	// Interface for the operations done by the philosophers upon the forks
	// Method to pick up the fork
	boolean pickUp(String side, int philosopherIndex);

	// Method to put down the fork
	void putDown(String side, int philosopherIndex);

}

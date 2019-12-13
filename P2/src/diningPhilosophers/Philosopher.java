package diningPhilosophers;

public class Philosopher extends Thread {
	// The philosopher class that extends Thread class

	private Fork leftFork; // The left fork object field
	private Fork rightFork; // The right fork object field
	private int remainingFood; // Number of rounds
	private int count = 0; // Counter for the number of times each philosopher ates
	private int index; // The index of each philosopher

	public Philosopher(Fork leftFork, Fork rightFork, int index, int rounds) {
		// Constructor with fields
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.index = index;
		this.remainingFood = rounds;
	}

	private void eating() {
		// Method for when a philosopher eats in an amount of time
		count++; // Increase the counter
		System.out.println("Philosoper " + index + " eating!");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void thinking() {
		// Method for when the philosopher thinks in an amount of time
		System.out.println("Philosoper " + index + " thinking!");

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// The run method specific for each thread class
		for (int i = 0; i < remainingFood; i++) {
			// At each round, the philosopher first thinks
			thinking();
			// It tries to pick up the left fork, if another philosopher picked up this
			// fork, it would not pick it up
			if (leftFork.pickUp("Left", index)) {
				// It tries to pick up the right fork, if another philosopher picked up this
				// fork, it would not pick it up
				if (rightFork.pickUp("Right", index)) {
					// It is eating, so he managed to take both forks
					eating();
					// Puts down the right fork
					rightFork.putDown("Right", index);
				}
				// Puts down the left fork
				leftFork.putDown("Left", index);
			}
		}
	}

	public int getCount() {
		// Getter for the counter
		return count;
	}
}

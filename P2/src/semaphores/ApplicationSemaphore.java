package semaphores;

import java.util.Random;

import diningPhilosophers.Fork;
import diningPhilosophers.Philosopher;

public class ApplicationSemaphore {
	// The main class for Dining Philosophers problem using monitors

	public static void main(String[] args) {
		Philosopher[] philosophers = new Philosopher[5]; // Initialization of the philosophers list
		Fork[] forks = new Fork[philosophers.length]; // Initialization of the forks list
		int rounds = randomNumberGenerator(); // Initialization of the number of rounds given by a random number generator

		for (int i = 0; i < forks.length; i++) {
			// Initialization of each fork
			forks[i] = new SemaphoreFork(i + 1);
		}
		System.out.println("For number of rounds equal with " + rounds + " Dining Philosopher starts!");
		for (int i = 0; i < philosophers.length; i++) {
			Fork leftFork = forks[i]; // Choosing the left fork of each philosopher
			Fork rightFork = forks[(i + 1) % forks.length]; // Choosing the right fork for each philosopher

			// Initialization of each philosopher using the constructor with fields an give
			// each philosopher the left fork, the right fork, it's index and number of rounds
			if (i == philosophers.length - 1) {
				philosophers[i] = new Philosopher(rightFork, leftFork, i + 1, rounds);
			} else {
				philosophers[i] = new Philosopher(leftFork, rightFork, i + 1, rounds);
			}

			// Starting of each thread
			philosophers[i].start();
		}

		// Join each thread
		for (int i = 0; i < philosophers.length; i++) {
			try {
				philosophers[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Printing how many times each philosopher ate
		for (int i = 0; i < philosophers.length; i++) {
			System.out.println("Philosopher " + (i + 1) + " ate " + philosophers[i].getCount() + " times!");
		}

		System.out.println("Dining Philosophers problem stopped!");
	}

	private static int randomNumberGenerator() {
		// The method for random input generator
		Random rand = new Random();
		return rand.nextInt(10) + 10;
	}
}

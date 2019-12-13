package semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import diningPhilosophers.Fork;

public class SemaphoreFork implements Fork {

	private Semaphore semaphore = new Semaphore(1); // Initialization of the semaphore with one permit
	private int index; // The index of the fork

	public SemaphoreFork(int index) {
		// Constructor with one field
		this.index = index;
	}

	public boolean pickUp(String forkSide, int philosopherIndex) {
		// The philosopher will try to pick up the fork by trying to acquire a permit into the semaphore
		// If in 10 milliseconds, the philosopher couldn't pick up the fork, it will
		// return false, otherwise it will return true and pick up the fork
		try {
			if (semaphore.tryAcquire(10, TimeUnit.MILLISECONDS)) {
				System.out.println(forkSide + " fork " + index + " picked up by philosophher " + philosopherIndex);
				return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void putDown(String forkSide, int philosopherIndex) {
		// The philosopher will put down the fork and release the permit to the
		// semaphore such that another philosopher can pick it up
		semaphore.release();
		System.out.println(forkSide + " fork " + index + " released by philosopher " + philosopherIndex);
	}
}

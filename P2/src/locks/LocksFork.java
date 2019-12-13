package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import diningPhilosophers.Fork;

public class LocksFork implements Fork {
	// A class that implements the Fork interface with locks

	private Lock lock = new ReentrantLock(); // Initialization of the lock
	private int index; // The index of the fork

	public LocksFork(int index) {
		// Constructor with one field
		this.index = index;
	}

	public boolean pickUp(String forkSide, int philosopherIndex) {
		// A philosopher(thread) will try to pick up a fork, and if in 10 milliseconds he
		// can't lock it(use it) if will return false and the philosopher will not pickup the fork, otherwise it will
		try {
			if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
				System.out.println(forkSide + " fork " + index + " picked up by philosophher " + philosopherIndex);
				return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void putDown(String forkSide, int philosopherIndex) {
		// The philosopher will put down the fork and release the lock
		System.out.println(forkSide + " fork " + index + " released by philosopher " + philosopherIndex);
		lock.unlock();
	}

}

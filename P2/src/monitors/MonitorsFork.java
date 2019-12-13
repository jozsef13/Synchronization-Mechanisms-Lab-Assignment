package monitors;

import diningPhilosophers.Fork;

public class MonitorsFork implements Fork {

	private int index; // Index of the fork
	private boolean isUsed = false; // A variable that will tell if the fork is used or not

	public MonitorsFork(int index) {
		// Constructor with one field
		this.index = index;
	}

	public synchronized boolean pickUp(String forkSide, int philosopherIndex) {
		// The philosopher will try to pick up the fork, but if it is used, it will wait 100 milliseconds or until another philosopher will notify
		if (isUsed) {
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// If after the philosopher waits, the fork is not used anymore, he will pick it up
		if (!isUsed) {
			System.out.println(forkSide + " fork " + index + " picked up by philosophher " + philosopherIndex);
			isUsed = true;
			return true;
		}

		return false;
	}

	public synchronized void putDown(String forkSide, int philosopherIndex) {
		// The philosopher will put down the fork and notify all the other philosophers that this fork is not used anymore
		System.out.println(forkSide + " fork " + index + " released by philosopher " + philosopherIndex);
		isUsed = false;
		notifyAll();
	}
}

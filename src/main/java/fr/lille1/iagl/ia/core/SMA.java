package fr.lille1.iagl.ia.core;

public class SMA {
	private int waitingTime;
	private int slow;
	private Environment environnement;

	public void run(int n) {
		for (int i = 0; i < n; i++) {
			runOnce();
		}
	}

	public void run() {
		while (true) {
			runOnce();
		}
	}

	public void runOnce() {
		// TODO
	}
}

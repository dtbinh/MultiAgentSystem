package fr.lille1.iagl.ia.main;

import fr.lille1.iagl.ia.core.SMA;

public class Wator {
	private SMA systeme;

	public SMA getSMA() {
		return this.systeme;
	}

	public static void main(String[] args) {
		Wator wator = new Wator();
		wator.getSMA().run();
	}
}

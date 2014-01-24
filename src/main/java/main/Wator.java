package main;

import wator.Ocean;
import core.Environnement;
import core.Systeme;

public class Wator {

	public static void main(final String[] args) throws InterruptedException {

		final int tailleEnv = 5;

		final int timeToBeStarved = 3;

		final int reproductionShark = 4;
		final int reproductionTuna = 3;

		final int initialSharkNumber = 1;
		final int initialTunaNumber = 10;

		final long pourcentageAffichage = 100;
		final long tempsAttenteAffichage = 50;

		final Environnement ocean = new Ocean(tailleEnv);
		final Systeme systeme = new Systeme(ocean);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		ocean.setSysteme(systeme);

	}
}

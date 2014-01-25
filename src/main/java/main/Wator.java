package main;

import wator.Ocean;
import core.Environnement;
import core.Systeme;
import core.Vue;

public class Wator {

	public static void main(final String[] args) throws InterruptedException {

		final int tailleEnv = 4;
		final int tailleVue = 600;

		final int timeToBeStarved = 5;

		final int reproductionShark = 6;
		final int reproductionTuna = 3;

		final int initialNumberOfShark = 1;
		final int initialNumberOfTuna = 10;

		final long pourcentageAffichage = 100;
		final long tempsAttenteAffichage = 500;

		final Environnement ocean = new Ocean(tailleEnv, initialNumberOfShark,
				initialNumberOfTuna);
		final Vue vue = new Vue(ocean, tailleVue, tailleVue);
		final Systeme systeme = new Systeme(vue, ocean);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		ocean.setSysteme(systeme);
		vue.setVisible(true);
		systeme.run();
	}
}

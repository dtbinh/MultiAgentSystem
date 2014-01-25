package main;

import wator.Ocean;
import core.Environnement;
import core.Systeme;
import core.Vue;

public class Wator {

	public static void main(final String[] args) throws InterruptedException {

		final int tailleEnv = 50;
		final int tailleVue = 600;

		final int timeToBeStarved = 5;

		final int reproductionShark = 2;
		final int reproductionTuna = 3;

		final int initialNumberOfShark = 30;
		final int initialNumberOfTuna = 200;

		final long pourcentageAffichage = 10;
		final long tempsAttenteAffichage = 500;

		final Environnement ocean = new Ocean(tailleEnv, initialNumberOfShark,
				initialNumberOfTuna, timeToBeStarved, reproductionShark,
				reproductionTuna);
		final Vue vue = new Vue(ocean, tailleVue, tailleVue);
		final Systeme systeme = new Systeme(vue, ocean);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		ocean.setSysteme(systeme);
		vue.setVisible(true);
		systeme.run();
	}
}

package main;

import wator.Ocean;
import wator.WatorStat;
import core.Environnement;
import core.Statistique;
import core.Systeme;
import core.Vue;

public class Wator {

	public static void main(final String[] args) throws InterruptedException {
		// final int tailleEnv = 100;
		//
		// final int tailleVue = 600;
		//
		// final int tailleCase = 5;
		//
		// final int timeToBeStarved = 6;
		//
		// final int reproductionShark = 8;
		// final int reproductionTuna = 5;
		//
		// final int initialNumberOfShark = 200;
		//
		// final int initialNumberOfTuna = 800;
		//
		// final long pourcentageAffichage = 10;
		// final long tempsAttenteAffichage = 300;
		final int tailleEnv = 75;

		final int tailleVue = 600;

		final int tailleCase = 5;

		final int timeToBeStarved = 6;

		final int reproductionShark = 8;
		final int reproductionTuna = 5;

		final int initialNumberOfShark = 200;

		final int initialNumberOfTuna = 800;

		final long pourcentageAffichage = 10;
		final long tempsAttenteAffichage = 300;

		final Environnement ocean = new Ocean(tailleEnv, initialNumberOfShark,
				initialNumberOfTuna, timeToBeStarved, reproductionShark,
				reproductionTuna);
		final Vue vue = new Vue(ocean, tailleVue, tailleVue, tailleCase);
		final Systeme systeme = new Systeme(vue, ocean);

		final Statistique stat = WatorStat.getInstance();
		stat.setEnvironnement(ocean);
		stat.setFile("Wator");

		systeme.setStatistique(stat);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		ocean.setSysteme(systeme);
		vue.setVisible(true);
		systeme.run();

	}
}

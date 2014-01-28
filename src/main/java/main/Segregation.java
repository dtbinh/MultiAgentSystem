package main;

import schelling.SchellingStats;
import schelling.Territoire;
import core.Environnement;
import core.Statistique;
import core.Systeme;
import core.Vue;

public class Segregation {

	public static void main(final String[] args) {
		final int tailleEnv = 100;
		final int tailleVue = 600;
		final int tailleCase = 5;
		final int nombreVert = 40;
		final int nombreRouge = 40;
		final int pourcentage = 75;
		final int tauxSatisfaction = 50;
		final long pourcentageAffichage = 10;
		final long tempsAttenteAffichage = 600;

		// final Environnement territoire = new Territoire(tailleEnv,
		// nombreVert,
		// nombreRouge, tauxSatisfaction);
		final Environnement territoire = new Territoire(tailleEnv, pourcentage,
				tauxSatisfaction);
		final Vue vue = new Vue(territoire, tailleVue, tailleVue, tailleCase);
		final Systeme systeme = new Systeme(vue, territoire);

		final Statistique stat = SchellingStats.getInstance();
		stat.setEnvironnement(territoire);
		stat.setFile("SchellingStat.csv");

		systeme.setStatistique(stat);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		territoire.setSysteme(systeme);
		vue.setVisible(true);
		systeme.run();

	}

}

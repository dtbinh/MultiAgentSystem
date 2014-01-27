package main;

import particles.StatParticules;
import schelling.Territoire;
import core.Environnement;
import core.Statistique;
import core.Systeme;
import core.Vue;

public class Segregation {

	public static void main(final String[] args) {
		final int tailleEnv = 30;
		final int tailleVue = 600;
		final int tailleCase = 10;
		final int nombreVert = 40;
		final int nombreRouge = 40;
		final int tauxSatisfaction = 30;
		final long pourcentageAffichage = 10;
		final long tempsAttenteAffichage = 500;

		final Environnement territoire = new Territoire(tailleEnv, nombreVert,
				nombreRouge, tauxSatisfaction);
		final Vue vue = new Vue(territoire, tailleVue, tailleVue, tailleCase);
		final Systeme systeme = new Systeme(vue, territoire);

		final Statistique stat = StatParticules.getInstance();
		stat.setEnvironnement(territoire);
		stat.setFile("ParticleStat.csv");

		systeme.setStatistique(stat);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		territoire.setSysteme(systeme);
		vue.setVisible(true);
		systeme.run();

	}

}

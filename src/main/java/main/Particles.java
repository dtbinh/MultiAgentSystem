package main;

import particles.Plateau;
import particles.StatParticules;
import core.Environnement;
import core.Statistique;
import core.Systeme;
import core.Vue;

public class Particles {
	public static void main(final String[] args) throws InterruptedException {
		final int tailleEnv = 30;
		final int tailleVue = 600;
		final int tailleCase = 10;
		final int nombreBille = 10;
		final long pourcentageAffichage = 50;
		final long tempsAttenteAffichage = 500;

		final Environnement plateau = new Plateau(tailleEnv, nombreBille, false);
		final Vue vue = new Vue(plateau, tailleVue, tailleVue, tailleCase);
		final Systeme systeme = new Systeme(vue, plateau);

		final Statistique stat = StatParticules.getInstance();
		stat.setEnvironnement(plateau);
		stat.setFile("ParticleStat.csv");

		systeme.setStatistique(stat);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		plateau.setSysteme(systeme);
		vue.setVisible(true);
		systeme.run();

	}
}

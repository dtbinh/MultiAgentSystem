package main;

import particles.Plateau;
import core.Environnement;
import core.Systeme;
import core.Vue;

public class Particles {
	public static void main(final String[] args) throws InterruptedException {
		final int tailleEnv = 75;
		final int tailleVue = 600;
		final int tailleCase = 15;
		final long pourcentageAffichage = 10;
		final long tempsAttenteAffichage = 500;

		final Environnement plateau = new Plateau(tailleEnv);
		final Vue vue = new Vue(plateau, tailleVue, tailleVue, tailleCase);
		final Systeme systeme = new Systeme(vue, plateau);
		vue.setVisible(true);
		systeme.run();
	}
}

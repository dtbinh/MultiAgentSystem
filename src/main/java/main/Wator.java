package main;

import wator.Ocean;
import wator.Shark;
import wator.Statistique;
import wator.Tuna;
import core.Agent;
import core.Coordonnees;
import core.Environnement;
import core.Systeme;
import core.Vue;

public class Wator {

	public static void main(String[] args) throws InterruptedException {

		int tailleEnv = 25;

		int eatShark = 5;
		int reproductionShark = 7;
		int reproductionTuna = 4;

		int shark = 10;
		int tuna = 40;

		long pourcentageAffichage = 50;
		long tempsAttenteAffichage = 500;

		int time = 1000;
		Environnement ocean = new Ocean(tailleEnv);
		Vue vue = new Vue(ocean, 400, 400);
		vue.setVisible(true);
		Systeme systeme = new Systeme(ocean, vue);
		systeme.setWaitingTime(tempsAttenteAffichage);
		systeme.setSpeed(pourcentageAffichage);
		ocean.setSysteme(systeme);

		boolean isNotFree = false;
		int x, y;
		Coordonnees coord;

		for (int i = 0; i < shark; i++) {
			isNotFree = false;
			do {
				x = (int) (Math.random() * tailleEnv);
				y = (int) (Math.random() * tailleEnv);
				coord = new Coordonnees(x, y);
				isNotFree = ocean.hasAgent(coord);
			} while (isNotFree);
			Agent s = new Shark(coord, ocean);
			((Shark) s).setTimeToEat(eatShark);
			((Shark) s).setTimeToReproduce(reproductionShark);
			Statistique.getInstance().addShark(1);
			systeme.addAgentToAgentList(s);
		}

		for (int i = 0; i < tuna; i++) {
			isNotFree = false;
			do {
				x = (int) (Math.random() * tailleEnv);
				y = (int) (Math.random() * tailleEnv);
				coord = new Coordonnees(x, y);
				isNotFree = ocean.hasAgent(coord);
			} while (isNotFree);
			Agent s = new Tuna(coord, ocean);
			((Tuna) s).setTimeToReproduce(reproductionTuna);
			Statistique.getInstance().addTuna(1);
			systeme.addAgentToAgentList(s);
			System.out.println(coord.getPosX() + " " + coord.getPosY() + " T");
		}
		systeme.run(time);
	}
}

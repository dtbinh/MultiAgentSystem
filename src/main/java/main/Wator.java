package main;

import wator.Shark;
import wator.Tuna;
import core.Agent;
import core.Environnement;
import core.Systeme;
import core.Vide;
import core.Vue;

public class Wator {

	public static void main(String[] args) throws InterruptedException {

		int tailleEnvX = 10, tailleEnvY = 10;
		int dieS = 25;
		int reproduceS = 7;
		int eatS = 7;
		int shark = 1;

		int dieT = 30;
		int reproduceT = 1;
		int eatT = 10;
		int tuna = 25;

		int time = 100;

		Environnement env = new Environnement(tailleEnvX, tailleEnvY, true);
		Vue vue = new Vue(env, 350, 350);
		vue.setVisible(true);
		Systeme systeme = new Systeme(env, vue);
		systeme.setWaitingTime(1000L);
		systeme.setSpeed(100L);
		env.setSysteme(systeme);
		boolean found = true;
		int x, y;

		for (int i = 0; i < shark; i++) {
			found = false;
			do {
				x = (int) (Math.random() * tailleEnvX);
				y = (int) (Math.random() * tailleEnvY);

				if ((env.getAgent(x, y) instanceof Vide)) {
					found = true;
					System.out.println(x + " " + y);
				}
			} while (!found);
			Agent s = new Shark(x, y, env, dieS, reproduceS, eatS);
			systeme.addAgentToAgentList(s);

		}

		for (int i = 0; i < tuna; i++) {
			found = false;
			do {
				x = (int) (Math.random() * tailleEnvX);
				y = (int) (Math.random() * tailleEnvY);

				if ((env.getAgent(x, y) instanceof Vide)) {
					found = true;
					System.out.println(x + " " + y);
				}
			} while (!found);
			Agent s = new Tuna(x, y, env, dieT, reproduceT, eatT);
			systeme.addAgentToAgentList(s);
		}

		systeme.run(time);
	}
}

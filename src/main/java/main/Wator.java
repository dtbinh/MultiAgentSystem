package main;

import wator.Shark;
import wator.Tuna;
import core.Agent;
import core.Environnement;
import core.Systeme;
import core.Vue;

public class Wator {
	public static void main(String[] args) {

		int tailleEnvX = 5, tailleEnvY = 5;
		int dieS = 25;
		int reproduceS = 15;
		int eatS = 1000;
		int shark = 9;

		int dieT = 30;
		int reproduceT = 3;
		int eatT = 10;
		int tuna = 0;

		int time = 1000;

		Environnement env = new Environnement(tailleEnvX, tailleEnvY);

		Vue vue = new Vue(env, 350, 350);

		Systeme systeme = new Systeme(env, vue);
		systeme.setWaitingTime(500L);
		systeme.setSpeed(200L);
		env.setSysteme(systeme);

		for (int i = 0; i < shark; i++) {
			int x = (int) (Math.random() * tailleEnvX);
			int y = (int) (Math.random() * tailleEnvY);
			Agent s = new Shark(x, y, env, dieS, reproduceS, eatS);
			// System.out.println(x + " shark " + y);
			systeme.addAgent(s);

		}

		for (int i = 0; i < tuna; i++) {
			int x = (int) (Math.random() * tailleEnvX);
			int y = (int) (Math.random() * tailleEnvY);
			Agent s = new Tuna(x, y, env, dieT, reproduceT, eatT);
			// System.out.println(x + " tuna" + y);
			systeme.addAgent(s);

		}

		vue.setVisible(true);
		systeme.run(time);
		// systeme.runOnce();
		// systeme.runOnce();
	}
}

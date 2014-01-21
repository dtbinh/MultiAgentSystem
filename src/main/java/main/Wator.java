package main;

import wator.Shark;
import wator.Tuna;
import core.Agent;
import core.Environnement;
import core.Systeme;
import core.Vue;

public class Wator {
	public static void main(String[] args) throws InterruptedException {

		int tailleEnvX = 50, tailleEnvY = 50;
		int dieS = 25;
		int reproduceS = 7;
		int eatS = 7;
		int shark = 15;

		int dieT = 30;
		int reproduceT = 4;
		int eatT = 10;
		int tuna = 100;

		int time = 1000;

		Environnement env = new Environnement(tailleEnvX, tailleEnvY, true);

		Vue vue = new Vue(env, 350, 350);
		vue.setVisible(true);

		Systeme systeme = new Systeme(env, vue);
		systeme.setWaitingTime(500L);
		systeme.setSpeed(0L);
		env.setSysteme(systeme);

		for (int i = 0; i < shark; i++) {
			int x = (int) (Math.random() * tailleEnvX);
			int y = (int) (Math.random() * tailleEnvY);
			Agent s = new Shark(x, y, env, dieS, reproduceS, eatS);
			// System.out.println(x + " shark " + y);
			systeme.addAgentToAgentList(s);

		}

		for (int i = 0; i < tuna; i++) {
			int x = (int) (Math.random() * tailleEnvX);
			int y = (int) (Math.random() * tailleEnvY);
			Agent s = new Tuna(x, y, env, dieT, reproduceT, eatT);
			// System.out.println(x + " tuna" + y);
			systeme.addAgentToAgentList(s);

		}

		systeme.run(time);
		// systeme.runOnce();
		// systeme.runOnce();
	}
}

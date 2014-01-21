package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wator.Shark;
import wator.Tuna;

public class Systeme {
	protected List<Agent> agents;
	protected List<Agent> toDelete;
	protected List<Agent> toAdd;
	protected Environnement environnement;
	protected Vue vue;
	protected Long waitingTime = 50L;
	protected Long speed = 100L;

	public Systeme(Environnement env, Vue vue) {
		agents = new ArrayList<Agent>();
		toDelete = new ArrayList<Agent>();
		toAdd = new ArrayList<Agent>();
		environnement = env;
		this.vue = vue;
	}

	public void setWaitingTime(Long ms) {
		waitingTime = ms;
	}

	public void setSpeed(Long pourcentage) {
		speed = pourcentage;
	}

	public void removeAgent(Agent a) {
		int x = a.posX;
		int y = a.posY;
		Agent vide = new Vide(x, y, environnement);
		agents.remove(a);
		addAgent(vide);
	}

	public void addAgent(Agent a) {
		if (!agents.contains(a)) {
			if (!(a instanceof Vide)) {
				agents.add(a);
			}
			environnement.grille[a.posX][a.posY] = a;
		}
	}

	public void runOnce() {
		// printCount();
		Collections.shuffle(agents);
		for (Agent a : agents) {
			a.action();
		}
		updateAgentList();
		vue.update();
		printCount();
		try {
			Thread.sleep(waitingTime * speed / 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printCount() {
		int nbS = 0, nbT = 0;
		for (Agent a : agents) {
			if (a instanceof Shark) {
				nbS++;
			}
			if (a instanceof Tuna) {
				nbT++;
			}
		}
		System.out.println(nbS + " :" + nbT + " " + agents.size());
	}

	private void updateAgentList() {
		for (Agent a : toDelete) {
			removeAgent(a);
		}
		toDelete.clear();
		for (Agent a : toAdd) {
			agents.add(a);
		}
		toAdd.clear();
		// System.out.println("agents :" + agents);
	}

	public void run(int n) {
		for (int i = 0; i < n; i++) {
			runOnce();
		}
	}

	public void toDelete(Agent a) {
		toDelete.add(a);
	}

	public void toAdd(Agent a) {
		toAdd.add(a);
		environnement.grille[a.posX][a.posY] = a;
	}

}

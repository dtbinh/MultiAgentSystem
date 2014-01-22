package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public void run(int n) {
		for (int i = 0; i < n; i++) {
			runOnce();
		}
	}

	public void runOnce() {

		// printCount();

		vue.update();
		Collections.shuffle(agents);
		for (Agent agent : agents) {
			agent.action();
		}
		updateAgentLists();

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

	private void updateAgentLists() {

		for (Agent agent : toDelete) {
			removeAgent(agent);
		}
		toDelete.clear();

		for (Agent a : toAdd) {
			agents.add(a);
		}
		toAdd.clear();

	}

	public void removeAgent(Agent agent) {
		int x = agent.posX;
		int y = agent.posY;
		agents.remove(agent);
		setCellWithAgent(x, y, new Vide(x, y, environnement));
	}

	public void addAgentToAgentList(Agent agent) {
		agents.add(agent);
		setCellWithAgent(agent.posX, agent.posY, agent);
	}

	public void addToDeleteList(Agent agent) {
		toDelete.add(agent);
	}

	public void addToAddList(Agent agent) {
		toAdd.add(agent);
		setCellWithAgent(agent.posX, agent.posY, agent);
	}

	public void setCellWithAgent(int x, int y, Agent agent) {
		environnement.grille[x][y] = agent;
	}

	public void setWaitingTime(Long ms) {
		waitingTime = ms;
	}

	public void setSpeed(Long pourcentage) {
		speed = pourcentage;
	}

}

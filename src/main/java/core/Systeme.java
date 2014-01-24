package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;

import wator.Shark;
import wator.Statistique;
import wator.Tuna;

public class Systeme extends Observable {

	protected List<Agent> agents;
	// protected List<Agent> toDelete;
	protected List<Agent> toAdd;
	protected Environnement environnement;
	protected Vue vue;
	protected Long waitingTime = 50L;
	protected Long speed = 100L;

	public Systeme(Environnement env, Vue vue) {
		agents = new ArrayList<Agent>();
		// toDelete = new ArrayList<Agent>();
		toAdd = new ArrayList<Agent>();
		environnement = env;
		this.vue = vue;
		addObserver(vue);
	}

	public void run(int n) {
		setChanged();
		notifyObservers(this);
		for (int i = 0; i < n; i++) {
			runOnce();
		}
	}

	public void run() {
		setChanged();
		notifyObservers(this);
		while (true) {
			runOnce();
		}
	}

	public void runOnce() {
		count();
		try {
			Thread.sleep(waitingTime * speed / 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Object agentClone = ((ArrayList) agents).clone();
		for (Object objet : (ArrayList) agentClone) {
			Agent agent = (Agent) objet;
			agent.action();
		}
		updateAgentLists();
		setChanged();
		notifyObservers(this);
	}

	private void count() {
		int s = 0, t = 0, o = 0;
		for (Agent a : agents) {
			if (a instanceof Tuna) {
				t++;
			} else {
				if (a instanceof Shark) {
					s++;
				} else {
					o++;
				}
			}
		}
		Statistique.getInstance().setStats(
				o + ";" + t + ";" + s + ";" + agents.size());
	}

	private void updateAgentLists() {
		for (Agent a : toAdd) {
			agents.add(a);
		}
		toAdd.clear();
	}

	public void removeAgent(Agent agent) {
		agents.remove(agent);
		environnement.setPositionAgent(agent.coordonnees, false);
	}

	public void newAgent(Agent agent) {
		toAdd.add(agent);
		environnement.setPositionAgent(agent.coordonnees, true);
	}

	public void addAgent(Agent agent) {
		agents.add(agent);
		environnement.setPositionAgent(agent.coordonnees, true);
	}

	public void setWaitingTime(Long ms) {
		waitingTime = ms;
	}

	public void setSpeed(Long pourcentage) {
		speed = pourcentage;
	}

	public Agent getAgentByCoord(int tx, int ty) {
		for (Agent agent : agents) {
			if (agent.getPosX() == tx && agent.getPosY() == ty) {
				return agent;
			}
		}
		return null;
	}

	public Agent getAgentByCoord(Coordonnees coord) {
		return getAgentByCoord(coord.getPosX(), coord.getPosY());
	}

	public int getAgentsCount() {
		return agents.size();
	}

}

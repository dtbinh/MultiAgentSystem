package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class Systeme extends Observable {

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
		addObserver(vue);
	}

	public void run(int n) {
		for (int i = 0; i < n; i++) {
			runOnce();
		}
	}

	public void runOnce() {
		setChanged();
		notifyObservers(this);

		Collections.shuffle(agents);
		for (Agent agent : agents) {
			agent.action();
		}

		updateAgentLists();

		try {
			Thread.sleep(waitingTime * speed / 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void updateAgentLists() {

		for (Agent agent : toDelete) {
			agents.remove(agent);
		}
		toDelete.clear();

		for (Agent a : toAdd) {
			agents.add(a);
		}
		toAdd.clear();

	}

	public void removeAgent(Agent agent) {
		toDelete.add(agent);
		environnement.setPositionAgent(agent.coordonnees, false);
	}

	public void addAgent(Agent agent) {
		toAdd.add(agent);
		environnement.setPositionAgent(agent.coordonnees, true);
	}

	public void addAgentToAgentList(Agent agent) {
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

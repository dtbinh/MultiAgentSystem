package wator;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import core.Agent;
import core.Coordonnees;
import core.Environnement;

public class Shark extends Agent {

	protected int TIME_TO_EAT;
	protected int TIME_TO_REPRODUCE;

	protected int leftTimeToEat;
	protected int leftTimeToReproduce;

	public Shark(Coordonnees coordonnees, Environnement environnement) {
		super(coordonnees, environnement, Color.RED);
		setTimeToEat(5);
		setTimeToReproduce(7);
	}

	public void setTimeToReproduce(int time) {
		TIME_TO_REPRODUCE = time;
		leftTimeToReproduce = TIME_TO_REPRODUCE;
	}

	public void setTimeToEat(int time) {
		TIME_TO_EAT = time;
		leftTimeToEat = TIME_TO_EAT;
	}

	@Override
	public void action() {
		if (isDead) {
			die();
			Statistique.getInstance().addShark(-1);
			return;
		}

		evolution();
		//
		List<Coordonnees> voisins = environnement.getVoisins(coordonnees);
		Collections.shuffle(voisins);
		Agent agentToEat = null;
		Coordonnees coordToMove = null;
		Agent agentToTest = null;
		// find the way to go. if i find a fish out.
		// i keep it to eat it and i break the loop.
		// else if i find a cell to go out i keep it too. else i don't move
		for (Coordonnees voisin : voisins) {
			if (canMove(voisin)) {
				coordToMove = voisin;
			} else {
				agentToTest = systeme.getAgentByCoord(voisin);
				if (canEat(agentToTest)) {
					agentToEat = agentToTest;
					break;
				}
			}
		}

		if (isStarved()) {
			setDead(true);
			return;
		}
		if (agentToEat != null) {
			if (canReproduce()) {
				reproduce();
			}
			eat(agentToEat);
			moveTo(agentToEat.getCoordonnees());
		} else {
			if (coordToMove != null) {
				if (canReproduce()) {
					reproduce();
				}
				moveTo(coordToMove);
			}
		}

	}

	private boolean isStarved() {
		return leftTimeToEat <= 0;
	}

	private boolean canEat(Agent agent) {
		return agent instanceof Tuna;
	}

	protected void eat(Agent agent) {
		kill(agent);
		leftTimeToEat = TIME_TO_EAT;
	}

	private void evolution() {
		age++;
		leftTimeToEat--;
		leftTimeToReproduce--;
	}

	protected void reproduce() {
		Agent babyShark = new Shark(coordonnees, environnement);
		leftTimeToReproduce = TIME_TO_REPRODUCE;
		super.reproduce(babyShark);
	}

	private boolean canReproduce() {
		return leftTimeToReproduce <= 0;
	}

}

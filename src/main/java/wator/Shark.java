package wator;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import core.Agent;
import core.Environnement;
import core.Vide;

public class Shark extends Agent {

	public Shark(int posX, int posY, Environnement environnement, int die,
			int reproduce, int eat) {
		super(posX, posY, environnement, die, reproduce, eat, Color.BLACK);
	}

	@Override
	public void action() {
		if (isStarved()) {
			die();
			return;
		}

		List<Agent> voisins = environnement.getVoisins(posX, posY);
		Collections.shuffle(voisins);
		Agent toEat = null;
		Agent toMove = null;
		for (Agent voisin : voisins) {
			if (voisin instanceof Tuna) {
				toEat = voisin;
				break;
			}
			if (voisin instanceof Vide) {
				toMove = voisin;
			}
		}

		if (canMove(toEat)) {
			if (canReproduce()) {
				reproduce(toEat);
			}
			eat(toEat);
			move(toEat);
		} else {
			if (canMove(toMove)) {
				if (canReproduce()) {
					reproduce(toMove);
				}
				move(toMove);
			}
		}

		leftTimeToReproduce--;
		leftTimeToEat--;
		age++;

	}

	private void die() {
		systeme.addToDeleteList(this);
	}

	private boolean canReproduce() {
		return leftTimeToReproduce <= 0;
	}

	private boolean canMove(Agent toMove) {
		return toMove != null;
	}

	private boolean isStarved() {
		return leftTimeToEat == 0;
	}

	@Override
	protected void reproduce(Agent toMove) {
		Agent shark = new Shark(posX, posY, environnement, TIME_TO_DIE,
				TIME_TO_REPRODUCE, TIME_TO_EAT);
		systeme.addToAddList(shark);

		leftTimeToReproduce = TIME_TO_REPRODUCE;
	}

	private void move(Agent toMove) {
		environnement.move(this, toMove);
	}

	public void eat(Agent agent) {

		leftTimeToEat = TIME_TO_EAT;

		agent.setDead(true);

		systeme.addToDeleteList(agent);
		systeme.setCellWithAgent(posX, posY,
				new Vide(posX, posY, environnement));
	}
}

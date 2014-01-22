package wator;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import core.Agent;
import core.Environnement;
import core.Vide;

public class Tuna extends Agent {

	public Tuna(int posX, int posY, Environnement environnement, int die,
			int reproduce, int eat) {
		super(posX, posY, environnement, die, reproduce, eat, Color.blue);
	}

	@Override
	public void action() {

		if (isDead) {
			return;
		}

		List<Agent> voisins = environnement.getVoisins(posX, posY);
		Collections.shuffle(voisins);

		Agent agentToMove = null;
		for (Agent voisin : voisins) {
			if (voisinEstCaseVide(voisin)) {
				agentToMove = voisin;
				break;
			}
		}

		if (canMove(agentToMove)) {
			reproduceAndMoveOrOnlyMove(agentToMove);
		}

		--leftTimeToReproduce;

	}

	private boolean voisinEstCaseVide(Agent voisin) {
		return voisin instanceof Vide;
	}

	private void reproduceAndMoveOrOnlyMove(Agent toMove) {
		if (canReproduce()) {
			reproduce(toMove);
		} else {
			environnement.move(this, toMove);
		}
	}

	private boolean canReproduce() {
		return leftTimeToReproduce <= 0;
	}

	private boolean canMove(Agent toMove) {
		return toMove != null;
	}

	@Override
	protected void reproduce(Agent toMove) {
		Agent tuna = new Tuna(posX, posY, environnement, TIME_TO_DIE,
				TIME_TO_REPRODUCE, TIME_TO_EAT);
		environnement.move(this, toMove);
		systeme.addToAddList(tuna);
	}

}

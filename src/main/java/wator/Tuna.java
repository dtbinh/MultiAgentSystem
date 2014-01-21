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
			systeme.toDelete(this);
			return;
		}
		List<Agent> voisins = environnement.getVoisins(posX, posY);
		Collections.shuffle(voisins);
		Agent toMove = null;
		for (Agent s : voisins) {
			if (s instanceof Vide) {
				toMove = s;
				break;
			}
		}
		if (toMove != null) {
			--leftTimeToReproduce;
			if (leftTimeToReproduce <= 0) {
				seReproduire(toMove);
			} else {
				environnement.move(this, toMove);
			}
		}

	}

	@Override
	protected void seReproduire(Agent toMove) {
		Agent tuna = new Tuna(posX, posY, environnement, TIME_TO_DIE,
				TIME_TO_REPRODUCE, TIME_TO_EAT);
		environnement.move(this, toMove);
		systeme.toAdd(tuna);

	}

}

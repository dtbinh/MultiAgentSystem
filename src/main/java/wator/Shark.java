package wator;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

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
		if (isDead) {
			systeme.toDelete(this);
			return;
		}
		List<Agent> voisins = environnement.getVoisins(posX, posY);
		Collections.shuffle(voisins);
		Agent toEat = null;
		Agent toMove = null;
		for (Agent s : voisins) {
			if (s instanceof Shark) {
				toEat = s;
			}
			if (s instanceof Vide) {
				toMove = s;
			}
		}

		if (canMove(toEat)) {
			if (canReproduce()) {
				seReproduire(toEat);
			} else {
				mange(toEat);
				leftTimeToReproduce--;
			}
		} else {
			if (isStarved()) {
				isDead = true;
				systeme.toDelete(this);
				return;
			}
			if (canMove(toMove)) {
				if (canReproduce()) {
					seReproduire(toMove);
				} else {
					leftTimeToReproduce--;
					environnement.move(this, toMove);
				}
			} else {
				leftTimeToReproduce--;
			}
		}
		age++;

	}

	private boolean canReproduce() {
		return leftTimeToReproduce <= 0;
	}

	private boolean canMove(Agent toMove) {
		return toMove != null;
	}

	private boolean isStarved() {
		return --leftTimeToEat == 0;
	}

	@Override
	protected void seReproduire(Agent toMove) {
		Agent shark = new Shark(posX, posY, environnement, TIME_TO_DIE,
				TIME_TO_REPRODUCE, TIME_TO_EAT);
		leftTimeToReproduce = TIME_TO_REPRODUCE;
		environnement.move(this, toMove);
		systeme.toAdd(shark);
	}

	public void mange(Agent a) {
		leftTimeToEat = TIME_TO_EAT;
		environnement.move(this, a);
		a.setDead(true);
		systeme.toDelete(a);
	}

}

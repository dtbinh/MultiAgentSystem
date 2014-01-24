package wator;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import core.Agent;
import core.Coordonnees;
import core.Environnement;

public class Tuna extends Agent {

	protected int TIME_TO_REPRODUCE;
	protected int leftTimeToReproduce;

	public Tuna(Coordonnees coordonnees, Environnement environnement) {
		super(coordonnees, environnement, Color.blue);
		setTimeToReproduce(4);
	}

	public void setTimeToReproduce(int time) {
		TIME_TO_REPRODUCE = time;
		leftTimeToReproduce = TIME_TO_REPRODUCE;
	}

	@Override
	public void action() {
		if (isDead) {
			return;
		}

		evolution();

		List<Coordonnees> voisins = environnement.getVoisins(coordonnees);
		Collections.shuffle(voisins);
		for (Coordonnees voisin : voisins) {
			if (canMove(voisin)) {
				if (canReproduce()) {
					reproduce();
				}
				moveTo(voisin);
				return;
			}
		}
	}

	private boolean canReproduce() {
		return leftTimeToReproduce <= 0;
	}

	private void evolution() {
		age++;
		leftTimeToReproduce--;
	}

	protected void reproduce() {
		Agent babyTuna = new Tuna(coordonnees, environnement);
		leftTimeToReproduce = TIME_TO_REPRODUCE;
		super.reproduce(babyTuna);
	}
}

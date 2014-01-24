package wator;

import java.awt.Color;

import core.Agent;
import core.Coordonnees;
import core.Environnement;

public class Tuna extends Agent {

	protected int TIME_TO_REPRODUCE;
	protected int leftTimeToReproduce;

	public Tuna(final Coordonnees coordonnees, final Environnement environnement) {
		super(coordonnees, environnement, Color.green);
		setTimeToReproduce(4);
	}

	@Override
	public void action() {
		if (isDead) {
			return;
		}

		vieillis();

		for (final Coordonnees voisin : environnement
				.getCoordonneesVoisines(coordonnees)) {

		}
	}

	/**
	 * Fait "vieillir" le thon d'un jour
	 */
	private void vieillis() {
		age++;
		leftTimeToReproduce--;
	}

	/**
	 * @return
	 */
	private boolean canReproduce() {
		return leftTimeToReproduce <= 0;
	}

	/**
	 * Setter
	 * 
	 * @param time
	 */
	public void setTimeToReproduce(final int time) {
		TIME_TO_REPRODUCE = time;
		leftTimeToReproduce = TIME_TO_REPRODUCE;
	}
}

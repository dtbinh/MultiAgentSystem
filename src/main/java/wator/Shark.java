package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import core.Agent;
import core.Case;
import core.Coordonnees;
import core.Environnement;

@Data
public class Shark extends Agent {

	private boolean aDejaJoue = false;

	private int TIME_TO_EAT;
	private int TIME_TO_REPRODUCE;

	private int leftTimeToEat;
	private int leftTimeToReproduce;

	/**
	 * Constructor
	 * 
	 * @param coordonnees
	 * @param environnement
	 */
	public Shark(final Coordonnees coordonnees,
			final Environnement environnement) {
		super(coordonnees, environnement, Color.RED);
		setTimeToEat(5);
		setTimeToReproduce(7);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.Agent#action()
	 */
	@Override
	public void action() {

		if (isStarved()) {
			// TODO JIV : ajouter le méthode le faisant mourrir
			return;
		}

		vieillis();

		final List<Case> voisinsMangeables = new ArrayList<Case>();

		for (final Coordonnees voisin : environnement
				.getCoordonneesVoisines(coordonnees)) {

		}

	}

	/**
	 * Fait "vieillir" d'un jour le requin
	 */
	private void vieillis() {
		age++;
		leftTimeToEat--;
		leftTimeToReproduce--;
	}

	/**
	 * @return
	 */
	private boolean isStarved() {
		return leftTimeToEat == 0;
	}

	/**
	 * @return
	 */
	private boolean canReproduce() {
		return leftTimeToReproduce == 0;
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

	/**
	 * Setter
	 * 
	 * @param time
	 */
	public void setTimeToEat(final int time) {
		TIME_TO_EAT = time;
		leftTimeToEat = TIME_TO_EAT;
	}

}

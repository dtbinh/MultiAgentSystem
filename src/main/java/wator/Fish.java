package wator;

import java.awt.Color;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import core.Agent;
import core.Case;
import core.Coordonnees;
import core.Environnement;

/**
 * Class de factorisation de code entre ce qui est commun pour les requins et
 * les thons.
 * 
 * @author Jules
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Fish extends Agent {

	protected int TIME_TO_REPRODUCE;

	protected int leftTimeToReproduce;

	public Fish(final Coordonnees coordonnees,
			final Environnement environnement, final Color color) {
		super(coordonnees, environnement, color);
	}

	/**
	 * @return
	 */
	protected boolean canReproduce() {
		return leftTimeToReproduce == 0;
	}

	/**
	 * @param casesVoisinesLibres
	 * @return
	 */
	protected boolean canMove(final List<Case> casesVoisinesLibres) {
		return !casesVoisinesLibres.isEmpty();
	}

	/**
	 * 
	 */
	protected void birth(final Fish newFish) {
		getCurrentCase().setAgent(newFish);
		leftTimeToReproduce = TIME_TO_REPRODUCE;
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

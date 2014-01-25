package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import core.Case;
import core.Coordonnees;
import core.Environnement;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Shark extends Fish {

	private int TIME_TO_EAT;

	private int leftTimeToEat;

	/**
	 * Constructor
	 * 
	 * @param coordonnees
	 * @param environnement
	 */
	public Shark(final Coordonnees coordonnees,
			final Environnement environnement) {
		super(coordonnees, environnement, new Color(30, 35, 38));
	}

	/**
	 * Constructor
	 * 
	 * @param coordonnees
	 * @param ocean
	 * @param timeToBeStarved
	 * @param reproductionShark
	 */
	public Shark(final Coordonnees coordonnees,
			final Environnement environnement, final int timeToBeStarved,
			final int reproductionShark) {
		this(coordonnees, environnement);
		setTimeToEat(timeToBeStarved);
		setTimeToReproduce(reproductionShark);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.Agent#action()
	 */
	@Override
	public void action() {

		if (aDejaJoue) {
			return;
		}

		aDejaJoue = true;

		if (isStarved()) {
			die();
			return;
		}

		vieillis();

		final List<Case> casesContenantVoisinsMangeables = new ArrayList<Case>();
		final List<Case> casesVoisinesLibres = new ArrayList<Case>();

		for (final Coordonnees voisin : environnement
				.getCoordonneesVoisines(coordonnees)) {
			final Case caseVoisine = environnement
					.getCaseFromCoordonnees(voisin);
			if (caseVoisine.isVide()) {
				casesVoisinesLibres.add(caseVoisine);
			} else if (caseVoisine.getAgent() instanceof Tuna) {
				casesContenantVoisinsMangeables.add(caseVoisine);
			}
		}

		makeAction(casesContenantVoisinsMangeables, casesVoisinesLibres);
	}

	/**
	 * Réalise les actions du requins.
	 * 
	 * @param casesContenantVoisinsMangeables
	 * @param casesVoisinesLibres
	 */
	private void makeAction(final List<Case> casesContenantVoisinsMangeables,
			final List<Case> casesVoisinesLibres) {

		final boolean canMove = canMove(casesVoisinesLibres);

		// 1. S'il peut bouger et qu'il est en capacité de se reporduire il se
		// reproduit et bouge mais ne fait rien d'autre.
		if (canReproduce() && canMove) {
			birth(new Shark(coordonnees, environnement, TIME_TO_EAT,
					TIME_TO_REPRODUCE));
			move(casesVoisinesLibres);

			return;
		}

		// 2. S'il peut manger, il mange et il bouge sur la case de la proie
		// mangé et ne fait rien d'autre.
		if (canEat(casesContenantVoisinsMangeables)) {
			// le move, en remplacent l'agent de la case où l'on se déplace
			// simule le fait de le manger.
			move(casesContenantVoisinsMangeables);
			// et on remet son temps pour manger a TIME_TO_EAT
			leftTimeToEat = TIME_TO_EAT;

			return;
		}

		// 3. S'il peut bouger il bouge sur une case libre autour de lui au
		// hasard et ne fait rien d'autre.
		if (canMove(casesVoisinesLibres)) {
			move(casesVoisinesLibres);
		}
	}

	/**
	 * @param casesVoisines
	 */
	private void move(final List<Case> casesVoisines) {
		if (!canReproduce()) {
			// On vide la case actuel si on ne s'est pas reproduit. (sinon elle
			// contient notre bébé et il ne faut pas l'effacer)
			emptyCurrentCase();
		}
		Collections.shuffle(casesVoisines);
		final Case caseVoisine = casesVoisines.get(0);
		// MAJ des coordonnées du requin
		setCoordonnees(caseVoisine.getCoordonnees());
		// Et déplacement de celui-ci dans sa nouvelle case.
		caseVoisine.setAgent(this);
	}

	/**
	 * Simule la mort du requin
	 */
	private void die() {
		emptyCurrentCase();
	}

	/**
	 * @param casesContenantVoisinsMangeables
	 * @return
	 */
	private boolean canEat(final List<Case> casesContenantVoisinsMangeables) {
		return !casesContenantVoisinsMangeables.isEmpty();
	}

	/**
	 * Fait "vieillir" d'un jour le requin
	 */
	private void vieillis() {
		age++;
		leftTimeToEat--;
		if (leftTimeToEat < 0) {
			leftTimeToEat = 0;
		}
		leftTimeToReproduce--;
		if (leftTimeToReproduce < 0) {
			leftTimeToReproduce = 0;
		}
	}

	/**
	 * @return
	 */
	private boolean isStarved() {
		return leftTimeToEat == 0;
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

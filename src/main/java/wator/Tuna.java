package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import core.Case;
import core.Coordonnees;
import core.Environnement;

@Data
public class Tuna extends Fish {

	/**
	 * Construtor
	 * 
	 * @param coordonnees
	 * @param environnement
	 */
	public Tuna(final Coordonnees coordonnees, final Environnement environnement) {
		super(coordonnees, environnement, Color.MAGENTA);
	}

	/**
	 * @param coordonnees
	 * @param ocean
	 * @param reproductionTuna
	 */
	public Tuna(final Coordonnees coordonnees,
			final Environnement environnement, final int reproductionTuna) {
		this(coordonnees, environnement);
		setTimeToReproduce(reproductionTuna);
	}

	@Override
	public void action() {

		if (aDejaJoue) {
			return;
		}

		aDejaJoue = true;

		vieillis();

		final List<Case> casesVoisinesLibres = new ArrayList<Case>();

		for (final Coordonnees voisin : environnement
				.getCoordonneesVoisines(coordonnees)) {
			final Case caseVoisine = environnement
					.getCaseFromCoordonnees(voisin);
			if (caseVoisine.isVide()) {
				casesVoisinesLibres.add(caseVoisine);
			}
		}

		makeAction(casesVoisinesLibres);
	}

	/**
	 * Réalise les actions du thon.
	 * 
	 * @param casesVoisinesLibres
	 */
	private void makeAction(final List<Case> casesVoisinesLibres) {

		if (canMove(casesVoisinesLibres)) {

			if (canReproduce()) {
				final Tuna babyTuna = new Tuna(coordonnees, environnement);
				babyTuna.setTimeToReproduce(TIME_TO_REPRODUCE);
				birth(babyTuna);
				leftTimeToReproduce = TIME_TO_REPRODUCE;
			} else {
				emptyCurrentCase();
			}

			Collections.shuffle(casesVoisinesLibres);
			final Case caseLibre = casesVoisinesLibres.get(0);
			// MAJ des coordonnées du thon
			setCoordonnees(caseLibre.getCoordonnees());
			// Et déplacement de celui-ci dans sa nouvelle case.
			caseLibre.setAgent(this);
		}
	}

	/**
	 * Fait "vieillir" le thon d'un jour
	 */
	private void vieillis() {
		age++;
		leftTimeToReproduce--;
		if (leftTimeToReproduce < 0) {
			leftTimeToReproduce = 0;
		}
	}

}

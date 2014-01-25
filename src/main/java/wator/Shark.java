package wator;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import core.Case;
import core.Coordonnees;
import core.Environnement;

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
		setTimeToEat(3);
		setTimeToReproduce(7);
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

		if (canEat(casesContenantVoisinsMangeables)) {

			if (canReproduce()) {
				birth(new Shark(coordonnees, environnement));
				leftTimeToReproduce = TIME_TO_REPRODUCE;
			} else {
				emptyCurrentCase();
			}

			Collections.shuffle(casesContenantVoisinsMangeables);
			final Case caseContenantVoisinMangeable = casesContenantVoisinsMangeables
					.get(0);
			// MAJ des coordonnées du requin
			setCoordonnees(caseContenantVoisinMangeable.getCoordonnees());
			// Et déplacement de celui-ci dans sa nouvelle case.
			caseContenantVoisinMangeable.setAgent(this);
			// et on remet son temps pour manger a TIME TO EAT
			leftTimeToEat = TIME_TO_EAT;

			return;
		}

		if (canMove(casesVoisinesLibres)) {

			if (canReproduce()) {
				birth(new Shark(coordonnees, environnement));
				leftTimeToReproduce = TIME_TO_REPRODUCE;
			} else {
				emptyCurrentCase();
			}

			Collections.shuffle(casesVoisinesLibres);
			final Case caseLibre = casesVoisinesLibres.get(0);
			// MAJ des coordonnées du requin
			setCoordonnees(caseLibre.getCoordonnees());
			// Et déplacement de celui-ci dans sa nouvelle case.
			caseLibre.setAgent(this);
		}
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

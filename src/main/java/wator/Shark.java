package wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
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
			final Case caseVoisine = environnement.getGrille()[voisin.getX()][voisin
					.getY()];

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
			Collections.shuffle(casesVoisinesLibres);

			final Case caseContenantLaProie = casesContenantVoisinsMangeables
					.get(0);
			eat(caseContenantLaProie);

			if (canReproduce()) {
				birth();
			} else {
				emptyCurrentCase();
			}

			caseContenantLaProie.setAgent(this);
			return;
		}

		if (canMove(casesVoisinesLibres)) {
			if (canReproduce()) {
				birth();
			} else {
				emptyCurrentCase();
			}

			Collections.shuffle(casesVoisinesLibres);
			casesVoisinesLibres.get(0).setAgent(this);
			return;
		}

	}

	/**
	 * Simule la mort du requin
	 */
	private void die() {
		emptyCurrentCase();
	}

	/**
	 * 
	 */
	private void birth() {
		getCurrentCase().setAgent(new Shark(coordonnees, environnement));
	}

	/**
	 * Réalise l'action de manger une proie.
	 * 
	 * @param caseContenantLaProie
	 */
	private void eat(final Case caseContenantLaProie) {
		caseContenantLaProie.setAgent(this);
	}

	/**
	 * @param casesContenantVoisinsMangeables
	 * @return
	 */
	private boolean canEat(final List<Case> casesContenantVoisinsMangeables) {
		return !casesContenantVoisinsMangeables.isEmpty();
	}

	/**
	 * @param casesVoisinesLibres
	 * @return
	 */
	private boolean canMove(final List<Case> casesVoisinesLibres) {
		return !casesVoisinesLibres.isEmpty();
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
	 * Vide la case courante de l'agent qu'elle contient.
	 */
	private void emptyCurrentCase() {
		getCurrentCase().setAgent(null);
	}

	/**
	 * @return
	 */
	private Case getCurrentCase() {
		return environnement.getGrille()[coordonnees.getX()][coordonnees.getY()];
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

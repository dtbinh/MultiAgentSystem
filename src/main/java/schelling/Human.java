package schelling;

import java.awt.Color;
import java.util.List;

import core.Agent;
import core.Case;
import core.Coordonnees;
import core.Environnement;

public abstract class Human extends Agent {

	protected int tauxSatisfaction;
	protected int actualTaux;

	public Human(final Coordonnees coordonnees,
			final Environnement environnement, final int tauxSatisfaction,
			final Color color) {
		super(coordonnees, environnement, color);
		this.tauxSatisfaction = tauxSatisfaction;
	}

	protected boolean isSatisfied() {
		return actualTaux >= tauxSatisfaction;
	}

	@Override
	public void action() {
		if (aDejaJoue) {
			return;
		}
		aDejaJoue = true;
		calculActualTaux();
		if (!isSatisfied()) {
			move();
		}

	}

	private void move() {
		final List<Coordonnees> moveOut = environnement
				.getCoordonneesDeLaGrille();
		for (final Coordonnees c : moveOut) {
			final Case nextCase = environnement.getCaseFromCoordonnees(c);
			if (nextCase.isVide()) {
				getCurrentCase().setAgent(null);
				setCoordonnees(c);
				nextCase.setAgent(this);
				return;
			}
		}
	}

	public void calculActualTaux() {
		final List<Coordonnees> mesVoisins = environnement
				.getCoordonneesVoisines(coordonnees);
		int voisinAcceptable = 0;
		for (final Coordonnees coord : mesVoisins) {
			// discutable? sommes nous satisfait si case vide?
			final Case current = environnement.getCaseFromCoordonnees(coord);
			if (current.isVide()) {
				voisinAcceptable++;
				continue;
			}
			if (current.getAgent().getClass() == this.getClass()) {
				voisinAcceptable++;
				continue;
			}
		}
		actualTaux = voisinAcceptable * 100 / 8;
	}

	public int getTaux() {
		return actualTaux;
	}
}

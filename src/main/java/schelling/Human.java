package schelling;

import java.awt.Color;

import core.Agent;
import core.Coordonnees;
import core.Environnement;

public abstract class Human extends Agent {

	protected boolean isSatisfied;
	protected int tauxSatisfaction;

	public Human(final Coordonnees coordonnees,
			final Environnement environnement, final int tauxSatisfaction,
			final Color color) {
		super(coordonnees, environnement, color);
		isSatisfied = false;
		this.tauxSatisfaction = tauxSatisfaction;
	}

	public void isSatisfied() {

	}

	@Override
	public void action() {

	}
}

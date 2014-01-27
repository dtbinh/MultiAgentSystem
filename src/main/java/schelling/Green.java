package schelling;

import java.awt.Color;

import core.Coordonnees;
import core.Environnement;

public class Green extends Human {

	public Green(final Coordonnees coordonnees, final int tauxSatisfaction,
			final Environnement environnement) {
		super(coordonnees, environnement, tauxSatisfaction, Color.GREEN);
	}

}

package schelling;

import java.awt.Color;

import core.Coordonnees;
import core.Environnement;

public class Red extends Human {

	public Red(final Coordonnees coordonnees, final int tauxSatisfaction,
			final Environnement environnement) {
		super(coordonnees, environnement, tauxSatisfaction, Color.RED);
	}

}

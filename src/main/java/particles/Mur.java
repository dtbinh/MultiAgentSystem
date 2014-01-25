package particles;

import java.awt.Color;

import core.Agent;
import core.Coordonnees;
import core.Environnement;

public class Mur extends Agent {

	public Mur(final Coordonnees coordonnees,
			final Environnement environnement, final Color color) {
		super(coordonnees, environnement, color);
	}

	@Override
	public void action() {
	}

}

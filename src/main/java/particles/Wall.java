package particles;

import java.awt.Color;

import core.Agent;
import core.Coordonnees;
import core.Environnement;

public class Wall extends Agent {

	public Wall(final Coordonnees coordonnees, final Environnement environnement) {
		super(coordonnees, environnement, Color.black);
	}

	@Override
	public void action() {

	}

}

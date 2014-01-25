package particles;

import java.awt.Color;

import core.Agent;
import core.Case;
import core.Coordonnees;
import core.Environnement;

public class Bille extends Agent {

	protected Direction direction;

	public Bille(Coordonnees coordonnees, Environnement environnement) {
		super(coordonnees, environnement, new Color((int) Math.random() * 256,
				(int) Math.random() * 256, (int) Math.random() * 256));
	}

	@Override
	public void action() {
		if (aDejaJoue) {
			return;
		}

		Case next = environnement.getCaseFromCoordonnees(coordonnees.getX()
				+ direction.getX(), coordonnees.getY() + direction.getY());
		if (next.isNotVide()) {
			getCurrentCase().setAgent(null);
			setCoordonnees(next.getCoordonnees());
			next.setAgent(this);
			return;
		}
		direction.getRebound();

	}
}

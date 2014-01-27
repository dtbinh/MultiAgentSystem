package particles;

import java.awt.Color;

import lombok.ToString;
import core.Agent;
import core.Case;
import core.Coordonnees;
import core.Environnement;

@ToString(exclude = { "environnement" })
public class Bille extends Agent {

	protected Direction direction;

	public Bille(final Coordonnees coordonnees,
			final Environnement environnement) {
		super(coordonnees, environnement, new Color(
				(int) (Math.random() * 200) + 30,
				(int) (Math.random() * 200) + 30,
				(int) (Math.random() * 200) + 30));
		direction = Direction.getRandomDirection();
	}

	@Override
	public void action() {
		if (aDejaJoue) {
			return;
		}

		final int thisX = coordonnees.getX();
		final int thisY = coordonnees.getY();
		// aDejaJoue = true;
		final Case next = environnement.getCaseFromCoordonnees(thisX
				+ direction.getX(), thisY + direction.getY());

		if (next.isVide()) {
			move(next);
		} else {
			direction.getRebound(next.getCoordonnees(),
					environnement.getTailleSup() + 1);
		}
	}

	private void move(final Case next) {
		getCurrentCase().setAgent(null);
		setCoordonnees(next.getCoordonnees());
		next.setAgent(this);
	}

}

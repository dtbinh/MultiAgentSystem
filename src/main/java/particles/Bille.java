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
				(int) (Math.random() * 256), (int) (Math.random() * 256),
				(int) (Math.random() * 256)));
		// direction = Direction.getRandomDirection();
		direction = new Direction(1, 1);
	}

	@Override
	public void action() {
		System.out.println("***********JE JOUE*********");
		if (aDejaJoue) {
			return;
		}
		final int thisX = coordonnees.getX();
		final int thisY = coordonnees.getY();
		Case next = null;
		do {
			next = environnement.getCaseFromCoordonnees(
					thisX + direction.getX(), thisY + direction.getY());

			direction.getRebound(next.getCoordonnees(),
					environnement.getTailleSup() + 1);
		} while (next.isNotVide());
		move(next);
	}

	private void move(final Case next) {
		getCurrentCase().setAgent(null);
		setCoordonnees(next.getCoordonnees());
		next.setAgent(this);
	}

}

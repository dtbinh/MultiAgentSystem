package core;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

import lombok.Data;

@Data
public class Systeme extends Observable {

	protected Environnement environnement;

	protected Long waitingTime = 50L;
	protected Long speed = 100L;

	/**
	 * Constructor
	 * 
	 * @param env
	 * @param vue
	 */
	public Systeme(final Environnement env) {
		environnement = env;
	}

	/**
	 * Run the system
	 */
	public void run() {
		while (true) {
			runOneTurn();
		}
	}

	/**
	 * Run one turn of the system
	 */
	public void runOneTurn() {
		slowExecution();

		final List<Coordonnees> coordonnéesDeLaGrille = environnement
				.getCoordonneesDeLaGrille();

		Collections.shuffle(coordonnéesDeLaGrille);

		for (final Coordonnees coordonnees : coordonnéesDeLaGrille) {

			final Case case0 = environnement.getGrille()[coordonnees.getX()][coordonnees
					.getY()];

			if (case0.isNotVide()) {
				case0.getAgent().action();
			}
		}
	}

	private void slowExecution() {
		try {
			Thread.sleep(waitingTime * speed / 100);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

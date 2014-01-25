package core;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

import lombok.Data;

@Data
public class Systeme extends Observable {

	protected Environnement environnement;
	protected Vue vue;

	protected Long waitingTime = 50L;
	protected Long speed = 100L;
	protected Statistique statistique;

	/**
	 * Constructor
	 * 
	 * @param env
	 * @param vue
	 */
	public Systeme(final Vue vue, final Environnement env) {
		environnement = env;
		this.vue = vue;
		addObserver(vue);
	}

	/**
	 * Run the system
	 */
	public void run() {
		setChanged();
		this.notifyObservers();
		while (true) {
			runOneTurn();
		}
	}

	/**
	 * Run the system
	 */
	public void run(int n) {
		setChanged();
		this.notifyObservers();
		while (n > 0) {
			runOneTurn();
			n--;
		}
	}

	/**
	 * Run one turn of the system
	 */
	public void runOneTurn() {
		slowExecution();

		// Je remets à false le fait que les agents aient jouer à ce tour.
		resetDelaCase();

		final List<Coordonnees> coordonnéesDeLaGrille = environnement
				.getCoordonneesDeLaGrille();

		Collections.shuffle(coordonnéesDeLaGrille);

		for (final Coordonnees coordonnees : coordonnéesDeLaGrille) {

			final Case currentCase = environnement
					.getCaseFromCoordonnees(coordonnees);
			if (currentCase.isNotVide()) {
				currentCase.getAgent().action();
			}
		}
		setChanged();
		this.notifyObservers();
	}

	/**
	 * Remets à "false" le boolean de tous les agents.
	 */
	private void resetDelaCase() {
		for (final Coordonnees coordonnees : environnement
				.getCoordonneesDeLaGrille()) {
			final Case caseParcouru = environnement
					.getCaseFromCoordonnees(coordonnees);
			if (caseParcouru.isNotVide()) {
				caseParcouru.getAgent().setADejaJoue(false);
			}

		}
	}

	private void slowExecution() {
		try {
			Thread.sleep(waitingTime * speed / 100);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}

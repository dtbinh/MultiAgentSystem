package wator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import core.Case;
import core.Coordonnees;
import core.Environnement;
import core.Systeme;

@Data
public class Ocean implements Environnement {

	private Systeme systeme;

	private final Case[][] grille;

	private final int tailleX;

	private final int tailleY;

	private final boolean isTore;

	private final List<Coordonnees> coordonneesDeLaGrille = new ArrayList<Coordonnees>();

	/**
	 * Constructor
	 * 
	 * @param tailleX
	 * @param tailleY
	 * @param initialNumberOfTuna
	 * @param initialNumberOfShark
	 * @param reproductionTuna
	 * @param reproductionShark
	 * @param timeToBeStarved
	 * @param isTore
	 */
	public Ocean(final int tailleX, final int tailleY,
			final int initialNumberOfShark, final int initialNumberOfTuna,
			final int timeToBeStarved, final int reproductionShark,
			final int reproductionTuna, final boolean isTore) {

		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.isTore = isTore;
		grille = new Case[tailleX][tailleY];

		initGrille(initialNumberOfShark, initialNumberOfTuna, timeToBeStarved,
				reproductionShark, reproductionTuna);
	}

	/**
	 * Constructor
	 * 
	 * @param taille
	 * @param reproductionTuna
	 * @param reproductionShark
	 * @param timeToBeStarved
	 */
	public Ocean(final int taille, final int initialNumberOfShark,
			final int initialNumberOfTuna, final int timeToBeStarved,
			final int reproductionShark, final int reproductionTuna) {
		this(taille, taille, initialNumberOfShark, initialNumberOfTuna,
				timeToBeStarved, reproductionShark, reproductionTuna, true);
	}

	/**
	 * Initialise complétement notre grille pour le premier tour de jeu.
	 * 
	 * @param initialNumberOfShark
	 * @param initialNumberOfTuna
	 * @param reproductionTuna
	 * @param reproductionShark
	 * @param timeToBeStarved
	 */
	private void initGrille(final int initialNumberOfShark,
			final int initialNumberOfTuna, final int timeToBeStarved,
			final int reproductionShark, final int reproductionTuna) {
		remplirGrilleAvecCasesVides();
		initGrilleWithSharksAndTunas(initialNumberOfShark, initialNumberOfTuna,
				timeToBeStarved, reproductionShark, reproductionTuna);
	}

	/**
	 * Initialise la grille
	 */
	private void remplirGrilleAvecCasesVides() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				final Coordonnees coordonnees = new Coordonnees(x, y);
				grille[x][y] = new Case(coordonnees);
				coordonneesDeLaGrille.add(coordonnees);
			}
		}
	}

	/**
	 * Ajoute les requins et les thons de façon aléatoire sur la grille.
	 * 
	 * @param initialNumberOfShark
	 * @param initialNumberOfTuna
	 * @param reproductionTuna
	 * @param reproductionShark
	 * @param timeToBeStarved
	 */
	private void initGrilleWithSharksAndTunas(final int initialNumberOfShark,
			final int initialNumberOfTuna, final int timeToBeStarved,
			final int reproductionShark, final int reproductionTuna) {

		Collections.shuffle(coordonneesDeLaGrille);
		int i = 0;
		for (; i < initialNumberOfShark; i++) {
			final Coordonnees coordonnees = coordonneesDeLaGrille.get(i);
			grille[coordonnees.getX()][coordonnees.getY()].setAgent(new Shark(
					coordonnees, this, timeToBeStarved, reproductionShark));
		}
		for (; i < initialNumberOfTuna + initialNumberOfShark; i++) {
			final Coordonnees coordonnees = coordonneesDeLaGrille.get(i);
			grille[coordonnees.getX()][coordonnees.getY()].setAgent(new Tuna(
					coordonnees, this, reproductionTuna));
		}

	}

	public List<Coordonnees> getCoordonneesVoisines(
			final Coordonnees coordonnees) {

		// TODO RAL : Ajouter le code pour le cas d'une grille non torique.

		final List<Coordonnees> res = new ArrayList<Coordonnees>();
		final int posX = coordonnees.getX();
		final int posY = coordonnees.getY();

		if (isTore) {
			res.add(new Coordonnees(((posX - 1) + tailleX) % tailleX,
					((posY - 1) + tailleY) % tailleY));
			res.add(new Coordonnees(((posX - 1) + tailleX) % tailleX, posY));
			res.add(new Coordonnees(((posX - 1) + tailleX) % tailleX,
					(posY + 1) % tailleY));
			res.add(new Coordonnees(posX, (posY + 1) % tailleY));
			res.add(new Coordonnees((posX + 1) % tailleX, (posY + 1) % tailleY));
			res.add(new Coordonnees((posX + 1) % tailleX, posY));
			res.add(new Coordonnees((posX + 1) % tailleX,
					((posY - 1) + tailleY) % tailleY));
			res.add(new Coordonnees(posX, ((posY - 1) + tailleY) % tailleY));

			return res;
		}

		return res;
	}

	public Case getCaseFromCoordonnees(final Coordonnees coordonnees) {
		return getCaseFromCoordonnees(coordonnees.getX(), coordonnees.getY());
	}

	public Case getCaseFromCoordonnees(final int x, final int y) {
		return grille[x][y];
	}

}
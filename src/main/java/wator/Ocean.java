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

	private Case[][] grille;

	private int tailleX;

	private int tailleY;

	private boolean isTore;

	private List<Coordonnees> coordonneesDeLaGrille = new ArrayList<Coordonnees>();

	/**
	 * Constructor
	 * 
	 * @param tailleX
	 * @param tailleY
	 * @param initialNumberOfTuna
	 * @param initialNumberOfShark
	 * @param isTore
	 */
	public Ocean(final int tailleX, final int tailleY,
			final int initialNumberOfShark, final int initialNumberOfTuna,
			final boolean isTore) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.isTore = isTore;
		grille = new Case[tailleX][tailleY];

		remplirGrilleAvecCasesVides();
		initGrilleWithSharksAndTunas(initialNumberOfShark, initialNumberOfTuna);
	}

	/**
	 * Constructor
	 * 
	 * @param taille
	 * @param isTore
	 */
	public Ocean(final int taille, final int initialNumberOfShark,
			final int initialNumberOfTuna, final boolean isTore) {
		this(taille, taille, initialNumberOfShark, initialNumberOfTuna, isTore);
	}

	/**
	 * Constructor
	 * 
	 * @param taille
	 */
	public Ocean(final int taille, final int initialNumberOfShark,
			final int initialNumberOfTuna) {
		this(taille, taille, initialNumberOfShark, initialNumberOfTuna, true);
	}

	/**
	 * Initialise la grille
	 */
	private void remplirGrilleAvecCasesVides() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				final Coordonnees coordonnees = new Coordonnees(tailleX,
						tailleY);
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
	 */
	private void initGrilleWithSharksAndTunas(final int initialNumberOfShark,
			final int initialNumberOfTuna) {

		Collections.shuffle(coordonneesDeLaGrille);
		int i = 0;
		for (; i < initialNumberOfShark; i++) {
			final Coordonnees coordonnees = coordonneesDeLaGrille.get(i);
			grille[coordonnees.getX()][coordonnees.getY()].setAgent(new Shark(
					coordonnees, this));
		}

		final int j = i;
		for (; i < initialNumberOfTuna + j; i++) {
			final Coordonnees coordonnees = coordonneesDeLaGrille.get(i);
			grille[coordonnees.getX()][coordonnees.getY()].setAgent(new Tuna(
					coordonnees, this));
		}
	}

	@Override
	public List<Coordonnees> getCoordonneesVoisines(
			final Coordonnees coordonnees) {

		// TODO RAL : todo WHAT ?

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

		// TODO RAL : Ajouter le code pour le cas d'une grille non torique.

		return res;
	}

}
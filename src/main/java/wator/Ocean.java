package wator;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import core.Case;
import core.Coordonnees;
import core.Environnement;
import core.Systeme;

@Data
public class Ocean implements Environnement {

	protected Systeme systeme;

	protected Case[][] grille;

	protected int tailleX;

	protected int tailleY;

	protected boolean isTore;

	/**
	 * Constructor
	 * 
	 * @param tailleX
	 * @param tailleY
	 * @param isTore
	 */
	public Ocean(final int tailleX, final int tailleY, final boolean isTore) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.isTore = isTore;
		grille = new Case[tailleX][tailleY];

		remplirGrilleAvecCasesVides();
	}

	/**
	 * Constructor
	 * 
	 * @param taille
	 * @param isTore
	 */
	public Ocean(final int taille, final boolean isTore) {
		this(taille, taille, isTore);
	}

	/**
	 * Constructor
	 * 
	 * @param taille
	 */
	public Ocean(final int taille) {
		this(taille, taille, true);
	}

	/**
	 * Initialise la grille
	 */
	private void remplirGrilleAvecCasesVides() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				grille[x][y] = new Case(new Coordonnees(tailleX, tailleY));
			}
		}
	}

	@Override
	public List<Coordonnees> getCoordonneesVoisines(final Coordonnees coordonnees) {

		// TODO RAL : todo WHAT ?

		final List<Coordonnees> res = new ArrayList<Coordonnees>();
		final int posX = coordonnees.getPosX();
		final int posY = coordonnees.getPosY();

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
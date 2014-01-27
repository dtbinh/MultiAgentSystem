package schelling;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.Case;
import core.Coordonnees;
import core.Environnement;
import core.Systeme;

public class Territoire implements Environnement {

	protected Systeme systeme;
	protected boolean isTore;
	protected int tailleSup;
	protected final int tailleMin;
	protected Case[][] grille;
	private final List<Coordonnees> coordonneesDeLaGrille = new ArrayList<Coordonnees>();

	public Territoire(final int taille, final int nombreVert,
			final int nombreRouge, final int tauxSatisfaction) {
		isTore = true;
		tailleSup = taille;
		tailleMin = 0;
		grille = new Case[taille][taille];
		remplirGrilleAvecCasesVides();
		setPopulation(nombreVert, nombreRouge, tauxSatisfaction);
		calculTaux();
	}

	private void calculTaux() {
		for (final Coordonnees coord : coordonneesDeLaGrille) {
			final Case current = getCaseFromCoordonnees(coord);
			if (current.isNotVide()) {
				((Human) current.getAgent()).calculActualTaux();
			}
		}
	}

	public Territoire(final int taille, final int remplissageTaux,
			final int tauxSatisfaction) {
		this(taille, (taille * taille) * remplissageTaux / 200,
				(taille * taille) * remplissageTaux / 200, tauxSatisfaction);
	}

	private void setPopulation(final int nombreVert, final int nombreRouge,
			final int tauxSatisfaction) {
		Collections.shuffle(coordonneesDeLaGrille);
		int p;
		for (p = 0; p < nombreRouge; p++) {
			final Coordonnees currentCoord = coordonneesDeLaGrille.get(p);
			grille[currentCoord.getX()][currentCoord.getY()].setAgent(new Red(
					currentCoord, tauxSatisfaction, this));
		}

		for (p = 0; p < nombreVert; p++) {
			final Coordonnees currentCoord = coordonneesDeLaGrille.get(p
					+ nombreRouge);
			grille[currentCoord.getX()][currentCoord.getY()]
					.setAgent(new Green(currentCoord, tauxSatisfaction, this));
		}
	}

	/**
	 * Initialise la grille
	 */
	private void remplirGrilleAvecCasesVides() {
		for (int x = 0; x < tailleSup; x++) {
			for (int y = 0; y < tailleSup; y++) {
				final Coordonnees coordonnees = new Coordonnees(x, y);
				grille[x][y] = new Case(coordonnees, Color.GRAY);
				coordonneesDeLaGrille.add(coordonnees);
			}
		}
	}

	@Override
	public void setSysteme(final Systeme systeme) {
		this.systeme = systeme;
	}

	@Override
	public List<Coordonnees> getCoordonneesVoisines(
			final Coordonnees coordonnees) {
		final List<Coordonnees> res = new ArrayList<Coordonnees>();
		final int posX = coordonnees.getX();
		final int posY = coordonnees.getY();

		if (isTore) {
			res.add(new Coordonnees(((posX - 1) + tailleSup) % tailleSup,
					((posY - 1) + tailleSup) % tailleSup));
			res.add(new Coordonnees(((posX - 1) + tailleSup) % tailleSup, posY));
			res.add(new Coordonnees(((posX - 1) + tailleSup) % tailleSup,
					(posY + 1) % tailleSup));
			res.add(new Coordonnees(posX, (posY + 1) % tailleSup));
			res.add(new Coordonnees((posX + 1) % tailleSup, (posY + 1)
					% tailleSup));
			res.add(new Coordonnees((posX + 1) % tailleSup, posY));
			res.add(new Coordonnees((posX + 1) % tailleSup,
					((posY - 1) + tailleSup) % tailleSup));
			res.add(new Coordonnees(posX, ((posY - 1) + tailleSup) % tailleSup));

			return res;
		}

		return res;
	}

	@Override
	public Case getCaseFromCoordonnees(final Coordonnees coordonnees) {
		return getCaseFromCoordonnees(coordonnees.getX(), coordonnees.getY());
	}

	@Override
	public Case getCaseFromCoordonnees(final int x, final int y) {
		return grille[x][y];
	}

	@Override
	public int getTailleSup() {
		return tailleSup;
	}

	@Override
	public int getTailleInf() {
		return tailleMin;
	}

	@Override
	public Case[][] getGrille() {
		return grille;
	}

	@Override
	public Systeme getSysteme() {
		return systeme;
	}

	@Override
	public List<Coordonnees> getCoordonneesDeLaGrille() {
		return coordonneesDeLaGrille;
	}

}

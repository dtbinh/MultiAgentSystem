package particles;

import java.util.ArrayList;
import java.util.List;

import core.Case;
import core.Coordonnees;
import core.Environnement;
import core.Systeme;

public class Plateau implements Environnement {

	protected int tailleX;
	protected int tailleY;
	protected Case[][] grille;
	protected Systeme systeme;
	private final List<Coordonnees> coordonneesDeLaGrille = new ArrayList<Coordonnees>();

	public Plateau(int taille) {
		tailleX = taille;
		tailleY = taille;
		grille = new Case[tailleX][tailleY];

		initGrille();

	}

	private void initGrille() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				Coordonnees coordonnees = new Coordonnees(x, y);
				grille[x][y] = new Case(coordonnees);
				coordonneesDeLaGrille.add(coordonnees);
			}
		}
	}

	@Override
	public int getTailleX() {
		return tailleX;
	}

	@Override
	public int getTailleY() {
		return tailleY;
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
	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	@Override
	public List<Coordonnees> getCoordonneesVoisines(Coordonnees coordonnees) {
		return null;
	}

	@Override
	public List<Coordonnees> getCoordonneesDeLaGrille() {
		return coordonneesDeLaGrille;
	}

	@Override
	public Case getCaseFromCoordonnees(final Coordonnees coordonnees) {
		return getCaseFromCoordonnees(coordonnees.getX(), coordonnees.getY());
	}

	@Override
	public Case getCaseFromCoordonnees(final int x, final int y) {
		return grille[x][y];
	}
}

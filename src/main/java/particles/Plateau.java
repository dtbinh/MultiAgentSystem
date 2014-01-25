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

	public Plateau(final int taille) {
		tailleX = taille;
		tailleY = taille;
		grille = new Case[tailleX][tailleY];
		initGrille();
		setWall();

	}

	private void initGrille() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				final Coordonnees coordonnees = new Coordonnees(x, y);
				grille[x][y] = new Case(coordonnees);
				coordonneesDeLaGrille.add(coordonnees);
			}
		}
	}

	private void setWall() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				final Coordonnees coordonnees = new Coordonnees(x, y);
				System.out.println(coordonnees);
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
	public void setSysteme(final Systeme systeme) {
		this.systeme = systeme;
	}

	@Override
	public List<Coordonnees> getCoordonneesVoisines(
			final Coordonnees coordonnees) {
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

package particles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import core.Case;
import core.Coordonnees;
import core.Environnement;
import core.Systeme;

@Data
public class Plateau implements Environnement {

	protected int tailleSup;
	protected int tailleInf;
	protected Case[][] grille;
	protected Systeme systeme;
	private final List<Coordonnees> coordonneesDeLaGrille = new ArrayList<Coordonnees>();

	public Plateau(final int taille, final int nombreBille, boolean b) {
		tailleSup = taille;
		tailleInf = 1;
		// +2 pour ajouter les murs
		grille = new Case[taille + 2][taille + 2];
		initGrille(b);
		setWall();
		setBille(nombreBille);
	}

	private void setBille(final int nombreBille) {
		Collections.shuffle(coordonneesDeLaGrille);
		for (int i = 0; i < nombreBille; i++) {
			final Coordonnees coordonnees = coordonneesDeLaGrille.get(i);
			grille[coordonnees.getX()][coordonnees.getY()].setAgent(new Bille(
					coordonnees, this));
		}
	}

	private void initGrille(boolean b) {
		for (int x = 1; x < tailleSup + 1; x++) {
			for (int y = 1; y < tailleSup + 1; y++) {
				final Coordonnees coordonnees = new Coordonnees(x, y);
				grille[x][y] = new Case(coordonnees, b);
				coordonneesDeLaGrille.add(coordonnees);
			}
		}
	}

	private void setWall() {
		for (int i = 0; i < tailleSup + 2; i++) {

			final Coordonnees c1 = new Coordonnees(0, i);
			grille[0][i] = new Case(c1);
			grille[0][i].setAgent(new Wall(c1, this));

			final Coordonnees c2 = new Coordonnees(i, 0);
			grille[i][0] = new Case(c2);
			grille[i][0].setAgent(new Wall(c2, this));

			final Coordonnees c3 = new Coordonnees(tailleSup + 1, i);
			grille[tailleSup + 1][i] = new Case(c3);
			grille[tailleSup + 1][i].setAgent(new Wall(c3, this));

			final Coordonnees c4 = new Coordonnees(i, tailleSup + 1);
			grille[i][tailleSup + 1] = new Case(c4);
			grille[i][tailleSup + 1].setAgent(new Wall(c4, this));
		}
	}

	@Override
	public List<Coordonnees> getCoordonneesVoisines(
			final Coordonnees coordonnees) {
		return null;
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

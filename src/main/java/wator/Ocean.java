package wator;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Coordonnees;
import core.Environnement;
import core.Systeme;

public class Ocean implements Environnement {

	protected Systeme systeme;
	protected boolean[][] grille;
	protected int tailleX;
	protected int tailleY;
	protected boolean isTore;

	public Ocean(int tailleX, int tailleY, boolean isTore) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.isTore = isTore;
		grille = new boolean[tailleX][tailleY];

		remplirGrilleAvecCasesVides();
	}

	public Ocean(int taille, boolean isTore) {
		this(taille, taille, isTore);
	}

	public Ocean(int taille) {
		this(taille, taille, true);
	}

	private void remplirGrilleAvecCasesVides() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				grille[x][y] = false;
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
	public List<Coordonnees> getVoisins(Coordonnees coordonnees) {
		// TODO
		List<Coordonnees> res = new ArrayList<Coordonnees>();
		int posX = coordonnees.getPosX();
		int posY = coordonnees.getPosY();
		// boolean xp = (posX + 1) < tailleX;
		// boolean xm = (posX - 1) >= 0;
		// boolean ym = (posY - 1) >= 0;
		// boolean yp = (posY + 1) < tailleY;

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
		}// else {
			// if (xm && ym) {
			// res.add(grille[posX - 1][posY - 1]);
			// }
			// if (xm) {
			// res.add(grille[posX - 1][posY]);
			// }
			// if (xm && yp) {
			// res.add(grille[posX - 1][posY + 1]);
			// }
			// if (yp) {
			// res.add(grille[posX][posY + 1]);
			// }
			// if (xp && yp) {
			// res.add(grille[posX + 1][posY + 1]);
			// }
			// if (xp) {
			// res.add(grille[posX + 1][posY]);
			// }
			// if (xp && ym) {
			// res.add(grille[posX + 1][posY - 1]);
			// }
			// if (ym) {
			// res.add(grille[posX][posY - 1]);
			// }
		return res;
	}

	@Override
	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasAgent(Coordonnees coordonnees) {
		return grille[coordonnees.getPosX()][coordonnees.getPosY()];
	}

	@Override
	public void move(Agent agent, Coordonnees to) {
		setPositionAgent(agent.getCoordonnees(), false);
		setPositionAgent(to, true);
		agent.setCoordonnees(to);

	}

	@Override
	public void setPositionAgent(Coordonnees aCoordonnees, boolean activated) {
		grille[aCoordonnees.getPosX()][aCoordonnees.getPosY()] = activated;
	}

	@Override
	public boolean[][] getGrille() {
		return grille;
	}

	@Override
	public Systeme getSysteme() {
		return systeme;
	}

}
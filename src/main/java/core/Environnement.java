package core;

import java.util.ArrayList;
import java.util.List;

public class Environnement {

	protected Systeme systeme;
	protected Agent[][] grille;
	protected int tailleX;
	protected int tailleY;
	protected boolean isTore;

	public Environnement(int tailleX, int tailleY, boolean isTore) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.isTore = isTore;

		grille = new Agent[tailleX][tailleY];

		remplirGrilleAvecCasesVides();
	}

	private void remplirGrilleAvecCasesVides() {
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				grille[x][y] = new Vide(x, y, this);
			}
		}
	}

	public void move(Agent from, Agent to) {

		grille[to.posX][to.posY] = from;
		grille[from.posX][from.posY] = new Vide(from.posX, from.posY, this);
		from.posX = to.posX;
		from.posY = to.posY;
	}

	public List<Agent> getVoisins(int posX, int posY) {
		// TODO
		List<Agent> res = new ArrayList<Agent>();
		boolean xp = (posX + 1) < tailleX;
		boolean xm = (posX - 1) >= 0;
		boolean ym = (posY - 1) >= 0;
		boolean yp = (posY + 1) < tailleY;

		if (isTore) {
			res.add(grille[((posX - 1) + tailleX) % tailleX][((posY - 1) + tailleY)
					% tailleY]);
			res.add(grille[((posX - 1) + tailleX) % tailleX][posY]);
			res.add(grille[((posX - 1) + tailleX) % tailleX][(posY + 1)
					% tailleY]);
			res.add(grille[posX][(posY + 1) % tailleY]);
			res.add(grille[(posX + 1) % tailleX][(posY + 1) % tailleY]);
			res.add(grille[(posX + 1) % tailleX][posY]);
			res.add(grille[(posX + 1) % tailleX][((posY - 1) + tailleY)
					% tailleY]);
			res.add(grille[posX][((posY - 1) + tailleY) % tailleY]);

			return res;
		} else {
			if (xm && ym) {
				res.add(grille[posX - 1][posY - 1]);
			}
			if (xm) {
				res.add(grille[posX - 1][posY]);
			}
			if (xm && yp) {
				res.add(grille[posX - 1][posY + 1]);
			}
			if (yp) {
				res.add(grille[posX][posY + 1]);
			}
			if (xp && yp) {
				res.add(grille[posX + 1][posY + 1]);
			}
			if (xp) {
				res.add(grille[posX + 1][posY]);
			}
			if (xp && ym) {
				res.add(grille[posX + 1][posY - 1]);
			}
			if (ym) {
				res.add(grille[posX][posY - 1]);
			}
			return res;
		}
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public Agent getAgent(int x, int y) {
		return grille[x][y];
	}

}

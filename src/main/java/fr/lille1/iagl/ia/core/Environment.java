package fr.lille1.iagl.ia.core;

import java.util.List;

public class Environment {

	private boolean isTore;
	private int tailleX;
	private int tailleY;
	private Agent[][] grille;

	public Environment() {
		this.isTore = false;
	}

	public void init(int x, int y) {
		this.tailleX = x;
		this.tailleY = y;
	}

	public List<?> voisins(int x, int y) {
		return null;
	}

	public void dessine() {

	}

}

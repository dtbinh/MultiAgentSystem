package core;

import java.awt.Color;

import lombok.Data;

@Data
public abstract class Agent {

	protected Environnement environnement;

	protected Coordonnees coordonnees;

	protected boolean isDead;

	protected int age = 0;

	protected Color color;

	/**
	 * Construtor
	 * 
	 * @param coordonnees
	 * @param environnement
	 * @param color
	 */
	public Agent(final Coordonnees coordonnees,
			final Environnement environnement, final Color color) {
		this.environnement = environnement;
		this.coordonnees = coordonnees;
		this.color = color;
		isDead = false;
	}

	/**
	 * Méthode d'action
	 */
	public abstract void action();

}
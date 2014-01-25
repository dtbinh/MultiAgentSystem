package core;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public abstract class Agent {

	protected Environnement environnement;

	protected Coordonnees coordonnees;

	protected boolean isDead;

	protected int age = 0;

	protected Color color;

	protected boolean aDejaJoue = false;

	protected JComponent monAffichage;

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
		setAffichage();
	}

	private void setAffichage() {
		monAffichage = new JLabel();
		monAffichage.setBackground(color);
		monAffichage.setOpaque(true);
		monAffichage.setBorder(new BevelBorder(BevelBorder.LOWERED));
	}

	/**
	 * Méthode anstraite d'action
	 */
	public abstract void action();

	/**
	 * Vide la case courante de l'agent qu'elle contient.
	 */
	protected void emptyCurrentCase() {
		getCurrentCase().setAgent(null);
	}

	/**
	 * @return
	 */
	protected Case getCurrentCase() {
		return environnement.getCaseFromCoordonnees(coordonnees);
	}

	protected void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public void setADejaJoue(boolean b) {
		aDejaJoue = b;

	}

	public JComponent print() {
		return monAffichage;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}
}
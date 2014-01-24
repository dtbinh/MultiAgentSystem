package core;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public abstract class Agent {
	protected Environnement environnement;
	protected JLabel print;
	protected Coordonnees coordonnees;
	protected Systeme systeme;

	protected boolean isDead;
	protected int age = 0;
	protected Color color;

	private int monID;
	private static int ID = 0;

	public Agent(Coordonnees coordonnees, Environnement environnement,
			Color color) {
		this.coordonnees = coordonnees;
		this.color = color;
		systeme = environnement.getSysteme();
		this.environnement = environnement;
		isDead = false;
		setPrint();
		monID = ++ID;
	}

	protected void setPrint() {
		print = new JLabel();
		print.setBackground(color);
		print.setOpaque(true);
		print.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public abstract void action();

	public void setDead(boolean b) {
		isDead = b;
	}

	public int getPosX() {
		return coordonnees.getPosX();
	}

	public int getPosY() {
		return coordonnees.getPosY();
	}

	public Component print() {
		return print;
	}

	protected void moveTo(Coordonnees toMove) {
		environnement.move(this, toMove);
	}

	protected void die() {
		System.out.println(this + " je meurs");
		setDead(true);
		systeme.removeAgent(this);
	}

	protected void kill(Agent agent) {
		System.out.println(this + " m' a tue " + agent);
		agent.die();
	}

	protected void reproduce(Agent newAgent) {
		systeme.newAgent(newAgent);
	}

	protected boolean canMove(Coordonnees voisin) {
		return !environnement.hasAgent(voisin);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + monID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Agent other = (Agent) obj;
		if (monID != other.monID) {
			return false;
		}
		return true;
	}

}

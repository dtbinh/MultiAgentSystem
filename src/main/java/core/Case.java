package core;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class Case {

	private Coordonnees coordonnees;

	private Agent agent;

	private JComponent defaultAffichage;

	/**
	 * Constructor
	 * 
	 * @param coordonnees
	 */
	public Case(final Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
		setComponent();
	}

	private void setComponent() {
		defaultAffichage = new JLabel();
		defaultAffichage.setOpaque(true);
		defaultAffichage.setBackground(new Color(0, 105, 148));
		defaultAffichage.setBorder(new BevelBorder(BevelBorder.LOWERED));
	}

	/**
	 * True if the case doesn't contain an agent.
	 * 
	 * @return
	 */
	public boolean isVide() {
		return agent == null;
	}

	/**
	 * True if the case contains an agent.
	 * 
	 * @return
	 */
	public boolean isNotVide() {
		return !isVide();
	}

	public JComponent printCase() {
		return isVide() ? defaultAffichage : agent.print();
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;

	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

}

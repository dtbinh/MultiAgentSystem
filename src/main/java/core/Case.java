package core;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = { "agent", "defaultAffichage" })
public class Case {

	private final Coordonnees coordonnees;

	private Agent agent;

	private JComponent defaultAffichage;

	/**
	 * Constructor
	 * 
	 * @param coordonnees
	 */
	public Case(final Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
		setComponent(Color.WHITE);
	}

	public Case(final Coordonnees coordonnees, final Color color) {
		this.coordonnees = coordonnees;
		setComponent(color);
	}

	private void setComponent(final Color color) {
		// defaultAffichage = new JLabel();
		// defaultAffichage = new Circle(color, 5, 3);
		defaultAffichage = new JPanel();
		defaultAffichage.setOpaque(true);
		defaultAffichage.setBackground(color);
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

	/**
	 * useless
	 * 
	 * @param agent
	 */
	public void setAgent(final Agent agent) {
		this.agent = agent;
	}

}

package core;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = { "environnement" })
public abstract class Agent {

	protected Environnement environnement;

	protected Coordonnees coordonnees;

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
		setAffichage();
	}

	protected void setAffichage() {
		// monAffichage = new JLabel();
		// monAffichage = new Circle(color, 5, 3);
		monAffichage = new JPanel();
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

	public JComponent print() {
		return monAffichage;
	}

}
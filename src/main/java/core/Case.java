package core;

import lombok.Data;

@Data
public class Case {

	Coordonnees coordonnees;

	Agent agent;

	/**
	 * Constructor
	 * 
	 * @param coordonnees
	 */
	public Case(final Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	/**
	 * True if the case doesn't contain an agent.
	 * 
	 * @return
	 */
	public boolean isVide() {
		return agent == null;
	}

}

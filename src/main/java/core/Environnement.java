package core;

import java.util.List;

public interface Environnement {
	void display();

	boolean hasAgent(Coordonnees coordonnees);

	int getTailleX();

	int getTailleY();

	void move(Agent agent, Coordonnees to);

	void setPositionAgent(Coordonnees aCoordonnees, boolean activated);

	boolean[][] getGrille();

	Systeme getSysteme();

	void setSysteme(Systeme systeme);

	List<Coordonnees> getVoisins(Coordonnees coordonnees);
}

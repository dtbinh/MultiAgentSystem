package core;

import java.util.List;

public interface Environnement {

	int getTailleX();

	int getTailleY();

	Case[][] getGrille();

	Systeme getSysteme();

	void setSysteme(final Systeme systeme);

	List<Coordonnees> getCoordonneesVoisines(final Coordonnees coordonnees);

	List<Coordonnees> getCoordonneesDeLaGrille();

}

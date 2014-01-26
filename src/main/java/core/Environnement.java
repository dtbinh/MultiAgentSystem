package core;

import java.util.List;

public interface Environnement {

	int getTailleSup();

	int getTailleInf();

	Case[][] getGrille();

	Systeme getSysteme();

	void setSysteme(final Systeme systeme);

	List<Coordonnees> getCoordonneesVoisines(final Coordonnees coordonnees);

	List<Coordonnees> getCoordonneesDeLaGrille();

	Case getCaseFromCoordonnees(final Coordonnees coordonnees);

	Case getCaseFromCoordonnees(final int x, final int y);

}

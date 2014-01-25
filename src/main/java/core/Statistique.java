package core;

public interface Statistique {

	void printLineToFile();

	void setFile(String fileName);

	void setEnvironnement(Environnement environnement);

	void update();

}

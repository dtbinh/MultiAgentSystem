package schelling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import core.Case;
import core.Coordonnees;
import core.Environnement;
import core.Statistique;

public class SchellingStats implements Statistique {

	private static Statistique INSTANCE = new SchellingStats();
	private Environnement environnement;
	private int moyenneSatifaction;
	private File file;
	private String line;

	private SchellingStats() {
		moyenneSatifaction = 0;
	}

	public static Statistique getInstance() {
		return INSTANCE;
	}

	@Override
	public void printLineToFile() {
		try {
			line = moyenneSatifaction + "\n";
			FileUtils.write(file, line, true);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		int population = 0;
		int sommeTaux = 0;
		for (final Coordonnees coordonnnes : environnement
				.getCoordonneesDeLaGrille()) {
			final Case current = environnement
					.getCaseFromCoordonnees(coordonnnes);
			if (current.isNotVide()) {
				population++;
				sommeTaux += ((Human) current.getAgent()).getTaux();
			}
		}
		moyenneSatifaction = (sommeTaux / population);
	}

	@Override
	public void printEntete() {
		try {
			FileUtils.write(file, line + "\n", true);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFile(final String fileName) {
		try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		file = new File(fileName);
	}

	@Override
	public void setEnvironnement(final Environnement environnement) {
		this.environnement = environnement;
	}

}

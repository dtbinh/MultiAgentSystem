package wator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import lombok.Data;

import org.apache.commons.io.FileUtils;

import core.Agent;
import core.Environnement;
import core.Statistique;

@Data
public class WatorStat implements Statistique {

	private static Statistique INSTANCE = new WatorStat();
	private Environnement environnement;
	private int nbShark;
	private int nbTuna;
	private Map<Integer, Integer> ageShark;
	private Map<Integer, Integer> ageTuna;
	private File file;
	private String line;

	private WatorStat() {
		nbShark = 0;
		nbTuna = 0;
	}

	public static Statistique getInstance() {
		return INSTANCE;
	}

	@Override
	public void printLineToFile() {
		try {
			line = nbShark + ";" + nbTuna;
			FileUtils.write(file, line + "\n", true);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		nbTuna = 0;
		nbShark = 0;
		final int max = environnement.getTailleSup();
		final int min = environnement.getTailleInf();
		for (int x = min; x < max; x++) {
			for (int y = min; y < max; y++) {
				final Agent agent = environnement.getCaseFromCoordonnees(x, y)
						.getAgent();
				if (agent instanceof Tuna) {
					nbTuna++;
					continue;
				}
				if (agent instanceof Shark) {
					nbShark++;
					continue;
				}
			}
		}
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

}

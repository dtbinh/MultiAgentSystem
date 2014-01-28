package wator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	private File fileNb;
	private File fileAge;
	private String line;

	private WatorStat() {
		nbShark = 0;
		nbTuna = 0;
		ageShark = new HashMap<Integer, Integer>();
		ageTuna = new HashMap<Integer, Integer>();
	}

	public static Statistique getInstance() {
		return INSTANCE;
	}

	@Override
	public void printLineToFile() {
		try {
			line = nbShark + ";" + nbTuna;
			FileUtils.write(fileNb, line + "\n", true);
			int max = 0;
			for (final Entry<Integer, Integer> entry : ageShark.entrySet()) {
				if (entry.getKey() > max) {
					max = entry.getKey();
				}
			}
			for (final Entry<Integer, Integer> entry : ageTuna.entrySet()) {
				if (entry.getKey() > max) {
					max = entry.getKey();
				}
			}
			line = "";
			for (int i = 1; i <= max; i++) {
				line += i + ";-" + ageShark.get(new Integer(i)) + ";"
						+ ageTuna.get(new Integer(i)) + "\n";
				FileUtils.write(fileAge, line + "\n", true);
			}
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
					Integer nb = ageTuna.get(new Integer(agent.getAge()));
					if (nb == null) {
						nb = 0;
					} else {
						nb++;
					}
					ageTuna.put(agent.getAge(), nb);
					continue;
				}
				if (agent instanceof Shark) {
					nbShark++;
					Integer nb = ageShark.get(new Integer(agent.getAge()));
					if (nb == null) {
						nb = 0;
					} else {
						nb++;
					}
					ageShark.put(agent.getAge(), nb);
					continue;
				}
			}
		}
	}

	@Override
	public void printEntete() {
		try {
			FileUtils.write(fileNb, line + "\n", true);
			FileUtils.write(fileAge, "age;nbRequins;nbThons\n", true);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFile(final String fileName) {
		try {
			Files.deleteIfExists(Paths.get(fileName + "nb.csv"));
			Files.deleteIfExists(Paths.get(fileName + "age.csv"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		fileNb = new File(fileName + "nb.csv");
		fileAge = new File(fileName + "age.csv");
	}

}

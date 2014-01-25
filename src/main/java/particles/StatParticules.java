package particles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.Data;
import core.Environnement;
import core.Statistique;

@Data
public class StatParticules implements Statistique {

	private static Statistique INSTANCE = new StatParticules();
	private Environnement environnement;
	private File file;
	private String line;

	private StatParticules() {
	}

	public static Statistique getInstance() {
		return INSTANCE;
	}

	@Override
	public void printLineToFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFile(String fileName) {
		try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		file = new File(fileName);
	}

	@Override
	public void update() {
	}

	@Override
	public void printEntete() {
		// TODO Auto-generated method stub

	}

}

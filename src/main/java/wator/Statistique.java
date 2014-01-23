package wator;

public class Statistique {

	protected int nbShark;
	protected int nbTuna;

	private Statistique() {
		nbShark = 0;
		nbTuna = 0;
	}

	public static Statistique INSTANCE = new Statistique();

	public static Statistique getInstance() {
		return INSTANCE;
	}

	public void addShark(int n) {
		nbShark += n;
	}

	public void addTuna(int n) {
		nbTuna += n;
	}

	public int getShark() {
		return nbShark;
	}

	public int getTuna() {
		return nbTuna;
	}

	@Override
	public String toString() {
		return nbShark + ";" + nbTuna + "\n";
	}

	public String getEntete() {
		return "Requins;Thons\n";
	}
}

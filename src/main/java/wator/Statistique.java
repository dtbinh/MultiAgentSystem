package wator;

public class Statistique {

	protected int nbShark;
	protected int nbTuna;
	protected int total;
	protected String ligne;

	private Statistique() {
		nbShark = 0;
		nbTuna = 0;
		total = 0;
		ligne = "0;0;0;0";
	}

	private static Statistique INSTANCE = new Statistique();

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

	public void setTotal(int to) {
		total = to;
	}

	@Override
	public String toString() {
		return nbShark + ";" + nbTuna + ";" + total + "\n";
	}

	public void setStats(String stats) {
		ligne = stats;
	}

	public String stats() {
		return ligne + "\n";
	}

	public String getEntete() {
		return "Autres;Thons;Requins;Total\n";
	}
}

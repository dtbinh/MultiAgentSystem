package core;

public class Coordonnees {
	protected int posX;
	protected int posY;

	public Coordonnees(int x, int y) {
		posX = x;
		posY = y;
	}

	public void setPosX(int x) {
		posX = x;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosY(int y) {
		posY = y;
	}

	public int getPosY() {
		return posY;
	}
}

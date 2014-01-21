package core;

import java.awt.Color;

public class Vide extends Agent {

	public Vide(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement, 0, 0, 0, Color.WHITE);
	}

	@Override
	public boolean isVide() {
		return true;

	}

	@Override
	public void action() {
	}

	@Override
	protected void seReproduire(Agent a) {

	}
}

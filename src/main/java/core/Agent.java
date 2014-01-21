package core;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public abstract class Agent {
	protected Environnement environnement;
	protected JLabel print;
	protected int posX, posY;
	protected Systeme systeme;

	protected final int TIME_TO_EAT;
	protected final int TIME_TO_REPRODUCE;
	protected final int TIME_TO_DIE;

	protected boolean isDead;
	protected int age = 0;
	protected int leftTimeToEat;
	protected int leftTimeToReproduce;
	protected int leftTimeToDie;
	protected String image = "";
	protected Color color;

	public Agent(int posX, int posY, Environnement environnement, int die,
			int reproduce, int eat, Color color) {
		this.posX = posX;
		this.posY = posY;
		TIME_TO_DIE = die;
		TIME_TO_EAT = eat;
		this.color = color;
		TIME_TO_REPRODUCE = reproduce;
		systeme = environnement.systeme;
		this.environnement = environnement;
		leftTimeToDie = TIME_TO_DIE;
		leftTimeToReproduce = TIME_TO_REPRODUCE;
		leftTimeToEat = TIME_TO_EAT;
		isDead = false;
		setPrint(image);
	}

	protected void setPrint(String image) {
		print = new JLabel();
		print.setBackground(color);
		print.setOpaque(true);
		print.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public abstract void action();

	public Component dessineMoi() {
		return print;
	}

	public boolean isVide() {
		return false;
	}

	protected abstract void reproduce(Agent a);

	public void setDead(boolean b) {
		isDead = b;
	}

}

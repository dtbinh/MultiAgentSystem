package fr.lille1.iagl.ia.core;

import java.awt.Color;
import java.awt.Image;

public abstract class Agent {

	private int posX;
	private int posY;
	private Image icone;
	private Color color;

	public abstract void action();

	public abstract void dessine();
}

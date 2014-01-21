package core;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vue extends JFrame {

	protected Environnement environnement;
	// private JPanel gridPanel;
	private JPanel grid;
	private int sizeX;
	private int sizeY;

	// TODO mettre le jscrollpane
	public Vue(Environnement environnement, int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		setPreferredSize(new Dimension(sizeX, sizeY));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.environnement = environnement;
		grid = new JPanel(new GridLayout(environnement.tailleX,
				environnement.tailleY));
		getContentPane().add(grid);
		pack();
	}

	public void update() {
		grid.removeAll();
		for (int x = 0; x < environnement.tailleX; x++) {
			for (int y = 0; y < environnement.tailleY; y++) {
				grid.add(environnement.grille[x][y].dessineMoi());
			}
		}
		grid.validate();
		grid.repaint();
	}
}

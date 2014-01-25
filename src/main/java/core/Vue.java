package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vue extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Environnement environnement;
	private JPanel grid;
	private int sizeX;
	private int sizeY;

	// TODO mettre le jscrollpane
	public Vue(Environnement environnement, int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.environnement = environnement;
		initFrame();
	}

	private void initFrame() {
		setPreferredSize(new Dimension(sizeX, sizeY));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid = new JPanel(new GridLayout(environnement.getTailleX(),
				environnement.getTailleY()));
		setLayout(new BorderLayout());
		getContentPane().add(grid, BorderLayout.CENTER);
		pack();
	}

	@Override
	public void update(Observable o, Object arg) {
		grid.removeAll();
		int tx = environnement.getTailleX();
		int ty = environnement.getTailleY();
		for (int x = 0; x < tx; x++) {
			for (int y = 0; y < ty; y++) {
				Case c = environnement.getCaseFromCoordonnees(x, y);
				grid.add(c.printCase());
			}
		}

		grid.validate();
		grid.repaint();
	}
}
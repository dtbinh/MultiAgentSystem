package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Vue extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Environnement environnement;
	private JPanel grid;
	private JScrollPane scrollPane;
	private final int sizeX;
	private final int sizeY;
	private final int cellSize;

	// TODO mettre le jscrollpane
	public Vue(final Environnement environnement, final int sizeX,
			final int sizeY, int cellSize) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.environnement = environnement;
		this.cellSize = cellSize;
		initFrame();

	}

	private void initFrame() {
		setPreferredSize(new Dimension(sizeX, sizeY));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollPane = new JScrollPane();

		initGridPane();
		setLayout(new BorderLayout());
		scrollPane.getViewport().add(grid);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		pack();
	}

	private void initGridPane() {
		GridLayout gbl = new GridLayout(environnement.getTailleX(),
				environnement.getTailleY());
		grid = new JPanel(gbl);
	}

	@Override
	public void update(final Observable o, final Object arg) {
		grid.removeAll();
		final int tx = environnement.getTailleX();
		final int ty = environnement.getTailleY();
		for (int x = 0; x < tx; x++) {
			for (int y = 0; y < ty; y++) {
				final Case c = environnement.getCaseFromCoordonnees(x, y);
				JComponent jc = c.printCase();
				jc.setPreferredSize(new Dimension(cellSize, cellSize));
				grid.add(jc);
			}
		}
		environnement.getSysteme().getStatistique().update();
		environnement.getSysteme().getStatistique().printLineToFile();
		grid.validate();
		grid.repaint();
	}
}
package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

	private final int zoom;

	// TODO mettre le jscrollpane
	public Vue(final Environnement environnement, final int sizeX,
			final int sizeY, final int cellSize) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.environnement = environnement;
		this.cellSize = cellSize;
		zoom = 2;
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
		final GridLayout gbl = new GridLayout(environnement.getTailleSup(),
				environnement.getTailleSup());
		grid = new JPanel(gbl);
	}

	@Override
	public void update(final Observable o, final Object arg) {
		grid.removeAll();
		final int max = environnement.getTailleSup();
		final int min = environnement.getTailleInf();

		for (int x = min; x < max + min; x++) {
			for (int y = min; y < max + min; y++) {
				final Case c = environnement.getCaseFromCoordonnees(x, y);
				final JComponent jc = c.printCase();

				jc.setPreferredSize(new Dimension(cellSize * zoom, cellSize
						* zoom));
				grid.add(jc);

			}
		}
		environnement.getSysteme().getStatistique().update();
		environnement.getSysteme().getStatistique().printLineToFile();
		grid.validate();
		grid.repaint();
	}
}
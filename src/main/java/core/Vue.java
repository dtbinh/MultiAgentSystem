package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

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

	// TODO mettre le jscrollpane
	public Vue(final Environnement environnement, final int sizeX,
			final int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.environnement = environnement;
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
		GridBagLayout gbl = new GridBagLayout();
		grid = new JPanel(gbl);
	}

	@Override
	public void update(final Observable o, final Object arg) {
		GridBagConstraints gbc = new GridBagConstraints();
		grid.removeAll();
		final int tx = environnement.getTailleX();
		final int ty = environnement.getTailleY();
		for (int x = 0; x < tx; x++) {
			for (int y = 0; y < ty; y++) {
				final Case c = environnement.getCaseFromCoordonnees(x, y);
				gbc.gridx = x;
				gbc.gridy = y;
				if (c.hasChanged()) {
					grid.add(c.printCase(), gbc);
				}
			}
		}
		environnement.getSysteme().getStats().update();
		environnement.getSysteme().getStats().printLineToFile();
		grid.validate();
		grid.repaint();
	}
}
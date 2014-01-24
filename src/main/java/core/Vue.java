package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import wator.Statistique;

public class Vue extends JFrame implements Observer {

	protected Environnement environnement;
	private JPanel grid;
	private int sizeX;
	private int sizeY;
	private JLabel agentNull;
	private BufferedWriter br;
	private JPanel paramPanel;

	// TODO mettre le jscrollpane
	public Vue(Environnement environnement, int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.environnement = environnement;
		initFrame();
		try {
			br = new BufferedWriter(new FileWriter("statistiqueWator.csv"));
			br.write(Statistique.getInstance().getEntete());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initFrame() {
		setPreferredSize(new Dimension(sizeX, sizeY));
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					br.close();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(0);
				}

			}
		});
		grid = new JPanel(new GridLayout(environnement.getTailleX(),
				environnement.getTailleY()));
		paramPanel = new Parametre();
		setLayout(new BorderLayout());
		getContentPane().add(grid, BorderLayout.CENTER);
		getContentPane().add(paramPanel, BorderLayout.EAST);
		pack();
	}

	private void setAgentNull(Color color) {
		agentNull = new JLabel();
		agentNull.setBackground(color);
		agentNull.setOpaque(true);
		agentNull.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
	}

	@Override
	public void update(Observable o, Object arg) {
		Systeme systeme = (Systeme) o;
		grid.removeAll();
		Statistique.getInstance().setTotal(systeme.getAgentsCount());
		boolean[][] grille = environnement.getGrille();
		int tx = environnement.getTailleX();
		int ty = environnement.getTailleY();
		for (int x = 0; x < tx; x++) {
			for (int y = 0; y < ty; y++) {
				if (grille[x][y]) {
					grid.add(systeme.getAgentByCoord(x, y).print());
				} else {
					setAgentNull(Color.WHITE);
					grid.add(agentNull);
				}
			}
		}
		grid.validate();
		grid.repaint();
		try {
			br.write(Statistique.getInstance().stats());
			br.flush();
			System.out.println(Statistique.getInstance().stats());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

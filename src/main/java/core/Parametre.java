package core;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Parametre extends JPanel {

	private JLabel shark;
	private JLabel tuna;
	private JTextField nbS;
	private JTextField nbT;

	public Parametre() {
		setLayout(new GridLayout(10, 10));
		shark = new JLabel("Requin");
		tuna = new JLabel("Thon");
		nbS = new JTextField(10);
		nbT = new JTextField(10);
		this.add(shark);
		this.add(shark);
		this.add(tuna);
		this.add(tuna);
	}
}

package game;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class battlePanel extends JFrame {

	private JPanel contentPane;
	private JPanel inhtPanel;

	/**
	 * Create the frame for the battle
	 * @return 
	 */
	public battlePanel(JPanel panel)
	{
		inhtPanel = panel;
	}
	/**
	 * initialize the JFrame
	 */
	public battlePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}

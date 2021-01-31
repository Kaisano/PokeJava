package game;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class battle extends JPanel {
	private static final ArrayList<ImageIcon> userPokemonImgs = null;
	private static String location = System.getProperty("user.dir");
	private JPanel mainDisplay, mainPane ;
	/**
	 * Create the panel and initialize components
	 */
	private Image image;
	public battle(JPanel display) {
		mainDisplay = display;
		
		setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0, 0, 450, 300);
		add(contentPane);
		contentPane.setLayout(null);
		
		mainPane = new JPanel();
		mainPane.setBounds(0, 0, 450, 300);
		contentPane.add(mainPane);
		//display.add(mainPane);
		mainPane.setLayout(null);
		
	}
	
	/**
	 * @param userPokemon what pokemon are currently in the game
	 */
	public void start(String userPokemon) {
		userPokemonImgs.add(new ImageIcon(location + "\\Images\\"));
		
		
		
		
		//mainPane.dispose();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
}

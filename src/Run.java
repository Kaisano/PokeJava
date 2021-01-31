import java.awt.EventQueue;
import java.io.IOException;

import LoginWindow.LoginSystem;

/**
 * @author Alex Lee
 * All assests (except a select few images) are ownership of Nintendo and Game Freak, creators of pokemon, and have only been used for personal use in this project. It will not be sold for profit anywhere.
 */
public class Run {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSystem window = new LoginSystem();
					window.getFrame().setVisible(true);
					window.getFrame().setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

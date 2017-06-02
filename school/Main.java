package school;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Justin Alderson
 * Main class
 * 
 */
public class Main {
	public static Color powderBlue = new Color(176,224,230);
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){     
				
				JFrame frame = new MainFrame("Alderson Software");
				frame.getContentPane().setBackground(Main.powderBlue);
				frame.setSize(800,550);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				}
		});
		
	}
}
	
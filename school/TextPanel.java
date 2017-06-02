package school;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * 
 * @author Justin Alderson
 * Class to represent the text box
 *
 */
public class TextPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Add components.
	protected static JTextArea information = new JTextArea(10 ,100);
	protected static JScrollPane scrollPane = new JScrollPane(information);

	public TextPanel() {
		setBorder(BorderFactory.createTitledBorder("Information will be displayed below"));
		// Set positions
		setLayout(new BorderLayout());
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		information.setLineWrap(true);//Sets the limits of the text area.
		add(scrollPane);
	}
	public void setText(String string) {
		information.setText(string);

	}

}

package school;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author Justin Alderson
 * Class to represent the details panel
 * 
 */
public  class DetailsPanel extends TextPanel implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	protected static String[] items = new String[] { "Mark off a lesson  ",
			"New student", "New M to M student", "Add to student info",
			"Display student info", "Add new lesson", "Add student to lesson",
			"Lesson/MtoM info", "Display school info", "Delete" };

	protected static JList<String> list = new JList<>(items);
	protected static int listSelect = -1;

	DetailsPanel() {
		// Construct details panel.
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		setBorder(BorderFactory.createTitledBorder("Options"));

		// Construct list.
		list.setFont(new Font("Verdana", Font.PLAIN, 14));
		list.addListSelectionListener(this);

		// Set layout within details panel.
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		add(list, gc);

	}

	public void valueChanged(ListSelectionEvent e) {
		int a = DetailsPanel.list.getSelectedIndex();// this gets the selected item.
		if (!e.getValueIsAdjusting()) {// This returns the last value as one click gets two values.
			DetailsPanel.listSelect = a;
			if(a == 0){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter lesson or man to man students name in the first box: ");
				MainFrame.instructions.append("\n" + "Enter lesson title in the second box: ");
				TextPanel.information.setText(" ");
			}else if(a == 1){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter students name in the first box: ");
				TextPanel.information.setText(" ");
			}else if(a == 2){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter students name in the first box: ");
				TextPanel.information.setText(" ");
			}else if(a == 3){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter students name in the first box: ");
				MainFrame.instructions.append("\n" + "Enter comment in the second box: ");
				TextPanel.information.setText(" ");
			}else if(a == 4){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter students name in the first box: ");
				TextPanel.information.setText(" ");
			}else if(a == 5){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter new lesson name in the first box: ");
				TextPanel.information.setText(" ");
			}else if(a == 6){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter lesson name in the first box: ");
				MainFrame.instructions.append("\n" + "Enter student name in the second box: ");
				TextPanel.information.setText(" ");
			}else if(a == 7){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter lesson name in the first box: ");
				TextPanel.information.setText(" ");
			}else if(a == 8){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("See information panel below. ");
				TextPanel.information.setText(" ");
				MainFrame.schoolInfo();
			}else if(a == 9){
				MainFrame.enter.setText("");
				MainFrame.enterTwo.setText("");
				MainFrame.instructions.setText("Enter the name of student or lesson you wish to delete in the first box.");
				TextPanel.information.setText(" ");
			}
		}

	}
}

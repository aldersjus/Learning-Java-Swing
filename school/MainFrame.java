package school;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Justin Alderson
 * Class to represent the main JFrame
 * 
 */
public class MainFrame extends JFrame implements ActionListener, Serializable {

	// //////////////////////////////////////
	protected static HashMap<String, School> hmS = new HashMap<String, School>();
	protected static ArrayList<String> allStudents = new ArrayList<String>();
	protected static ArrayList<String> allLessons = new ArrayList<String>();
	protected static HashMap<String, Student> hm = new HashMap<String, Student>();
	protected static HashMap<String, Lesson> hmL = new HashMap<String, Lesson>();
	protected static CapitalizeString cap = new CapitalizeString();
	
	private static final long serialVersionUID = 1L;
	// Add components.
	private JLabel label = new JLabel("EFL Management School System");
	private JLabel labelTwo = new JLabel("Enter details below and click the button");
	private JLabel labelThree = new JLabel("Instructions displayed below");
	protected DetailsPanel detailsPanel = new DetailsPanel();
	protected TextPanel text = new TextPanel();
	protected static JTextField enter = new JTextField();
	protected static JTextField enterTwo = new JTextField();
	protected static JButton b1 = new JButton("Confirm");
	protected static JButton b2 = new JButton("Confirm");
	protected static JTextArea instructions = new JTextArea();

	private static String pending = " ";
	private static String schoolName = "EFL Management School System";

	@SuppressWarnings("unchecked")
	public MainFrame(String title) {
		super(title);
		// Null layout this means use x and y coordinates.
		setLayout(null);

		// Add details to components.
		label.setFont(new Font("Verdana", Font.BOLD, 26));
		labelTwo.setFont(new Font("Courier New", Font.BOLD, 16));
		labelThree.setFont(new Font("Courier New", Font.BOLD, 16));
		detailsPanel.setBackground(Main.powderBlue);
		text.setBackground(Main.powderBlue);

		// Set components positions.
		detailsPanel.setBounds(10, 10, 250, 250);
		text.setBounds(10, 280, 780, 220);
		label.setBounds(300, 10, 500, 40);
		labelTwo.setBounds(300, 200, 500, 16);
		labelThree.setBounds(300, 70, 300, 16);
		enter.setBounds(300, 230, 140, 20);
		enterTwo.setBounds(450, 230, 140, 20);
		b1.setBounds(605, 230, 100, 20);

		instructions.setBounds(300, 100, 480, 60);
		instructions.setLineWrap(true);

		// Add to screen.
		add(label);
		add(labelTwo);
		add(labelThree);
		add(detailsPanel);
		add(text);
		add(enter);
		add(enterTwo);
		add(b1);
		add(instructions);

		b1.addActionListener(this);
		try {// MUST WRITE AND READ IN THE SAME ORDER
		//If no file found this will create a new file
		FileInputStream fis = new FileInputStream("data.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		hmL = (HashMap<String, Lesson>) ois.readObject();
		hm = (HashMap<String, Student>) ois.readObject();
		hmS = (HashMap<String, School>) ois.readObject();
		allLessons = (ArrayList<String>) ois.readObject();
		allStudents = (ArrayList<String>) ois.readObject();
		schoolName = (String)ois.readObject();
		ois.close();
		fis.close();
		} 
		catch (ClassNotFoundException e) {
			TextPanel.information.setText("File not Found");
		}
		catch (IOException ioe) {
	
			TextPanel.information.setText("No data");
		}
		if (hmS.get("School") == null) {
			School school = new School(schoolName);
			hmS.put("School", school);
		}
		else if(hmS.get("School") != null){
			TextPanel.information.setText("The name of your school is: "
					+ hmS.get("School").getName());
			}
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (DetailsPanel.listSelect == 0) {
			String accept = " ";
			String name = enterTwo.getText();
			if(name.length() > accept.length()){
			String namein = cap.capitalize(name);
			setPending(namein);
			markOff();
			}else{
				MainFrame.instructions.setText("No lesson name added please redo operation");
				DetailsPanel.list.clearSelection();
			}
		} else if (DetailsPanel.listSelect == 1) {
			addNewStudent();
		} else if (DetailsPanel.listSelect == 2) {
			addNewMtoMStudent();
		} else if (DetailsPanel.listSelect == 3) {
			String accept = " ";
			String name = enterTwo.getText();
			if(name.length() > accept.length()){
			String namein = cap.capitalize(name);
			setPending(namein);
			StudentInfoUpdate();
			}else{
				MainFrame.instructions.setText("No student name added please redo operation");
				DetailsPanel.list.clearSelection();
			}
		} else if (DetailsPanel.listSelect == 4) {
					studentInfo();
		} else if (DetailsPanel.listSelect == 5) {
			addNewLesson();
		} else if (DetailsPanel.listSelect == 6) {//Add a student to a lesson
			String acceptable = " ";
			String nameof = enterTwo.getText();
			if(nameof.length() > acceptable.length()){
			String nameofin = cap.capitalize(nameof);
			setPending(nameofin);
			addStudentToLesson();
			}else{
				MainFrame.instructions.setText("No student name added please redo operation");
				DetailsPanel.list.clearSelection();
			}
		} else if (DetailsPanel.listSelect == 7) {
			lessonInfo();
		} else if (DetailsPanel.listSelect == 8) {
			
		} else if (DetailsPanel.listSelect == 9) {
			usageGuide();
		}
	
	}
	public static  void setPending(String in){
		MainFrame.pending = in;
	}
	public static  String getPending(){
		return MainFrame.pending;
	}
	public static void addNewStudent() {
		String inputStudent = enter.getText();
		String inputStudentcap = MainFrame.cap.capitalize(inputStudent);
		if(hm.get(inputStudentcap) != null){
			TextPanel.information.setText("Student has already been created.\n"
					+ "Please choose a different name!"); 
			MainFrame.enter.setText(null);
		}else{
		Student student = new Student(inputStudentcap);
		hm.put(inputStudentcap, student);
		TextPanel.information.setText("The name of your new student is: "
				+ student.getName());
		enter.setText(" ");
		MainFrame.allStudents.add(inputStudentcap);
		DetailsPanel.list.clearSelection();
		saveData();
		}
	}
	public static void addNewMtoMStudent() {
		String nameof = enter.getText();
		String nameofin = cap.capitalize(nameof);
		if(hm.get(nameofin) != null | hmL.get(nameofin) != null){
			TextPanel.information.setText("Student has already been created.\n"
					+ "Please choose a different name!"); 
			MainFrame.enter.setText(null);
		}else{
		Lesson lesson = new Lesson(nameofin);
		lesson.addToClass("N/A, this is a Man to Man class.");
		hmL.put(nameofin, lesson);
		Student student = new Student(nameofin);
		hm.put(nameofin, student);
		allLessons.add(nameofin);
		allStudents.add(nameofin);
		TextPanel.information.setText("The name of your new Man to Man lesson is: "
				+ lesson.getName());
		TextPanel.information.append("\n" + " ");
		TextPanel.information.append("\n"
						+ "If you wish to mark off the lesson please select mark off and enter the student`s name.");
		DetailsPanel.list.clearSelection();
		saveData();
		}
	}
	// Create new lesson
	public static void addNewLesson() {
		String nameof = enter.getText();
		String nameofin = cap.capitalize(nameof);
		if(hmL.get(nameofin) != null){
			TextPanel.information.setText("Lesson has already been created.\n"
					+ "Please choose a different name!"); 
			MainFrame.enter.setText(null);
		}else{
		Lesson lesson = new Lesson(nameofin);
		hmL.put(nameofin, lesson);
		allLessons.add(nameofin);
		TextPanel.information.setText("The name of your new  lesson is: "
				+ lesson.getName());
		TextPanel.information.append("\n" + " ");
		TextPanel.information
				.append("\n"
						+ "If you wish to mark off the lesson please select mark off and enter the lesson`s name.");
		DetailsPanel.list.clearSelection();
		saveData();
		}
	}
	
	//Print student information
	public static void studentInfo() {
		String nameof = enter.getText();
		String nameofin = cap.capitalize(nameof);
		if (hm.get(nameofin) != null) {
			Student found = hm.get(nameofin);
			String info = found.getName();
			StringBuilder infoMain = new StringBuilder();
			TextPanel.information.setText("Student name is: " + info);
			for (int i = 0; i < found.comments.size(); i++) {
				infoMain.append(" ").append(found.comments.get(i)).append("...,");
			}
			TextPanel.information.append("\n" + "Student information: " + infoMain);
		} else {
			TextPanel.information.setText("Student not  in database! ");
			TextPanel.information.append("\n" + " ");
			TextPanel.information.append("\n"
					+ "Please ensure you are entering the correct name or create a new student file.");
			TextPanel.information.append("\n" + " ");
			TextPanel.information
					.append("\n"
							+ "Please select school info from the list to see the names of all students.");
		}
		DetailsPanel.list.clearSelection();
		}
	
	// Print lesson details
	public static void lessonInfo() {
		String nameof = enter.getText();
		String nameofin = cap.capitalize(nameof);
		if (hmL.get(nameofin) != null) {
			Lesson found = hmL.get(nameofin);
			String info = found.getName();
			StringBuilder infoMain = new StringBuilder();
			StringBuilder studied = new StringBuilder();
			TextPanel.information.append("Lesson name is: " + info + "\n");
			for (int i = 0; i < found.studentsInClass.size(); i++) {
				infoMain.append(" ").append(found.studentsInClass.get(i)).append("..,");
			}
			for (int i = 0; i < found.lessonTaken.size(); i++) {
				studied.append(" ").append(found.lessonTaken.get(i)).append(".....,");
			}
			TextPanel.information.append("\n" + "Students: " + infoMain);
			TextPanel.information.append("\n\n" + "Lessons taken by this class: "
					+ studied);

		} else {
			TextPanel.information.setText("Lesson not  in database! ");
			TextPanel.information.append("\n" + " ");
			TextPanel.information.append("\n"
					+ "Please ensure you are entering the correct name or create a new lesson file.");
			TextPanel.information.append("\n" + " ");
			TextPanel.information
					.append("\n"
							+ "Please select school info from the list to see the names of all lessons.");
		}
		DetailsPanel.list.clearSelection();
	}
	
	//Print school information
	public static void schoolInfo() {
		StringBuilder infoMain = new StringBuilder();
		StringBuilder informationMain = new StringBuilder();
		String name = hmS.get("School").getName();
		for (int i = 0; i < allStudents.size(); i++) {
			infoMain.append(" ").append(allStudents.get(i)).append("..,");
		}
		for (int i = 0; i < allLessons.size(); i++) {
			informationMain.append(" ").append(allLessons.get(i)).append("..,");
		}
		TextPanel.information.append("School name: " + name);
		TextPanel.information.append("\n" + " Students: " + infoMain);
		TextPanel.information.append("\n" + " Lessons: " + informationMain);
		DetailsPanel.list.clearSelection();
	}
	
		public static void StudentInfoUpdate() {
			String nameof = enter.getText();
			String nameofin = cap.capitalize(nameof);
			String title = getPending();
			if (hm.get(nameofin) != null) {
				Student found = hm.get(nameofin);
				String info = found.getName();
				found.setInfo(title);
				hm.put(info, found);
				TextPanel.information.append("Student name is: " + info
						+ "\n" + "Comment name added.");
				enter.setText(" ");
				enterTwo.setText(" ");
			}else{
				TextPanel.information.append("\n" + "Cannot find student");
			}
			saveData();
			DetailsPanel.list.clearSelection();
	}
	// Add student to lesson
	public static void addStudentToLesson() {
		String nameof = enter.getText();
		String nameofin = cap.capitalize(nameof);
		String title = getPending();
		if (hmL.get(nameofin) != null) {
			Lesson found = hmL.get(nameofin);
			String info = found.getName();
			found.addToClass(title);
			hmL.put(nameofin, found);
			TextPanel.information.append("Lesson name is: " + info
					+ "\n" + "Student`s name added.");
			enter.setText(" ");
			enterTwo.setText(" ");
		}else{
			TextPanel.information.append("\n" + "Cannot find lesson");
		}
		saveData();
		DetailsPanel.list.clearSelection();
	}
	
	///Mark off a lesson....
	public static void markOff(){
		String nameof = enter.getText();
		String nameofin = cap.capitalize(nameof);
		String title = getPending();
		if (hmL.get(nameofin) != null) {
			Lesson found = hmL.get(nameofin);
			String info = found.getName();
			found.addLesson(title);
			hmL.put(nameofin, found);
			TextPanel.information.append("Lesson name is: " + info
					+ "\n" + "Lesson title  added.");
			enter.setText(" ");
			enterTwo.setText(" ");
		}else{
			TextPanel.information.append("\n" + "Cannot find lesson");
		}
		saveData();
		DetailsPanel.list.clearSelection();
	}
	public static void usageGuide(){
		String nameof = enter.getText();
		String nameofin = cap.capitalize(nameof);
		if(hm.get(nameofin) != null & hmL.get(nameofin) != null){
			hm.remove(nameofin);
			hmL.remove(nameofin);
			allStudents.remove(nameofin);
			allLessons.remove(nameofin);
			TextPanel.information.append("\n" + "Man to man student deleted.");
		}else if(hm.get(nameofin) != null){
			hm.remove(nameofin);
			allStudents.remove(nameofin);
			TextPanel.information.append("\n" + "Student deleted.");
		}else if(hmL.get(nameofin) != null){
			hmL.remove(nameofin);
			allLessons.remove(nameofin);
			TextPanel.information.append("\n" + "Lesson deleted.");
		}else{
			TextPanel.information.append("\n" + "Cannot find any match.");
		}
	}
	public static void saveData(){
		try {// MUST WRITE AND READ IN THE SAME ORDER
		FileOutputStream fos = new FileOutputStream("data.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// USING SAME FILE MUST WRITE HASHMAP FOLLOWED BY ARRAYS
		oos.writeObject(hmL);
		oos.writeObject(hm);
		oos.writeObject(hmS);
		oos.writeObject(allLessons);
		oos.writeObject(allStudents);
		oos.close();
		fos.close();
		instructions.setText("Data saved");

	} catch (IOException ioe) {
		instructions.setText("Unable to save data.");
	 	}
		DetailsPanel.list.clearSelection();
		
	}
	
}

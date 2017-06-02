package school;

import java.io.Serializable;
import java.util.ArrayList;

public class Lesson implements Serializable{
	
	/**
	 * @author Justin Alderson
	 * Class to represent a lesson
	 */
	private static final long serialVersionUID = 1L;
	private String name = " ";
	//Below students in this class.
	protected ArrayList<String> studentsInClass = new ArrayList<String>();
	//Below lessons taken by this class.
	protected ArrayList<String> lessonTaken = new ArrayList<String>();
	private StringBuilder info = new StringBuilder();
	private StringBuilder information = new StringBuilder();
	
	
	public Lesson(String nameOfLesson){
		this.name = nameOfLesson;
	}
	public void addToClass(String nameOf){
		studentsInClass.add(nameOf);
		
	}
	public  StringBuilder printStudents(){
		for(String student : studentsInClass){
			info.append(" ").append(student);
		}
		return info;
	}
	public void addLesson(String nameOf){
		lessonTaken.add(nameOf);
		
	}
	public  StringBuilder printLessons(){
		for(String lesson : lessonTaken){
			information.append(" ").append(lesson);
		}
		return information;
	}
	public String getName(){
		return name;
		
	}
	

}

package school;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Justin Alderson
 * Class to represent a student
 *
 */
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	protected ArrayList<String> comments = new ArrayList<String>();
	private ArrayList<String> lessonTaken = new ArrayList<String>();
	private StringBuilder info = new StringBuilder();

	public Student(String name){
		this.name = name;
	}
	public void setInfo(String information){
		comments.add(information);
	}
	public String getName(){
		return name;
		
	}
	public void addLesson(String nameOf){
		lessonTaken.add(nameOf);
		
	}
	public  StringBuilder printLessonTaken(){
		for(String word : lessonTaken){
			info.append(" ").append(word);
		}
		return info;
		
	}
	public  StringBuilder printComments(){
		for(String word : comments){
			info.append(" ").append(word);
		}
		return info;
	}
}



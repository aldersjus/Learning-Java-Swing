package school;

import java.io.Serializable;

public class School implements Serializable{
	
	/**
	 * 
	 * @author Justin Alderson
	 * Class to represent school
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected  String schoolName = " ";
	protected static boolean schoolCreated = false;
	
	
	public School(String nameof){
		schoolName = nameof;
		School.schoolCreated = true;
	}
	public String getName(){
		return schoolName;
	}
	
}

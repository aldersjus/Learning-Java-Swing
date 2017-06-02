package school;

/**
 * 
 * @author Justin Alderson
 * Class to perform capitalization
 *
 */
public class CapitalizeString{
	public  String capitalize(String word) {
		String a = Character.toUpperCase(word.charAt(0)) + word.substring(1);
		return a;
	}
}

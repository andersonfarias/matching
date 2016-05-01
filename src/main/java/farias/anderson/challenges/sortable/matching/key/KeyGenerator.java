package farias.anderson.challenges.sortable.matching.key;

/**
 * Represents a class that generates a key for an object to use in string
 * similarities algorithms.
 * 
 * @author Anderson Farias
 */
public interface KeyGenerator {

	/**
	 * Generates an String that will be used in String similarity algorithms
	 * 
	 * @return String for similarity algorithms
	 */
	public String generate();

}
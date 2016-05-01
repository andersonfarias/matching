package farias.anderson.challenges.sortable.matching.matchers;

/**
 * Interface to define a String Matcher. A String Matcher is a class responsible
 * for the decision of whether the String a and b are similar enought to be
 * considered a match
 * 
 * @author Anderson Farias
 */
public interface StringMatcher {

	/**
	 * Returns true when the given Strings a and b matches, false otherwise
	 * 
	 * @param a
	 *            String to compare
	 * @param b
	 *            The other String to compare
	 * @return true when the given Strings a and b matches, false otherwise
	 */
	public boolean matches( String a, String b );
}
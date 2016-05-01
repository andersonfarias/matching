package farias.anderson.challenges.sortable.matching.matchers;

/**
 * Interface to define a Best Matcher. A best matcher is a class responsible for
 * the decision of which item best matches, if any, between a reference String
 * and a set of Strings
 * 
 * @author Anderson Farias
 *
 * @param <T>
 *            Type of the item to compare and found out the one with the best
 *            match, if any
 */
public interface BestMatcher<T> {

	/**
	 * Add the new String b to set of matches for the string a that is
	 * associated to the item item
	 * 
	 * @param a
	 *            reference string
	 * @param b
	 *            string to compare against the reference string and maybe be
	 *            the best match
	 * @param item
	 *            item associated to the string with the match
	 */
	public void add( String a, String b, T item );

	/**
	 * Returns the best match among the added items
	 * 
	 * @return the best match among the added items or null if none of those
	 *         were considered good enough for a match
	 */
	public T getBest();

}
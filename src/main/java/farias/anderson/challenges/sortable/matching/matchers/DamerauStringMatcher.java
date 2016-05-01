package farias.anderson.challenges.sortable.matching.matchers;

import info.debatty.java.stringsimilarity.Damerau;

/**
 * A implementation of a {@link StringMatcher} that uses the Damerou's algorithm
 * for saying whether the String a and b are similar enough to be considered a
 * match.
 * 
 * For more details about the Damerou's algorithm, please check this out:
 * https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance
 * 
 * @see StringMatcher
 * @author Anderson Farias
 */
public class DamerauStringMatcher implements StringMatcher {

	/**
	 * Threshold to decide between a true or false match
	 */
	private double threshold;

	/**
	 * Instance of the implementation of the Damerau's algorithm
	 */
	private Damerau damerau;

	/**
	 * {@inheritDoc StringMatcher#matches(String, String)}
	 */
	@Override
	public boolean matches( String a, String b ) {
		return damerau.distance( a, b ) <= threshold;
	}

	/**
	 * Constructor
	 * 
	 * @param threshold
	 *            threshold to decide between a true or false match
	 */
	public DamerauStringMatcher( double threshold ) {
		super();
		this.threshold = threshold;
		this.damerau = new Damerau();
	}

}
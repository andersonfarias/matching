package farias.anderson.challenges.sortable.matching.matchers;

import info.debatty.java.stringsimilarity.JaroWinkler;

/**
 * A implementation of a {@link StringMatcher} that uses the JaroWinkler
 * algorithm for saying whether the String a and b are similar enough to be
 * considered a match.
 * 
 * For more details about the JaroWinkler algorithm, please check this out:
 * https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance
 * 
 * @see StringMatcher
 * @author Anderson Farias
 */
public class JaroWinklerStringMatcher implements StringMatcher {

	/**
	 * Threshold to decide between a true or false match
	 */
	private double threshold;

	/**
	 * Instance of the implementation of the Jaro Winkler algorithm
	 */
	private JaroWinkler jaroWinkler;

	/**
	 * {@inheritDoc StringMatcher#matches(String, String)}
	 */
	@Override
	public boolean matches( String a, String b ) {
		return jaroWinkler.distance( a, b ) >= threshold;
	}

	/**
	 * Constructor
	 * 
	 * @param threshold
	 *            threshold to decide between a true or false match
	 */
	public JaroWinklerStringMatcher( double threshold ) {
		super();
		this.threshold = threshold;
		this.jaroWinkler = new JaroWinkler();
	}

}
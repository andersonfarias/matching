package farias.anderson.challenges.sortable.matching.matchers;

import farias.anderson.challenges.sortable.matching.Config;
import farias.anderson.challenges.sortable.matching.domain.Product;
import info.debatty.java.stringsimilarity.JaroWinkler;

/**
 * Implementation of a {@link BestMatcher} that uses the Jaro Winkler algorithm
 * to say what is item that best matches, if any, between a reference String and
 * a set of Strings
 * 
 * For more details about the JaroWinkler algorithm, please check this out:
 * https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance
 * 
 * @author Anderson Farias
 */
public class JaroWinklerBestMatcher implements BestMatcher<Product> {

	/**
	 * Max similarity
	 */
	double max;

	/**
	 * Instance of the implementation of the Jaro Winkler algorithm
	 */
	private JaroWinkler jw;

	/**
	 * Item
	 */
	private Product product;

	/**
	 * {@inheritDoc BestMatcher#add(String, String, Object)}
	 */
	@Override
	public void add( String a, String b, Product p ) {
		double similarity = jw.similarity( a, b );

		if ( similarity > max ) {
			this.max = similarity;
			this.product = p;
		}
	}

	/**
	 * {@inheritDoc BestMatcher#getBest()}
	 */
	@Override
	public Product getBest() {
		boolean thereIsABestMatch = this.max >= Config.JARO_WINKLER_BEST_MATCHER_THRESHOLD;

		return thereIsABestMatch ? this.product : null;
	}

	/**
	 * Default constructor
	 */
	public JaroWinklerBestMatcher() {
		this.jw = new JaroWinkler();
		this.max = Integer.MIN_VALUE;
	}
}
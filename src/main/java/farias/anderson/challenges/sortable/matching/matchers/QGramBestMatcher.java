package farias.anderson.challenges.sortable.matching.matchers;

import org.apache.commons.lang3.StringUtils;

import farias.anderson.challenges.sortable.matching.Config;
import farias.anderson.challenges.sortable.matching.domain.Product;
import info.debatty.java.stringsimilarity.QGram;

/**
 * Implementation of a {@link BestMatcher} that uses the QGram algorithm to say
 * what is item that best matches, if any, between a reference String and a set
 * of Strings
 * 
 * For more details about the QGram algorithm, please check this out:
 * https://en.wikipedia.org/wiki/N-gram
 * 
 * @author Anderson Farias
 */
public class QGramBestMatcher implements BestMatcher<Product> {

	/**
	 * Closest distance between the given Strings and the reference one
	 */
	double min;

	/**
	 * Instance of the implementation of the QGram algorithm
	 */
	private QGram qGram;

	/**
	 * Item
	 */
	private Product product;

	/**
	 * {@inheritDoc BestMatcher#add(String, String, Object)}
	 */
	@Override
	public void add( String a, String b, Product p ) {
		double similarity = qGram.distance( a, b );

		if ( similarity < min ) {
			this.min = similarity;
			this.product = p;
		}
	}

	/**
	 * {@inheritDoc BestMatcher#getBest()}
	 */
	@Override
	public Product getBest() {
		boolean thereIsABestMatch = this.min < Config.QGRAM_BEST_MATCHER_THRESHOLD && !StringUtils.isEmpty( this.product.getFamily() );

		return thereIsABestMatch ? this.product : null;
	}

	/**
	 * Constructor
	 * 
	 * @param k
	 *            length of each n-gram
	 */
	public QGramBestMatcher( int k ) {
		this.qGram = new QGram( k );
		this.min = Integer.MAX_VALUE;
	}
}
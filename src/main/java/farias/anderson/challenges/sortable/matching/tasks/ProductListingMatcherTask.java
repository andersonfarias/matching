package farias.anderson.challenges.sortable.matching.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import farias.anderson.challenges.sortable.matching.blocking.Block;
import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;
import farias.anderson.challenges.sortable.matching.domain.Result;
import farias.anderson.challenges.sortable.matching.key.KeyGenerator;
import farias.anderson.challenges.sortable.matching.key.ListingKeyGenerator;
import farias.anderson.challenges.sortable.matching.key.ProductKeyGenerator;
import farias.anderson.challenges.sortable.matching.matchers.BestMatcher;
import farias.anderson.challenges.sortable.matching.matchers.JaroWinklerBestMatcher;
import farias.anderson.challenges.sortable.matching.matchers.QGramBestMatcher;

/**
 * Class responsible for matching a set of given products and listings. Do do
 * that, this class does a cartesian product between the products and listings
 * and for each pair, it uses a combination of {@link BestMatcher}s to find the
 * best match among all pairs. In the end, it returns all the matches in a list
 * of {@link Result}.
 * 
 * @see JaroWinklerBestMatcher
 * @see QGramBestMatcher
 * 
 * @author Anderson Farias
 */
public class ProductListingMatcherTask implements Callable<List<Result>> {

	/**
	 * {@link Block} that contains the products and listings
	 */
	private Block block;

	/**
	 * Executes, in a thread-safe manner, the matching for the given block and
	 * returns the matching result in a list of {@link Result}
	 * 
	 * @return list of {@link Result}
	 * @throws Exception
	 *             Exception if unable to compute a result
	 */
	@Override
	public List<Result> call() throws Exception {

		List<Product> a = block.getProducts();
		List<Listing> b = block.getListings();

		for ( Listing listing : b ) {

			BestMatcher<Product> jwMatcher = new JaroWinklerBestMatcher();
			BestMatcher<Product> qGramMatcher = null;

			for ( Product product : a ) {

				int k = product.getModel() != null ? product.getModel().length() : 3;

				qGramMatcher = new QGramBestMatcher( k );

				KeyGenerator productKey = new ProductKeyGenerator( product );
				KeyGenerator listingKey = new ListingKeyGenerator( listing, product );

				jwMatcher.add( productKey.generate(), listingKey.generate(), product );
				qGramMatcher.add( productKey.generate(), listingKey.generate(), product );

			}

			Product p = null;

			if ( qGramMatcher != null )
				p = qGramMatcher.getBest();
			if ( p == null )
				p = jwMatcher.getBest();

			if ( p != null )
				p.getListings().add( listing );
		}

		List<Result> results = new ArrayList<>();
		for ( Product p : a )
			results.add( new Result( p.getProductName(), p.getListings() ) );

		return results;
	}

	/**
	 * Constructor
	 * 
	 * @param block
	 *            block to perform the matching
	 */
	public ProductListingMatcherTask( Block block ) {
		super();
		this.block = block;
	}

}
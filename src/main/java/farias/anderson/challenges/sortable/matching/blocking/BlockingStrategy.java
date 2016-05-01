package farias.anderson.challenges.sortable.matching.blocking;

import java.util.Collection;
import java.util.List;

import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;

/**
 * Represents one strategy to blocking.
 * 
 * The standard (naive) approach to find matches in n input objects is to
 * compare each object with every other object. This requires lots of
 * comparisons, basically a complete Cartesian product (OA × OB ) is examined.
 * The resulting quadratic complexity of O(n2) results in infeasible execution
 * times in particular for large input sets. Therefore, an initial step in the
 * matching process called blocking is commonly applied to reduce the search
 * space to a small subset of the most likely matching object pairs.
 * 
 * Blocking approaches semantically partition the input data into blocks of
 * similar objects and restrict object matching to objects of the same block.
 * 
 * @author Anderson Farias
 */
public interface BlockingStrategy {

	/**
	 * Partition the input data into blocks of similar objects. In this case, it
	 * generates a block that contains similar products along with it's similar
	 * listings.
	 * 
	 * @param products
	 *            list of products
	 * @param listings
	 *            list of listings
	 * @return list of partitions
	 */
	public Collection<Block> generate( List<Product> products, List<Listing> listings );

}
package farias.anderson.challenges.sortable.matching.data.load;

import java.util.List;

import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;

/**
 * Represent an object responsible for loading the data
 * 
 * @author Anderson Farias
 */
public interface DataLoader {

	/**
	 * Load a list of {@link Product}
	 * 
	 * @return list of loaded {@link Product}
	 */
	public List<Product> loadProducts();

	/**
	 * Load a list of {@link Listing}
	 * 
	 * @return list of loaded {@link Listing}
	 */
	public List<Listing> loadListings();
}
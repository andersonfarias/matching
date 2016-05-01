package farias.anderson.challenges.sortable.matching.blocking;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import farias.anderson.challenges.sortable.matching.data.clean.StringCleaner;
import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;
import farias.anderson.challenges.sortable.matching.matchers.ManufacturerStringMatcher;
import farias.anderson.challenges.sortable.matching.matchers.StringMatcher;

/**
 * Blocking strategy that create blocks based on the manufacturer. It uses the
 * {@link ManufacturerStringMatcher} to see whether a product and a manufacturer
 * have the same manufacturer
 * 
 * @author Anderson Farias
 */
public class ManufacturerBlockingStrategy implements BlockingStrategy {

	/**
	 * Partition the input data into blocks of similar objects. In this case, it
	 * generates a block that contains similar products along with it's similar
	 * listings based on the manufacturer, in other words, if the product and
	 * the listing have the same manufacturer, then they must go at the same
	 * blocking
	 * 
	 * @param products
	 *            list of products
	 * @param listings
	 *            list of listings
	 * @return list of partitions
	 */
	@Override
	public Collection<Block> generate( List<Product> products, List<Listing> listings ) {
		Map<String, Block> blockings = new HashMap<>();

		for ( Product product : products ) {
			boolean missingRequiredData = StringUtils.isBlank( product.getProductName() ) || StringUtils.isBlank( product.getManufacturer() );

			if ( missingRequiredData )
				continue;

			String manufacturer = StringCleaner.clean( product.getManufacturer() );

			if ( !blockings.containsKey( manufacturer ) )
				blockings.put( manufacturer, new Block() );

			blockings.get( manufacturer ).getProducts().add( product );
		}

		StringMatcher manufacturerMatcher = new ManufacturerStringMatcher();

		for ( Listing listing : listings ) {
			boolean missingRequiredData = StringUtils.isBlank( listing.getTitle() ) || StringUtils.isBlank( listing.getManufacturer() );

			if ( missingRequiredData )
				continue;

			String listingManufacturer = StringCleaner.clean( listing.getManufacturer() );

			for ( String productManufacturer : blockings.keySet() )
				if ( manufacturerMatcher.matches( productManufacturer, listingManufacturer ) )
					blockings.get( productManufacturer ).getListings().add( listing );

		}

		return blockings.values();
	}

}
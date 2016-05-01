package farias.anderson.challenges.sortable.matching.key;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import farias.anderson.challenges.sortable.matching.data.clean.StringCleaner;
import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;

/**
 * Class responsible for the generation of a String for a given {@link Listing}
 * that will be used in String similarity algorithms
 * 
 * @author Anderson Farias
 */
public class ListingKeyGenerator implements KeyGenerator {

	/**
	 * Listing to generate the key
	 */
	private Listing listing;

	/**
	 * Product that will be matched against the listing
	 */
	private Product product;

	/**
	 * Generates an String that will be used in String similarity algorithms for
	 * the given listing. The String is generated based on the "cleaned" listing
	 * title and removing the product's manufacturer from that title (if
	 * present). Next possible unnecessary white-spaces are removed and the
	 * string is normalized.
	 * 
	 * @see StringCleaner
	 * @see Normalizer
	 * @return Listing String for similarity algorithms
	 */
	@Override
	public String generate() {

		String listKey = StringCleaner.clean( listing.getTitle() );
		String[] listKeys = listKey.replaceAll( product.getManufacturer(), "" ).split( " " );

		StringBuilder sb = new StringBuilder();
		for ( String listKeyPiece : listKeys ) {

			if ( listKeyPiece.trim().isEmpty() )
				continue;

			if ( sb.length() > 0 )
				sb.append( " " );

			sb.append( listKeyPiece.trim() );
		}

		return Normalizer.normalize( sb.toString(), Form.NFC );
	}

	/**
	 * Constructor
	 * 
	 * @param listing
	 *            listing to generate the key
	 * @param product
	 *            product that will be matched against the given listing
	 */
	public ListingKeyGenerator( Listing listing, Product product ) {
		super();
		this.listing = listing;
		this.product = product;
	}

}
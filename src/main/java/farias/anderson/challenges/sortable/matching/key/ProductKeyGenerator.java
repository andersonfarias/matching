package farias.anderson.challenges.sortable.matching.key;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.apache.commons.lang3.StringUtils;

import farias.anderson.challenges.sortable.matching.data.clean.StringCleaner;
import farias.anderson.challenges.sortable.matching.domain.Product;

/**
 * Class responsible for the generation of a String for a given {@link Product}
 * that will be used in String similarity algorithms
 * 
 * @author Anderson Farias
 */
public class ProductKeyGenerator implements KeyGenerator {

	/**
	 * Product to generate the key
	 */
	private Product product;

	/**
	 * Generates an String that will be used in String similarity algorithms for
	 * the given product. The String is generated based on the "cleaned"
	 * product's name, model and family present). Next the string is normalized.
	 * 
	 * @see StringCleaner
	 * @see Normalizer
	 * @return Product String for similarity algorithms
	 */
	@Override
	public String generate() {

		String key = StringCleaner.clean( product.getProductName() );
		String model = StringCleaner.clean( product.getModel() );

		if ( !key.contains( model ) )
			key = key.concat( " ".concat( model ) );

		if ( !StringUtils.isEmpty( product.getFamily() ) )
			key += " " + StringCleaner.clean( product.getFamily() );

		return Normalizer.normalize( key, Form.NFC );
	}

	/**
	 * Constructor
	 * 
	 * @param product
	 *            product to generate the key
	 */
	public ProductKeyGenerator( Product product ) {
		super();
		this.product = product;
	}

}
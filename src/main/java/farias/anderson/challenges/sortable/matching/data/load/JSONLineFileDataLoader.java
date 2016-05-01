package farias.anderson.challenges.sortable.matching.data.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.Validate;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import farias.anderson.challenges.sortable.matching.Config;
import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;

/**
 * This is a data loader that loads the data from a file with one JSON object
 * per line.
 * 
 * @see DataLoader
 * @author Anderson Farias
 */
public class JSONLineFileDataLoader implements DataLoader {

	/**
	 * File that contains the errors, warnings and successful messages
	 */
	private ResourceBundle messages;

	/**
	 * JSON parser
	 */
	private Gson jsonParser;

	/**
	 * Complete path of the file that contains the products
	 * 
	 * @see Product
	 */
	private String productsFilePath;

	/**
	 * Complete path of the file that contains the listings
	 * 
	 * @see Listing
	 */
	private String listingsFilePath;

	/**
	 * {@inheritDoc DataLoader#loadProducts()}
	 */
	@Override
	public List<Product> loadProducts() {
		return loadData( Product.class, this.productsFilePath, "product file path" );
	}

	/**
	 * {@inheritDoc DataLoader#loadListings()}
	 */
	@Override
	public List<Listing> loadListings() {
		return loadData( Listing.class, this.listingsFilePath, "listings file path" );
	}

	/**
	 * Load the contents of file containing one json object per line, parse each
	 * one and return the parsed list to the given object type
	 * 
	 * @param type
	 *            object type to convert the json
	 * @param filePath
	 *            complete path of the file that contains the jsons
	 * @param label
	 *            human readable description of the file
	 * @return the contents of file containing one json object per line, parse
	 *         each one and return the parsed list to the given object type
	 */
	private <T> List<T> loadData( Class<T> type, String filePath, String label ) {
		Validate.notEmpty( filePath, messages.getString( "IllegalArgumentException.NotNull" ), label );

		File file = new File( filePath );

		if ( !file.exists() )
			throw new IllegalArgumentException( String.format( messages.getString( "IllegalArgumentException.FileDoNotExists" ), filePath ) );

		if ( !file.isFile() )
			throw new IllegalArgumentException( String.format( messages.getString( "IllegalArgumentException.NotAFile" ), filePath ) );

		LineIterator it = null;
		try {
			it = FileUtils.lineIterator( file );
		} catch ( IOException e ) {
			throw new RuntimeException( e.getMessage() );
		}

		List<T> data = new ArrayList<>();
		while ( it.hasNext() )
			data.add( jsonParser.fromJson( it.next(), type ) );

		return data;
	}

	/**
	 * Constructor
	 * 
	 * @param productsFilePath
	 *            Complete path of the file that contains the products
	 * @param listingsFilePath
	 *            Complete path of the file that contains the listings
	 * @see Product
	 * @see Listing
	 */
	public JSONLineFileDataLoader( String productsFilePath, String listingsFilePath ) {
		super();
		this.productsFilePath = productsFilePath;
		this.listingsFilePath = listingsFilePath;
		this.messages = ResourceBundle.getBundle( Config.MESSAGES_FILE_NAME );
		this.jsonParser = new GsonBuilder().setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES ).create();
	}

}
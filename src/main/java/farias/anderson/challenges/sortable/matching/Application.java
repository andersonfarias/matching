package farias.anderson.challenges.sortable.matching;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import farias.anderson.challenges.sortable.matching.blocking.Block;
import farias.anderson.challenges.sortable.matching.blocking.BlockingStrategy;
import farias.anderson.challenges.sortable.matching.blocking.ManufacturerBlockingStrategy;
import farias.anderson.challenges.sortable.matching.data.load.DataLoader;
import farias.anderson.challenges.sortable.matching.data.load.JSONLineFileDataLoader;
import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;
import farias.anderson.challenges.sortable.matching.domain.Result;
import farias.anderson.challenges.sortable.matching.tasks.ProductListingMatcherTask;

/**
 * Entry point of the application
 * 
 * @author Anderson Farias
 */
public class Application {

	/**
	 * Main method of the application It expects the following arguments
	 * <ul>
	 * <li><b>products</b>: (required) path of the products file</li>
	 * <li><b>listings</b>: (required) path of the listings file</li>
	 * <li><b>result</b>: (required) path of the output result file</li>
	 * </ul>
	 * Usage example:<br>
	 * java -jar matching-1.0.0.jar -products ~/products.txt -listings
	 * ~/listings.txt -result ~/result.txt
	 * 
	 * @param args
	 *            arguments
	 * @throws Exception
	 *             if some error occur during the program's execution
	 */
	public static void main( String[] args ) throws Exception {

		// Initialing the command line options
		Options options = new Options();

		for ( farias.anderson.challenges.sortable.matching.cli.Options option : farias.anderson.challenges.sortable.matching.cli.Options.values() ) {
			if ( option.isBool() ) {
				options.addOption( new Option( option.getName(), option.getDescription() ) );
			} else {
				options.addOption( option.getName(), true, option.getDescription() );
			}
		}

		// Command line args parser
		CommandLineParser parser = new DefaultParser();
		CommandLine line = null;
		try {
			// parse the command line arguments
			line = parser.parse( options, args );
		} catch ( ParseException exp ) {
			// oops, something went wrong
			System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
			System.exit( 0 );
		}

		boolean printHelp = line.hasOption( farias.anderson.challenges.sortable.matching.cli.Options.HELP.getName() );
		if ( printHelp ) {
			// automatically generate the help statement
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "matching", options );
			System.exit( 0 );
		}

		String productsFilePath = null;
		String listingsFilePath = null;
		String resultsFilePath = null;

		if ( line.hasOption( farias.anderson.challenges.sortable.matching.cli.Options.PRODUCTS.getName() ) )
			productsFilePath = line.getOptionValue( farias.anderson.challenges.sortable.matching.cli.Options.PRODUCTS.getName() );
		if ( line.hasOption( farias.anderson.challenges.sortable.matching.cli.Options.LISTINGS.getName() ) )
			listingsFilePath = line.getOptionValue( farias.anderson.challenges.sortable.matching.cli.Options.LISTINGS.getName() );
		if ( line.hasOption( farias.anderson.challenges.sortable.matching.cli.Options.RESULT.getName() ) )
			resultsFilePath = line.getOptionValue( farias.anderson.challenges.sortable.matching.cli.Options.RESULT.getName() );

		if ( StringUtils.isEmpty( productsFilePath ) || StringUtils.isEmpty( listingsFilePath ) || StringUtils.isEmpty( resultsFilePath ) ) {
			System.err.println( "Missing required params!" );
			System.exit( 0 );
		}

		System.out.println( "Loading Data." );
		DataLoader dataLoader = new JSONLineFileDataLoader( productsFilePath, listingsFilePath );

		List<Listing> listings = dataLoader.loadListings();
		List<Product> products = dataLoader.loadProducts();

		// check for empty listings and products
		if ( listings.isEmpty() || products.isEmpty() ) {
			System.err.println( "There must be at least one product and one listings!" );
			System.exit( 0 );
		}

		BlockingStrategy blockingStrategy = new ManufacturerBlockingStrategy();

		System.out.println( "Executing Blocking." );
		Collection<Block> blockings = blockingStrategy.generate( products, listings );

		System.out.println( "Preparing tasks for parallel execution." );
		List<Callable<List<Result>>> parallelTasks = new ArrayList<>( blockings.size() );
		for ( Block block : blockings )
			parallelTasks.add( new ProductListingMatcherTask( block ) );

		ExecutorService executor = Executors.newWorkStealingPool();

		Gson jsonParser = new GsonBuilder().setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES ).create();
		List<String> lines = new ArrayList<>( products.size() );

		System.out.println( "Executing." );
		executor.invokeAll( parallelTasks )

		.stream().map( future -> {
			try {
				return future.get( 1, TimeUnit.MINUTES );
			} catch ( Exception e ) {
				e.printStackTrace();
				throw new RuntimeException( e.getMessage() );
			}
		} ).forEach( result -> {
			if ( result == null ) {
				System.out.println( "Porra é essa??" );
			}

			for ( Result r : result )
				lines.add( jsonParser.toJson( r ) );
		} );

		File output = new File( resultsFilePath );

		System.out.println( "Outputing result." );
		FileUtils.writeLines( output, lines );
		System.out.println( "Done!" );
	}
}
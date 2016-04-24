package farias.anderson.challenges.sortable.matching;

import java.io.File;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import farias.anderson.challenges.sortable.matching.datastructure.Block;
import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;
import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.Jaccard;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.KShingling;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.LongestCommonSubsequence;
import info.debatty.java.stringsimilarity.NGram;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import info.debatty.java.stringsimilarity.QGram;
import info.debatty.java.stringsimilarity.SorensenDice;
import info.debatty.java.stringsimilarity.StringProfile;

public class Application {

	private static final int LEVENSHTEIN_SINGLE_WORD_THRESHOLD = 2;

	private static final int LEVENSHTEIN_COMPOSED_WORDS_THRESHOLD = 1;

	private static final double JARO_WINKLER_THRESHOLD = 0.8;

	public static void main( String[] args ) throws Exception {

		ClassLoader cl = Application.class.getClassLoader();

		File listings = new File( cl.getResource( "listings.txt" ).getFile() );
		File products = new File( cl.getResource( "products.txt" ).getFile() );

		List<String> listingsJSON = FileUtils.readLines( listings );
		List<String> productsJSON = FileUtils.readLines( products );

		System.out.println( String.format( "Amount of Listings: %d", listingsJSON.size() ) );
		System.out.println( String.format( "Amount of Products: %d", productsJSON.size() ) );

		Gson gson = new GsonBuilder().setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES ).create();

		Map<String, Block> blockings = new HashMap<>();

		for ( String json : productsJSON ) {
			Product product = gson.fromJson( json, Product.class );

			String manufacturer = product.getManufacturer().trim().toLowerCase();
			if ( !blockings.containsKey( manufacturer ) )
				blockings.put( manufacturer, new Block() );

			Block block = blockings.get( manufacturer );
			block.getProducts().add( product );
		}

		Set<String> notMatched = new HashSet<>();
		for ( String json : listingsJSON ) {
			Listing listing = gson.fromJson( json, Listing.class );

			if ( listing.getManufacturer() == null || listing.getManufacturer().trim().isEmpty() ) {
				continue;
			}

			String manufacturer = listing.getManufacturer().trim().toLowerCase();

			double d = 0;
			boolean matched = false;

			Damerau damerau = new Damerau();
			JaroWinkler jw = new JaroWinkler();

			for ( String key : blockings.keySet() ) {
				d = damerau.distance( key, manufacturer.trim().toLowerCase() );

				if ( d <= LEVENSHTEIN_SINGLE_WORD_THRESHOLD ) {
					blockings.get( key ).getListings().add( listing );
					matched = true;
					break;
				}

				String[] pieces = manufacturer.split( " " );

				for ( String piece : pieces ) {
					d = damerau.distance( key, piece.trim().toLowerCase() );

					if ( d <= LEVENSHTEIN_COMPOSED_WORDS_THRESHOLD ) {
						blockings.get( key ).getListings().add( listing );
						matched = true;
						break;
					}
				}

				if ( pieces.length > 1 ) {
					StringBuilder acronym = new StringBuilder();
					for ( String piece : pieces ) {
						piece = piece.trim().toLowerCase();
						if ( piece.isEmpty() )
							continue;

						acronym.append( new Character( piece.charAt( 0 ) ).toString() );
					}

					d = damerau.distance( key, acronym.toString() );

					if ( d <= LEVENSHTEIN_COMPOSED_WORDS_THRESHOLD ) {
						blockings.get( key ).getListings().add( listing );
						matched = true;
						break;
					}
				}

				d = jw.similarity( key, manufacturer.trim().toLowerCase() );
				if ( d >= JARO_WINKLER_THRESHOLD ) {
					blockings.get( key ).getListings().add( listing );
					matched = true;
					break;
				}
			}

			if ( matched )
				continue;

			notMatched.add( manufacturer );
		}

		System.out.println( "Quantidade de Listagens fora do matching: " + notMatched.size() );
		System.out.println( "Listagens fora do matching: " + Arrays.deepToString( notMatched.toArray() ) );

		JaroWinkler jw = new JaroWinkler(); // threshold
		Levenshtein levenshtein = new Levenshtein();
		NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();
		Damerau damerau = new Damerau();

		QGram qgram = new QGram();// n
		NGram ngram = new NGram(); // n

		Cosine cosine = new Cosine(); // k
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		SorensenDice sorensen = new SorensenDice(); // k
		
		double similarityJW, similarityJaccard, maxJW, maxJaccard;
		double similarityLevenshtein, similarityNLevenshtein, minLevenshtein, minNLevenshtein, similarityDamerau, minDamerau;
		double similarityQGram, similarityNGram, minQGram, maxNGram;
		double similarityCosine, maxCosine;
		double similarityLCS, maxLCS;
		double similaritySorensen, maxSorensen;
		
		double normalizedQGram = 0, p1=0, p2=0;

		for ( String key : blockings.keySet() ) {

			List<Product> a = blockings.get( key ).getProducts();
			List<Listing> b = blockings.get( key ).getListings();

			for ( Listing listing : b ) {
				String listKey = listing.getTitle().trim().toLowerCase().replaceAll( "[_\\-,;:%^]", " " );
				listKey = Normalizer.normalize( listKey, Form.NFC );

				maxJW = Integer.MIN_VALUE;
				maxJaccard = Integer.MIN_VALUE;
				minLevenshtein = Integer.MAX_VALUE;
				minNLevenshtein = Integer.MAX_VALUE;
				minDamerau = Integer.MAX_VALUE;
				maxNGram = Integer.MIN_VALUE;
				
				minQGram = Integer.MAX_VALUE;
				normalizedQGram = 0;
				
				maxCosine = Integer.MIN_VALUE;
				maxLCS = Integer.MIN_VALUE;
				maxSorensen = Integer.MIN_VALUE;

				Product pJW = null;
				Product pJaccard = null;
				Product pLevenshtein = null;
				Product pNLevenshtein = null;
				Product pDemarau = null;
				
				Product pMinQGram = null;
				Product pMaxNGram = null;
				
				Product pCosine = null;
				Product pLCS = null;
				Product pSorensen = null;

				int k = Integer.MAX_VALUE;
				for ( String token : listKey.split( " " ) ) {
					if ( token.trim().isEmpty() )
						continue;

					if ( token.length() < k )
						k = token.length();
				}

				Jaccard jaccard = new Jaccard( k );

				for ( Product product : a ) {

					String productKey = String.format( "%s %s", product.getProductName().trim().toLowerCase(), product.getModel().trim().toLowerCase() );
					if ( product.getFamily() != null && !product.getFamily().trim().isEmpty() )
						productKey += " " + product.getFamily().trim().toLowerCase();

					productKey = productKey.replaceAll( "[_\\-,;:%^]", " " );
					productKey = Normalizer.normalize( productKey, Form.NFC );

					similarityJW = jw.similarity( productKey, listKey );
					similarityJaccard = jaccard.similarity( productKey, listKey );
					similarityLevenshtein = levenshtein.distance( productKey, listKey );
					similarityNLevenshtein = normalizedLevenshtein.distance( productKey, listKey );
					similarityDamerau = damerau.distance( productKey, listKey );
					similarityNGram = ngram.distance( productKey, listKey );
					similarityQGram = qgram.distance( productKey, listKey );
					similarityCosine = cosine.distance( productKey, listKey );
					similarityLCS = lcs.distance( productKey, listKey );
					similaritySorensen = sorensen.distance( productKey, listKey );

					if ( similarityJW >= maxJW ) {
						maxJW = similarityJW;
						pJW = product;
					}

					if ( similarityJaccard >= maxJaccard ) {
						maxJaccard = similarityJaccard;
						pJaccard = product;
					}

					if ( similarityLevenshtein < minLevenshtein ) {
						minLevenshtein = similarityLevenshtein;
						pLevenshtein = product;
					}

					if ( similarityNLevenshtein < minNLevenshtein ) {
						minNLevenshtein = similarityNLevenshtein;
						pNLevenshtein = product;
					}

					if ( similarityDamerau < minDamerau ) {
						minDamerau = similarityDamerau;
						pDemarau = product;
					}

					if ( similarityNGram > maxNGram ) {
						maxNGram = similarityNGram;
						pMaxNGram = product;
					}

					if ( similarityQGram < minQGram ) {
						minQGram = similarityQGram;
						pMinQGram = product;
						
						KShingling kshingling = new KShingling(qgram.getK());
						kshingling.getProfile( productKey );
						p1 = kshingling.getDimension();
						
						kshingling = new KShingling(qgram.getK());
						kshingling.getProfile( listKey );
						p2 = kshingling.getDimension();
						
						double max = Math.max( p1, p2 );
						
						normalizedQGram = minQGram / max;
					}
					
					if ( similarityCosine > maxCosine ) {
						maxCosine = similarityCosine;
						pCosine = product;
					}
					
					if ( similarityLCS > maxLCS ) {
						maxLCS = similarityLCS;
						pLCS = product;
					}
					
					if ( similaritySorensen > maxSorensen ) {
						maxSorensen = similaritySorensen;
						pSorensen = product;
					}
				}

				System.out.println( String.format(
							"\n\nListing: '%s'"
							+ "\n Jaccard\n\tProduct: '%s'\n\tSimilarity: %f"
							+ "\n Jaro Winkler\n\tProduct: '%s'\n\tSimilarity: %f"
							+ "\n Levenshtein\n\tProduct: '%s'\n\tSimilarity: '%f'"
							+ "\n Normalized Levenshtein\n\tProduct: '%s'\n\tSimilarity: %f"
							+ "\n Damerau\n\tProduct: '%s'\n\tSimilarity: %f"
							+ "\n Min QGram\n\tProduct: '%s'\n\tSimilarity: %f\n\tP1: %f\n\tP2: %f\n\tNormalized: %f"
							+ "\n Max NGram\n\tProduct: '%s'\n\tSimilarity: %f"
							+ "\n Cosine\n\tProduct: '%s'\n\tSimilarity: %f"
							+ "\n LCS\n\tProduct: '%s'\n\tSimilarity: %f"
							+ "\n Sorensen\n\tProduct: '%s'\n\tSimilarity: %f",
							
							listKey,
							pJaccard, maxJaccard,
							pJW, maxJW,
							pLevenshtein, minLevenshtein,
							pNLevenshtein, minNLevenshtein,
							pDemarau, minDamerau,
							pMinQGram, minQGram, p1, p2, normalizedQGram,
							pMaxNGram, maxNGram,
							pCosine, maxCosine,
							pLCS, maxLCS,
							pSorensen, maxSorensen
					) );
				

				// if ( max >= 0.80 ) {
				// p.getListings().add( listing );
				// }
			}
		}

		// int matchers = 0;
		// List<String> result = new ArrayList<>( blockings.size() );
		// for ( Block block : blockings.values() ) {
		// for ( Product product : block.getProducts() ) {
		// Result r = new Result( product.getProductName(),
		// product.getListings() );
		// matchers += r.getListings().size();
		// result.add( gson.toJson( r ) );
		//
		// if ( r.getListings().size() > 0 ) {
		// System.out.println( gson.toJson( r ) );
		// }
		// }
		// }
		//
		// System.out.println( "Resultado: " + result.size() );
		// System.out.println( "Matchers: " + matchers );

		// File output = new File(
		// "/Users/andersonfarias/Documents/Workspace/matching/src/main/resources/result.json"
		// );
		//
		// FileUtils.writeLines( output, result );
	}
}
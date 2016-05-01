package farias.anderson.challenges.sortable.matching.matchers;

import farias.anderson.challenges.sortable.matching.Config;
import farias.anderson.challenges.sortable.matching.data.clean.StringCleaner;

/**
 * A implementation of a {@link StringMatcher} that uses a combination of others
 * {@link StringMatcher} to say whether a given listing's manufacturer matches
 * against a given product's manufacturer.
 * 
 * @see StringMatcher
 * @author Anderson Farias
 */
public class ManufacturerStringMatcher implements StringMatcher {

	/**
	 * Jaro Winkler string matcher instance
	 * 
	 * @see JaroWinklerStringMatcher
	 */
	private JaroWinklerStringMatcher jaroWinlkerMatcher;

	/**
	 * Damerau string matcher instance
	 * 
	 * @see DamerauStringMatcher
	 */
	private DamerauStringMatcher singleWordDamerauMatcher;

	/**
	 * Damerau string matcher instance
	 * 
	 * @see DamerauStringMatcher
	 */
	private DamerauStringMatcher composedWordDamerauMatcher;

	/**
	 * {@inheritDoc StringMatcher#matches(String, String)}
	 */
	@Override
	public boolean matches( String product, String listing ) {

		if ( singleWordDamerauMatcher.matches( product, listing ) )
			return true;

		String[] pieces = listing.split( " " );
		boolean listingManufacturerIsComposedWord = pieces.length > 1;

		if ( listingManufacturerIsComposedWord ) {

			for ( String piece : pieces )
				if ( composedWordDamerauMatcher.matches( product, StringCleaner.clean( piece ) ) )
					return true;

			if ( singleWordDamerauMatcher.matches( product, buildAcronym( pieces ) ) )
				return true;

		}

		return jaroWinlkerMatcher.matches( product, listing );
	}

	/**
	 * Build a acronym for the given pieces of a String. Example: give the
	 * pieces ["hewlet" , "packard"] it would return hp.
	 * 
	 * @param pieces
	 *            pieces of a string
	 * @return acronym for the given pieces of a String.
	 */
	private String buildAcronym( String[] pieces ) {
		StringBuilder acronym = new StringBuilder();

		for ( String piece : pieces ) {
			piece = StringCleaner.clean( piece );

			if ( piece.isEmpty() )
				continue;

			acronym.append( new Character( piece.charAt( 0 ) ).toString() );
		}

		return acronym.toString();
	}

	/**
	 * Default constructor
	 */
	public ManufacturerStringMatcher() {
		singleWordDamerauMatcher = new DamerauStringMatcher( Config.LEVENSHTEIN_SINGLE_WORD_THRESHOLD );
		composedWordDamerauMatcher = new DamerauStringMatcher( Config.LEVENSHTEIN_COMPOSED_WORDS_THRESHOLD );
		jaroWinlkerMatcher = new JaroWinklerStringMatcher( Config.JARO_WINKLER_THRESHOLD );
	}

}
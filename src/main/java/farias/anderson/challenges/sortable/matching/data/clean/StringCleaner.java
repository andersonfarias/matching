package farias.anderson.challenges.sortable.matching.data.clean;

/**
 * Utility class to perform some "clean" operations on String objects. By
 * "clean", I mean operations that remove special characters, unnecessary
 * white-spaces, convert the String to lower case and so on.
 * 
 * @author Anderson Farias
 */
public final class StringCleaner {

	/**
	 * Characters to replace for white-space
	 */
	private static final String CLEAR_PATTERN = "[_\\-,;:%^]";

	/**
	 * Remove special characters of the given string, convert it to lower case
	 * and remove unnecessary white-spaces
	 * 
	 * @param s
	 *            string to clean
	 * @return cleaned string
	 */
	public static final String clean( String s ) {
		return s.trim().toLowerCase().replaceAll( CLEAR_PATTERN, " " );
	}

	/**
	 * Private constructor
	 */
	private StringCleaner() {

	}
}
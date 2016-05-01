package farias.anderson.challenges.sortable.matching;

/**
 * Class that contains the configurations used in the application
 * 
 * @author Anderson Farias
 */
public class Config {

	/**
	 * Name of the file that contains the messages
	 */
	public static final String MESSAGES_FILE_NAME = "messages";

	/**
	 * Threshold for the levenshtein algorithm to consider a match
	 */
	public static final int LEVENSHTEIN_SINGLE_WORD_THRESHOLD = 2;

	/**
	 * Threshold for the levenshtein algorithm, when used in composed words, to
	 * consider a match
	 */
	public static final int LEVENSHTEIN_COMPOSED_WORDS_THRESHOLD = 1;

	/**
	 * Threshold for the Jaro Winkler algorithm, when used to compare two
	 * Strings, to consider a match
	 */
	public static final double JARO_WINKLER_THRESHOLD = 0.8;

	/**
	 * Threshold for the Jaro Winkler algorithm used to get the best match
	 */
	public static final double JARO_WINKLER_BEST_MATCHER_THRESHOLD = 90;

	/**
	 * Threshold for the QGram algorithm to consider a match
	 */
	public static final double QGRAM_BEST_MATCHER_THRESHOLD = 25;
}
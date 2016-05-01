package farias.anderson.challenges.sortable.matching.cli;

/**
 * Options used at the Command Line Interface (CLI)
 * 
 * @author Anderson Farias
 */
public enum Options {

	/**
	 * Products option
	 */
	PRODUCTS( "products", "products", "input file with the products", false ),

	/**
	 * Listings option
	 */
	LISTINGS( "listings", "listings", "input file with the listings", false ),

	/**
	 * Result option
	 */
	RESULT( "result", "result", "output file", false ),

	/**
	 * Help option
	 */
	HELP( "help", "help", "print the help", true );

	/**
	 * Flag to indicate if the option is a boolean value or not
	 */
	private final boolean bool;

	/**
	 * Name of the option
	 */
	private final String name;

	/**
	 * Argument of the option at the CLI
	 */
	private final String argName;

	/**
	 * Description of the option
	 */
	private final String description;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            option's name
	 * @param argName
	 *            option's argument name at the CLI
	 * @param description
	 *            option's description
	 * @param bool
	 *            whether the option is a boolean value or not
	 */
	private Options( String name, String argName, String description, boolean bool ) {
		this.bool = bool;
		this.name = name;
		this.argName = argName;
		this.description = description;
	}

	/**
	 * Returns the option's name
	 * 
	 * @return option's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the option's argument name
	 * 
	 * @return option's argument name
	 */
	public String getArgName() {
		return argName;
	}

	/**
	 * Returns the option's description
	 * 
	 * @return option's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns whether the option is a boolean value or not
	 * 
	 * @return whether the option is a boolean value or not
	 */
	public boolean isBool() {
		return bool;
	}

}
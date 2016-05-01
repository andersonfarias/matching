package farias.anderson.challenges.sortable.matching.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Domain object that represents the result of the challenge
 * 
 * @author Anderson Farias
 */
public class Result {

	/**
	 * Product's name
	 */
	private String productName;

	/**
	 * Product's listings
	 */
	private List<Listing> listings;

	/**
	 * Default constructor
	 */
	public Result() {

	}

	/**
	 * Constructor
	 * 
	 * @param productName
	 *            product's name
	 * @param listings
	 *            product's listings
	 */
	public Result( String productName, List<Listing> listings ) {
		super();
		this.productName = productName;
		this.listings = listings;
	}

	/**
	 * Returns the product's name
	 * 
	 * @return the product's name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the the product's name
	 * 
	 * @param productName
	 *            the product's name
	 */
	public void setProductName( String productName ) {
		this.productName = productName;
	}

	/**
	 * Returns the product's listings
	 * 
	 * @return the product's listings
	 */
	public List<Listing> getListings() {
		return listings;
	}

	/**
	 * Sets the product's listings
	 * 
	 * @param listings
	 *            the product's listings
	 */
	public void setListings( List<Listing> listings ) {
		this.listings = listings;
	}

	/**
	 * {@inheritDoc Object#hashCode()}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( listings == null ) ? 0 : listings.hashCode() );
		result = prime * result + ( ( productName == null ) ? 0 : productName.hashCode() );
		return result;
	}

	/**
	 * {@inheritDoc Object#equals(Object)}
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Result other = (Result) obj;
		if ( listings == null ) {
			if ( other.listings != null )
				return false;
		} else if ( !listings.equals( other.listings ) )
			return false;
		if ( productName == null ) {
			if ( other.productName != null )
				return false;
		} else if ( !productName.equals( other.productName ) )
			return false;
		return true;
	}

	/**
	 * {@inheritDoc Object#toString()}
	 */
	@Override
	public String toString() {
		return "Result [product=" + productName + ", listings=" + Arrays.deepToString( listings.toArray( new Listing[] {} ) ) + "]";
	}

}
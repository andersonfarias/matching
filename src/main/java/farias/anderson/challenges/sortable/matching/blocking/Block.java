package farias.anderson.challenges.sortable.matching.blocking;

import java.util.ArrayList;
import java.util.List;

import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;

/**
 * Represents one "group" or "block" of products and listings that belongs to
 * the same manufacturer
 * 
 * @author Anderson Farias
 */
public class Block {

	/**
	 * Listings
	 */
	List<Listing> listings;

	/**
	 * Products
	 */
	List<Product> products;

	/**
	 * Return the list of listings
	 * 
	 * @return list of listings
	 */
	public List<Listing> getListings() {
		return listings;
	}

	/**
	 * Sets the list of listings
	 * 
	 * @param listings
	 *            list of listings to set
	 */
	public void setListings( List<Listing> listings ) {
		this.listings = listings;
	}

	/**
	 * Return the list of products
	 * 
	 * @return list of products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Sets the list of products
	 * 
	 * @param products
	 *            list of products to set
	 */
	public void setProducts( List<Product> products ) {
		this.products = products;
	}

	/**
	 * {@inheritDoc Object#hashCode()}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( listings == null ) ? 0 : listings.hashCode() );
		result = prime * result + ( ( products == null ) ? 0 : products.hashCode() );
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
		Block other = (Block) obj;
		if ( listings == null ) {
			if ( other.listings != null )
				return false;
		} else if ( !listings.equals( other.listings ) )
			return false;
		if ( products == null ) {
			if ( other.products != null )
				return false;
		} else if ( !products.equals( other.products ) )
			return false;
		return true;
	}

	/**
	 * Default constructor
	 */
	public Block() {
		listings = new ArrayList<>();
		products = new ArrayList<>();
	}

}
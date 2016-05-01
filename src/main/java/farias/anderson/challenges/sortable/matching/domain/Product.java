package farias.anderson.challenges.sortable.matching.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Domain object that represents one product
 * 
 * @author Anderson Farias
 */
public class Product {

	/**
	 * Name
	 */
	private String productName;

	/**
	 * Manufacturer
	 */
	private String manufacturer;

	/**
	 * Model
	 */
	private String model;

	/**
	 * Family
	 */
	private String family;

	/**
	 * Announced Date
	 */
	@SerializedName( "announced-date" )
	private String announcedDate;

	/**
	 * Listings
	 */
	private List<Listing> listings;

	/**
	 * Default constructor
	 */
	public Product() {
		this.listings = new ArrayList<>();
	}

	/**
	 * Returns the name
	 * 
	 * @return the name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the name
	 * 
	 * @param productName
	 *            the name
	 */
	public void setProductName( String productName ) {
		this.productName = productName;
	}

	/**
	 * Returns the manufacturer
	 * 
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer
	 * 
	 * @param manufacturer
	 *            the manufacturer
	 */
	public void setManufacturer( String manufacturer ) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Returns the model
	 * 
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model
	 * 
	 * @param model
	 *            the model
	 */
	public void setModel( String model ) {
		this.model = model;
	}

	/**
	 * Returns the family
	 * 
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * Sets the family
	 * 
	 * @param family
	 *            the family
	 */
	public void setFamily( String family ) {
		this.family = family;
	}

	/**
	 * Returns the announced date
	 * 
	 * @return the announced date
	 */
	public String getAnnouncedDate() {
		return announcedDate;
	}

	/**
	 * Sets the announced date
	 * 
	 * @param announcedDate
	 *            the announced date
	 */
	public void setAnnouncedDate( String announcedDate ) {
		this.announcedDate = announcedDate;
	}

	/**
	 * Returns the listings
	 * 
	 * @return the listings
	 */
	public List<Listing> getListings() {
		return listings;
	}

	/**
	 * Sets the listings
	 * 
	 * @param listings
	 *            the listings
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
		result = prime * result + ( ( announcedDate == null ) ? 0 : announcedDate.hashCode() );
		result = prime * result + ( ( family == null ) ? 0 : family.hashCode() );
		result = prime * result + ( ( listings == null ) ? 0 : listings.hashCode() );
		result = prime * result + ( ( manufacturer == null ) ? 0 : manufacturer.hashCode() );
		result = prime * result + ( ( model == null ) ? 0 : model.hashCode() );
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
		Product other = (Product) obj;
		if ( announcedDate == null ) {
			if ( other.announcedDate != null )
				return false;
		} else if ( !announcedDate.equals( other.announcedDate ) )
			return false;
		if ( family == null ) {
			if ( other.family != null )
				return false;
		} else if ( !family.equals( other.family ) )
			return false;
		if ( listings == null ) {
			if ( other.listings != null )
				return false;
		} else if ( !listings.equals( other.listings ) )
			return false;
		if ( manufacturer == null ) {
			if ( other.manufacturer != null )
				return false;
		} else if ( !manufacturer.equals( other.manufacturer ) )
			return false;
		if ( model == null ) {
			if ( other.model != null )
				return false;
		} else if ( !model.equals( other.model ) )
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
		return "Product [name=" + productName + ", manufacturer=" + manufacturer + ", model=" + model + ", family=" + family + ", announcedDate=" + announcedDate + "]";
	}

}
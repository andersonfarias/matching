package farias.anderson.challenges.sortable.matching.domain;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String productName;

	private String manufacturer;

	private String model;

	private String family;

	private String announcedDate;

	private List<Listing> listings;

	public Product() {
		this.listings = new ArrayList<>();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName( String productName ) {
		this.productName = productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer( String manufacturer ) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel( String model ) {
		this.model = model;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily( String family ) {
		this.family = family;
	}

	public String getAnnouncedDate() {
		return announcedDate;
	}

	public void setAnnouncedDate( String announcedDate ) {
		this.announcedDate = announcedDate;
	}

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings( List<Listing> listings ) {
		this.listings = listings;
	}

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

	@Override
	public String toString() {
		return "Product [name=" + productName + ", manufacturer=" + manufacturer + ", model=" + model + ", family=" + family + ", announcedDate=" + announcedDate + "]";
	}

}
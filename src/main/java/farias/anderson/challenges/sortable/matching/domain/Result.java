package farias.anderson.challenges.sortable.matching.domain;

import java.util.Arrays;
import java.util.List;

public class Result {

	private String productName;

	private List<Listing> listings;

	public Result() {

	}

	public Result( String productName, List<Listing> listings ) {
		super();
		this.productName = productName;
		this.listings = listings;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName( String productName ) {
		this.productName = productName;
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
		result = prime * result + ( ( listings == null ) ? 0 : listings.hashCode() );
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

	@Override
	public String toString() {
		return "Result [product=" + productName + ", listings=" + Arrays.deepToString( listings.toArray( new Listing[] {} ) ) + "]";
	}

}
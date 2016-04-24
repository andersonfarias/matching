package farias.anderson.challenges.sortable.matching.datastructure;

import java.util.ArrayList;
import java.util.List;

import farias.anderson.challenges.sortable.matching.domain.Listing;
import farias.anderson.challenges.sortable.matching.domain.Product;

public class Block {

	List<Listing> listings;

	List<Product> products;

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings( List<Listing> listings ) {
		this.listings = listings;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts( List<Product> products ) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( listings == null ) ? 0 : listings.hashCode() );
		result = prime * result + ( ( products == null ) ? 0 : products.hashCode() );
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

	public Block() {
		listings = new ArrayList<>();
		products = new ArrayList<>();
	}

}
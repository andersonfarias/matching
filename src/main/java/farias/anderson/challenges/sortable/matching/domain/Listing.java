package farias.anderson.challenges.sortable.matching.domain;

public class Listing {

	private String title;

	private String manufacturer;

	private String currency;

	private String price;

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer( String manufacturer ) {
		this.manufacturer = manufacturer;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency( String currency ) {
		this.currency = currency;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice( String price ) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( currency == null ) ? 0 : currency.hashCode() );
		result = prime * result + ( ( manufacturer == null ) ? 0 : manufacturer.hashCode() );
		result = prime * result + ( ( price == null ) ? 0 : price.hashCode() );
		result = prime * result + ( ( title == null ) ? 0 : title.hashCode() );
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
		Listing other = (Listing) obj;
		if ( currency == null ) {
			if ( other.currency != null )
				return false;
		} else if ( !currency.equals( other.currency ) )
			return false;
		if ( manufacturer == null ) {
			if ( other.manufacturer != null )
				return false;
		} else if ( !manufacturer.equals( other.manufacturer ) )
			return false;
		if ( price == null ) {
			if ( other.price != null )
				return false;
		} else if ( !price.equals( other.price ) )
			return false;
		if ( title == null ) {
			if ( other.title != null )
				return false;
		} else if ( !title.equals( other.title ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Listing [title=" + title + ", manufacturer=" + manufacturer + ", currency=" + currency + ", price=" + price + "]";
	}

}
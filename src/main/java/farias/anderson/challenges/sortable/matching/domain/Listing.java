package farias.anderson.challenges.sortable.matching.domain;

/**
 * Domain object that represents one listing for a product
 * 
 * @author Anderson Farias
 */
public class Listing {

	/**
	 * Title
	 */
	private String title;

	/**
	 * Manufacturer
	 */
	private String manufacturer;

	/**
	 * Currency
	 */
	private String currency;

	/**
	 * Price
	 */
	private String price;

	/**
	 * Returns the title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title
	 * 
	 * @param title
	 *            the title
	 */
	public void setTitle( String title ) {
		this.title = title;
	}

	/**
	 * Return the manufacturer
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
	 * Returns the currency
	 * 
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency
	 * 
	 * @param currency
	 *            the currency
	 */
	public void setCurrency( String currency ) {
		this.currency = currency;
	}

	/**
	 * Gets the price
	 * 
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price
	 * 
	 * @param price
	 *            the price
	 */
	public void setPrice( String price ) {
		this.price = price;
	}

	/**
	 * {@inheritDoc Object#hashCode()}
	 */
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

	/**
	 * {@inheritDoc Object#toString()}
	 */
	@Override
	public String toString() {
		return "Listing [title=" + title + ", manufacturer=" + manufacturer + ", currency=" + currency + ", price=" + price + "]";
	}

}
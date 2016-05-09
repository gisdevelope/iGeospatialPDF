package geo;

/**
 * Abstract class used as parent class for all geometries.
 * 
 * @author DaGri
 * @since 02.05.2016
 *
 */
public class Geometry {

	// ATTRIBUTES

	/**
	 * The type of this {@link Geometry} as {@link String}.
	 */
	private String geometryType;
	
	// CONSTRUCTORS

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the geometry type of this {@link Geometry}.
	 *
	 * @return the geometryType
	 */
	public String getGeometryType() {
		return geometryType;
	}

	/**
	 * Sets the geometry type of this {@link Geometry}.
	 *
	 * @param geometryType the geometryType to set
	 */
	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}



	// OTHERS
}

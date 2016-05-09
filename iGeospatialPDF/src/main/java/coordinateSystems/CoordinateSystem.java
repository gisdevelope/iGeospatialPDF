package coordinateSystems;

/**
 * Abstract parental class that represents a coordinate system. This class
 * provides a WKT-string as {@link String}, a EPSG-Code as {@link Integer} and a
 * description as {@link String}.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public abstract class CoordinateSystem {

	// ATTRIBUTES

	/**
	 * The WKT String of this {@link CoordinateSystem}.
	 */
	private String WKT;

	/**
	 * The EPSG-code of this {@link CoordinateSystem}.
	 */
	private int EPSG;

	/**
	 * A description of this {@link CoordinateSystem}.
	 */
	private String description;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link CoordinateSystem} using a {@link String} for the
	 * WKT-String and a {@link String} for the EPSG-Code.
	 * 
	 * @param wKT
	 *            the WKT-String to set
	 * @param ePSG
	 *            the EPSG-Code to set
	 */
	public CoordinateSystem() {
		super();
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the WKT-String of this {@link CoordinateSystem} as {@link String}
	 * .
	 *
	 * @return the wKT
	 */
	public String getWKT() {
		return WKT;
	}

	/**
	 * Sets the WKT-String of this {@link CoordinateSystem}.
	 *
	 * @param wKT
	 *            the wKT to set
	 */
	public void setWKT(String wKT) {
		WKT = wKT;
	}

	/**
	 * Returns the EPSG-code of this {@link CoordinateSystem} as {@link Integer}.
	 *
	 * @return the ePSG
	 */
	public int getEPSG() {
		return EPSG;
	}

	/**
	 * Sets the EPSG-code of this {@link CoordinateSystem}.
	 *
	 * @param epsg
	 *            the epsg to set
	 */
	public void setEPSG(int epsg) {
		EPSG = epsg;
	}

	/**
	 * Returns the description of this {@link CoordinateSystem} as
	 * {@link String}.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of this {@link CoordinateSystem}.
	 *
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	// OTHERS
}

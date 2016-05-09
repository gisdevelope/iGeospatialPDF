package geo;

import coordinateSystems.CoordinateSystem;

/**
 * Class to represent a point, specified by a coordinate pair, on the surface of
 * the earth.
 * 
 * @author DaGri
 * @since 02.05.2016
 *
 */
public class Point2D extends Geometry {

	// ATTRIBUTES

	/**
	 * The latitude (geographic X, mathematics Y) value of this {@link Point2D}.
	 */
	private double lat;

	/**
	 * The longitude (geographic Y, mathematics X) value of this {@link Point2D}
	 * .
	 */
	private double lon;

	/**
	 * The {@link CoordinateSystem} used by this {@link Point2D}.
	 */
	private CoordinateSystem coordSystem;

	// CONSTRUCTORS

	@Deprecated
	/**
	 * Constructor for a {@link Point2D} using double values for the X- and
	 * Y-component.
	 * 
	 * @param lat
	 *            the latitude (geographic X, mathematics Y) value to set
	 * @param lon
	 *            the longitude (geographic Y, mathematics X) value to set
	 */
	public Point2D(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.setGeometryType("Point2D");
	}

	/**
	 * Constructor for a {@link Point2D} using double values for the X- and
	 * Y-component and a {@link CoordinateSystem} for the used system.
	 * 
	 * @param lat
	 *            the latitude (geographic X, mathematics Y) value to set
	 * @param lon
	 *            the longitude (geographic Y, mathematics X) value to set
	 * @param s
	 *            the {@link CoordinateSystem} to set
	 */
	public Point2D(double lat, double lon, CoordinateSystem s) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.coordSystem = s;
		this.setGeometryType("Point2D");
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the latitude (geographic X, mathematics Y) value of this
	 * {@link Point2D}.
	 *
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * Sets the latitude (geographic X, mathematics Y) value of this
	 * {@link Point2D}.
	 *
	 * @param lat
	 *            the latitude to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Returns the longitude (geographic Y, mathematics X) value of this
	 * {@link Point2D}.
	 *
	 * @return the longitude
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * Sets the longitude (geographic Y, mathematics X) value of this
	 * {@link Point2D}.
	 *
	 * @param lon
	 *            the longitude to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * Returns the {@link CoordinateSystem} used by this {@link Point2D}.
	 *
	 * @return the coordSystem
	 */
	public CoordinateSystem getCoordSystem() {
		return coordSystem;
	}

	/**
	 * Sets the {@link CoordinateSystem} used by this {@link Point2D}.
	 *
	 * @param coordSystem
	 *            the coordSystem to set
	 */
	public void setCoordSystem(CoordinateSystem coordSystem) {
		this.coordSystem = coordSystem;
	}

	// OTHERS
}

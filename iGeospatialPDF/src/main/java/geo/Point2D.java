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
	 * The northing (geographic X, mathematics Y) value of this {@link Point2D}.
	 */
	private double northing;

	/**
	 * The easting (geographic Y, mathematics X) value of this {@link Point2D}
	 * .
	 */
	private double easting;

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
	 * @param northing
	 *            the northing (geographic X, mathematics Y) value to set
	 * @param easting
	 *            the easting (geographic Y, mathematics X) value to set
	 */
	public Point2D(double northing, double easting) {
		super();
		this.northing = northing;
		this.easting = easting;
		this.setGeometryType("Point2D");
	}

	/**
	 * Constructor for a {@link Point2D} using double values for the X- and
	 * Y-component and a {@link CoordinateSystem} for the used system.
	 * 
	 * @param northing
	 *            the northing (geographic X, mathematics Y) value to set
	 * @param easting
	 *            the easting (geographic Y, mathematics X) value to set
	 * @param s
	 *            the {@link CoordinateSystem} to set
	 */
	public Point2D(double northing, double easting, CoordinateSystem s) {
		super();
		this.northing = northing;
		this.easting = easting;
		this.coordSystem = s;
		this.setGeometryType("Point2D");
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the northing (geographic X, mathematics Y) value of this
	 * {@link Point2D}.
	 *
	 * @return the northing
	 */
	public double getNorthing() {
		return northing;
	}

	/**
	 * Sets the northing (geographic X, mathematics Y) value of this
	 * {@link Point2D}.
	 *
	 * @param northing
	 *            the northing to set
	 */
	public void setNorthing(double northing) {
		this.northing = northing;
	}

	/**
	 * Returns the easting (geographic Y, mathematics X) value of this
	 * {@link Point2D}.
	 *
	 * @return the easting
	 */
	public double getEasting() {
		return easting;
	}

	/**
	 * Sets the easting (geographic Y, mathematics X) value of this
	 * {@link Point2D}.
	 *
	 * @param easting
	 *            the easting to set
	 */
	public void setEasting(double easting) {
		this.easting = easting;
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

package geo;

import java.util.ArrayList;

/**
 * Class to represent a polygon, specified by an {@link ArrayList} of
 * {@link Point2D}, on the surface of the earth.
 * 
 * @author DaGri
 * @since 02.05.2016
 */
public class Polygon extends Geometry {

	// ATTRIBUTES

	/**
	 * The {@link ArrayList} of {@link Point2D} used as base points for this
	 * {@link Polygon}.
	 */
	private ArrayList<Point2D> points = new ArrayList<>();

	/**
	 * The geographical length of this {@link Polygon} as {@link Double}.
	 */
	private double geoLength = 0.0;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link Polygon} using an {@link ArrayList} of
	 * {@link Point2D}s.
	 * 
	 * @param points
	 *            the points of the {@link Polygon}
	 */
	public Polygon(ArrayList<Point2D> points) {
		super();
		this.points = points;
		this.calcGeoLength();
	}

	// METHODS

	/**
	 * Calculates the length of this {@link Polygon}.
	 */
	private void calcGeoLength() {
		this.setGeoLength(new GeoCalculator().calcPolygonLength(this));
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the the {@link ArrayList} of {@link Point2D}s.
	 *
	 * @return the points
	 */
	public ArrayList<Point2D> getPoints() {
		return points;
	}

	/**
	 * Sets the {@link ArrayList} of {@link Point2D}.
	 *
	 * @param points
	 *            the points to set
	 */
	public void setPoints(ArrayList<Point2D> points) {
		this.points = points;
		this.calcGeoLength();
	}

	/**
	 * Returns the geographical length of this {@link Polygon} in meters.
	 *
	 * @return the geoLength as {@link Double} in meters
	 */
	public double getGeoLength() {
		return geoLength;
	}

	/**
	 * Sets the geographical length of this {@link Polygon}.
	 *
	 * @param geoLength
	 *            the geoLength to set as {@link Double} in meters
	 */
	public void setGeoLength(double geoLength) {
		this.geoLength = geoLength;
	}

	// OTHERS
}

package geo;

import java.util.ArrayList;

/**
 * Class to represent a linear object on the surface of the earth.
 * 
 * @author DaGri
 * @since 02.05.2016
 *
 */
public class LineString extends Geometry {

	// ATTRIBUTES

	/**
	 * The {@link ArrayList} of {@link Point2D} used as base points for this
	 * {@link LineString}.
	 */
	private ArrayList<Point2D> points = new ArrayList<>();

	/**
	 * The geographical length of this {@link LineString} as {@link Double}.
	 */
	private double geoLength = 0.0;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link LineString} using an {@link ArrayList} of
	 * {@link Point2D}s.
	 * 
	 * @param points
	 *            the points of the {@link LineString}
	 */
	public LineString(ArrayList<Point2D> points) {
		super();
		this.points = points;
		this.calcGeoLength();
		this.setGeometryType("LineString");
	}

	// METHODS

	/**
	 * Calculates the lenght of this {@link LineString}.
	 */
	private void calcGeoLength() {
		this.setGeoLength(new GeoCalculator().calcLineStringLength(this));
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
	 * Returns the geographical length of this {@link LineString} in meters.
	 *
	 * @return the geoLength as {@link Double} in meters
	 */
	public double getGeoLength() {
		return geoLength;
	}

	/**
	 * Sets the geographical length of this {@link LineString}.
	 *
	 * @param geoLength
	 *            the geoLength to set as {@link Double} in meters
	 */
	public void setGeoLength(double geoLength) {
		this.geoLength = geoLength;
	}

	// OTHERS
}

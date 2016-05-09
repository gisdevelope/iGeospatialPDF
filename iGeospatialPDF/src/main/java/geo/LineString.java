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

	private ArrayList<Point2D> points = new ArrayList<>();

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
	}

	// METHODS

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
	}

	// OTHERS
}

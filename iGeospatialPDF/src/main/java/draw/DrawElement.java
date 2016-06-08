package draw;

import draw.geo.DrawPoint;
import geo.Point2D;
import resources.PdfCoordinate;

/**
 * Abstract parental class for all draw-able elements that provides functions
 * and attributes for all child-classes.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public abstract class DrawElement {

	// ATTRIBUTES

	// CONSTRUCTORS

	// METHODS

	/**
	 * Reduces the {@link PdfCoordinate}s of the {@link DrawElement} about the
	 * given values in northing and easting.
	 *
	 * @param northing
	 *            the northing to reduce in meters as {@link Double}
	 * @param easting
	 *            the easting to reduce in meters as {@link Double}
	 */
	public abstract void reduce(double northing, double easting);

	/**
	 * Turns the {@link PdfCoordinate}s of the {@link DrawElement} about the
	 * given angle.
	 *
	 * @param angle
	 *            the angle to turn about in radiant
	 */
	public abstract void turn(double angle);

	/**
	 * Scales the {@link PdfCoordinate}s of the {@link DrawElement} by the given
	 * factor.
	 *
	 * @param factor
	 *            the factor to scale about as {@link Double}
	 */
	public abstract void scale(double factor);

	/**
	 * Converts the {@link Point2D}s of the internal stored elements to the
	 * internal {@link DrawPoint}s.
	 */
	public abstract void convertToPdfSystem();

	// GETTERS AND SETTERS

	// OTHERS
}

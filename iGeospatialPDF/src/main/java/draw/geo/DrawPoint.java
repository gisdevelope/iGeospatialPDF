package draw.geo;

import draw.DrawElement;
import draw.Icon;
import geo.Point2D;

/**
 * Class to represent a PDF-printable {@link Point2D}. This class extends the
 * {@link DrawElement} and owns a {@link Point2D} that provides the geographical
 * component.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class DrawPoint extends DrawElement {

	// ATTRIBUTES

	/**
	 * The {@link Point2D} of this {@link DrawElement}.
	 */
	private Point2D point;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link DrawPoint} using a {@link Icon} to display and a
	 * {@link Point2D} for the geographic component.
	 * 
	 * @param icon
	 *            the {@link Icon} to set
	 * @param point
	 *            the {@link Point2D} to set
	 */
	@Deprecated
	public DrawPoint(Icon icon, Point2D point) {
		super();
		// TODO : ICON GESONDERT BEHANDELN?
		this.point = point;
	}

	/**
	 * Constructor for a {@link DrawPoint} using a {@link Point2D} for the
	 * geographic component.
	 * 
	 * @param point
	 *            the {@link Point2D} to set
	 */
	public DrawPoint(Point2D point) {
		super();
		this.point = point;
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link Point2D} of this {@link DrawPoint}.
	 *
	 * @return the point as {@link DrawPoint}
	 */
	public Point2D getPoint() {
		return point;
	}

	/**
	 * Sets the {@link Point2D} of this {@link DrawPoint}.
	 *
	 * @param point
	 *            the point to set
	 */
	public void setPoint(Point2D point) {
		this.point = point;
	}

	// OTHERS

}

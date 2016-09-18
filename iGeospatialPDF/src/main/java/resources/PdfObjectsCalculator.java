package resources;

import java.util.logging.Logger;

import draw.geo.DrawPoint;
import draw.geo.DrawPolygon;
import geo.BoundingBox;
import geo.Point2D;
import geo.Polygon;

/**
 * Class to TODO
 * 
 * @author DaGri
 * @since 24.06.2016
 *
 */
public class PdfObjectsCalculator {

	// ATTRIBUTES

	/**
	 * The {@link Logger} to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	public PdfObjectsCalculator() {
	}

	// METHODS

	public boolean pointInPolygon(Point2D point, Polygon polygon) {
		// TODO NOT YET IMPLEMENTED
		return false;
	}

	public boolean drawPointInDrawPolygon(DrawPoint point, DrawPolygon polygon) {
		// TODO NOT YET IMPLEMENTED
		return false;
	}

	/**
	 * Checks if a {@link Point2D} is inside the {@link BoundingBox}.
	 *
	 * @param point
	 *            the {@link Point2D}
	 * @param bBox
	 *            the {@link BoundingBox}
	 * @return <code>true</code> if it is inside, <code>false</code> if not
	 */
	public boolean pointInBoundingbox(Point2D point, BoundingBox bBox) {
		// IF POINT IS RIGHT ABOVE THE LOWER LEFT CORNER
		if (point.getEasting() >= bBox.getDownLeft().getEasting()
				&& point.getNorthing() >= bBox.getDownLeft().getNorthing()) {
			// IF POINT IS LEFT BELOW THE UPPER RIGHT CORNER
			if (point.getEasting() <= bBox.getUpRight().getEasting()
					&& point.getNorthing() <= bBox.getUpRight().getNorthing()) {
				// THE POINT IS INSIDE
				return true;
			}
		}
		// ELSE RETURN FALSE
		return false;
	}

	// TODO : DUPLICATED CODE?
	/**
	 * Checks if the northing and easting value of a {@link DrawPoint} are
	 * between 0 and the values width and height.
	 *
	 * @param point
	 *            the {@link DrawPoint}
	 * @param width
	 *            the width as {@link Float}
	 * @param height
	 *            the height as {@link Float}
	 * @return <code>true</code> if it is inside, <code>false</code> if not
	 */
	public boolean drawPointInArea(DrawPoint point, float width, float height) {
		// IF THE POINT IS IN X DIRECTION BETWEEN 0 AND THE IMAGE WIDTH
		if (point.getPdfCoord().getX() >= 0 && point.getPdfCoord().getX() <= width)
			// IF THE POINT IS IN Y DIRECTION BETWEEN 0 AND THE IMAGE HEIGHT
			if (point.getPdfCoord().getY() >= 0 && point.getPdfCoord().getY() <= height)
				// POINT IS ON THE IMAGE
				return true;
		// ELSE POINT IS NOT ON THE IMAGE
		return false;
	}

	// GETTERS AND SETTERS

	// OTHERS
}

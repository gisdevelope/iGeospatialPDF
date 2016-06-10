package resources;

import java.util.logging.Logger;

/**
 * Class to provide support at various {@link PdfCoordinate} calculations.
 * 
 * @author DaGri
 * @since 08.06.2016
 *
 */
public class PdfCoordinateCalculator {

	// ATTRIBUTES

	/**
	 * The instance of this {@link PdfCoordinateCalculator}.
	 */
	@SuppressWarnings("unused")
	private static PdfCoordinateCalculator instance;

	/**
	 * The {@link Logger} used to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Empty, private constuctor for a {@link PdfCoordinateCalculator}.
	 */
	private PdfCoordinateCalculator() {

	}

	// METHODS

	/**
	 * Returns an instance of this {@link PdfCoordinateCalculator}.
	 *
	 * @return a {@link PdfCoordinateCalculator}
	 */
	public static PdfCoordinateCalculator getInstance() {
		if (getInstance() == null) {
			setInstance(new PdfCoordinateCalculator());
			return getInstance();
		} else
			return getInstance();
	}

	/**
	 * Scales a {@link PdfCoordinate} by the given factor and returns it.s
	 *
	 * @param coord
	 *            the {@link PdfCoordinate} to scale
	 * @param factor
	 *            the factor to scale about
	 * @return the given {@link PdfCoordinate}
	 */
	public PdfCoordinate scale(PdfCoordinate coord, double factor) {
		coord.setX((float) (coord.getX() * factor));
		coord.setY((float) (coord.getY() * factor));
		return coord;
	}

	/**
	 * Turns an {@link PdfCoordinate} by the given value in radiant around the
	 * specified turning center xc, yc and returns it.
	 *
	 * @param coord
	 *            the {@link PdfCoordinate} to turn
	 * @param rad
	 *            the angle in radiant to turn about
	 * @param turnEasting
	 *            the x value of the point to turn around
	 * @param turnNorthing
	 *            the y value of the point to turn around
	 * @return coord the turned {@link PdfCoordinate}
	 */
	public PdfCoordinate turn(PdfCoordinate coord, double turnNorthing, double turnEasting, double rad) {
		double angle = rad + Math.atan2(coord.getY() - turnNorthing, coord.getX() - turnEasting);
		double distance = Math.sqrt((coord.getX() - turnEasting) * (coord.getX() - turnEasting)
				+ (coord.getY() - turnNorthing) * (coord.getY() - turnNorthing));
		coord.setX((float) (turnEasting + distance * Math.cos(angle)));
		coord.setY((float) (turnNorthing + distance * Math.sin(angle)));
		return coord;
	}

	/**
	 * Reduces the {@link PdfCoordinate} with the northing and easting value and
	 * then returns it.
	 *
	 * @param coord
	 *            the {@link PdfCoordinate} to reduce
	 * @param northing
	 *            the northing to redurce
	 * @param easting
	 *            the easting to reduce
	 * @return coord the reduced {@link PdfCoordinate}
	 */
	public PdfCoordinate redurce(PdfCoordinate coord, double northing, double easting) {
		// REDURCE THE X BY THE GIVEN EASTING
		coord.setX((float) (coord.getX() - easting));
		// REDURCE THE Y BY THE GIVEN NORTHING
		coord.setY((float) (coord.getY() - northing));
		return coord;
	}

	// GETTERS AND SETTERS

	/**
	 * Sets the instance of this {@link PdfCoordinateCalculator}.
	 *
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(PdfCoordinateCalculator inst) {
		instance = inst;
	}

	// OTHERS
}

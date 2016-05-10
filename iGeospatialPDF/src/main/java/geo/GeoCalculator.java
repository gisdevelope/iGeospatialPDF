package geo;

import coordinateSystems.EPSG25832;

/**
 * Class to provide various geographical calculations.
 * 
 * @author DaGri
 * @since 06.05.2016
 */
public class GeoCalculator {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Empty constructor for a {@link GeoCalculator}.
	 * 
	 */
	public GeoCalculator() {
		// NICHTS
	}

	// METHODS

	/**
	 * Calculates the geographic distance between two {@link Point2D}s in meters
	 * and returns it as a {@link Double} value.
	 *
	 * @param p1
	 *            : the first {@link Point2D}
	 * @param p2
	 *            : the second {@link Point2D}
	 * @return distance : the distance in meters as {@link Double}
	 */
	public double calcDistance(Point2D p1, Point2D p2) {
		double erg = 0.0;
		// EPSG 25832
		if (p1.getCoordSystem().getClass() == EPSG25832.class) {
			// PYTHAGORAS
			erg = Math.sqrt((p1.getLat() - p2.getLat()) * (p1.getLat() - p2.getLat())
					+ (p1.getLon() - p2.getLon()) * (p1.getLon() - p2.getLon()));
		}
		return erg;
	}

	/**
	 * Calculates the length of a {@link LineString} as {@link Double}.
	 *
	 * @param lineString
	 *            the {@link LineString} to calculate the length of
	 * @return length the length of the {@link LineString} as {@link Double}
	 */
	public double calcLineStringLength(LineString lineString) {
		double erg = 0.0;
		for (int a = 0; a < lineString.getPoints().size() - 1; a++) {
			erg = erg + this.calcDistance(lineString.getPoints().get(a), lineString.getPoints().get(a + 1));
		}
		return erg;
	}

	// GETTERS AND SETTERS

	// OTHERS
}

package tests;

import coordinateSystems.EPSG25832;
import geo.Point2D;

/**
 * Class to test {@link Point2D} behaviour.
 * 
 * @author DaGri
 * @since 21.05.2016
 *
 */
public class PointTestClass {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		double easting = 374300.0;
		double northing = 5712457.0;

		Point2D point = new Point2D(northing, easting, new EPSG25832());
		Point2D point2 = new Point2D(northing + 100, easting, new EPSG25832());
		System.out.println("DONE!");

	}
}

package tests;

import coordinateSystems.EPSG25832;
import geo.BoundingBox;
import geo.Point2D;

/**
 * Class to test new features of a {@link BoundingBox}.
 * 
 * @author DaGri
 * @since 21.05.2016
 */
public class BoundingBoxTest {
 
	public static void main(String[] args) {
		BoundingBox bbox = new BoundingBox(new Point2D(0, 0, new EPSG25832()), new Point2D(100, 100, new EPSG25832()),
				new EPSG25832());
		
		System.out.println(bbox.getDownLeft().getNorthing());
		System.out.println(bbox.getDownLeft().getEasting());
		System.out.println(bbox.getUpRight().getNorthing());
		System.out.println(bbox.getUpRight().getEasting());
		System.out.println("");
		
		// TEST ONE : TO THE RIGHT
		BoundingBox testBox = bbox.getBBoxRight(100);
		System.out.println(testBox.getDownLeft().getNorthing());
		System.out.println(testBox.getDownLeft().getEasting());
		System.out.println(testBox.getUpRight().getNorthing());
		System.out.println(testBox.getUpRight().getEasting());
		// WORKS
		System.out.println("");
		// TEST TWO : TO THE RIGHT
		testBox = bbox.getBBoxLeft(100);
		System.out.println(testBox.getDownLeft().getNorthing());
		System.out.println(testBox.getDownLeft().getEasting());
		System.out.println(testBox.getUpRight().getNorthing());
		System.out.println(testBox.getUpRight().getEasting());
		// WORKS
		System.out.println("");
		// TEST TWO : TO THE RIGHT
		testBox = bbox.getBBoxAbove(100);
		System.out.println(testBox.getDownLeft().getNorthing());
		System.out.println(testBox.getDownLeft().getEasting());
		System.out.println(testBox.getUpRight().getNorthing());
		System.out.println(testBox.getUpRight().getEasting());
		// WORKS
		System.out.println("");
		// TEST TWO : TO THE RIGHT
		testBox = bbox.getBBoxBelow(100);
		System.out.println(testBox.getDownLeft().getNorthing());
		System.out.println(testBox.getDownLeft().getEasting());
		System.out.println(testBox.getUpRight().getNorthing());
		System.out.println(testBox.getUpRight().getEasting());
		// WORKS
	}
}

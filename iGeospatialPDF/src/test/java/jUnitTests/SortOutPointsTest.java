package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import resources.PdfCoordinate;
import resources.PdfCoordinateCalculator;

/**
 * Tests the method {@link PdfCoordinateCalculator}.pdfCoordinateInArea(...).
 * 
 * @author DaGri
 * @since 12.07.2016
 *
 */
public class SortOutPointsTest {

	@Test
	public void test() {
		float width = 100f;
		float height = 100f;

		// INSIDE
		PdfCoordinate c1 = new PdfCoordinate(10f, 10f);
		PdfCoordinate c2 = new PdfCoordinate(90f, 10f);
		PdfCoordinate c3 = new PdfCoordinate(10f, 90f);
		PdfCoordinate c4 = new PdfCoordinate(90f, 90f);

		// CORNERS
		PdfCoordinate c5 = new PdfCoordinate(0f, 0f);
		PdfCoordinate c6 = new PdfCoordinate(100f, 0f);
		PdfCoordinate c7 = new PdfCoordinate(100f, 100f);
		PdfCoordinate c8 = new PdfCoordinate(0f, 100f);

		// OUTISDE
		PdfCoordinate c9 = new PdfCoordinate(50f, -10f);
		PdfCoordinate c10 = new PdfCoordinate(110f, 50f);
		PdfCoordinate c11 = new PdfCoordinate(50f, 110f);
		PdfCoordinate c12 = new PdfCoordinate(-10f, 50f);

		ArrayList<PdfCoordinate> coords = new ArrayList<>();
		coords.add(c1);
		coords.add(c2);
		coords.add(c3);
		coords.add(c4);
		coords.add(c5);
		coords.add(c6);
		coords.add(c7);
		coords.add(c8);
		coords.add(c9);
		coords.add(c10);
		coords.add(c11);
		coords.add(c12);

		PdfCoordinateCalculator calc = PdfCoordinateCalculator.getInstance();
		for (int a = 0; a < coords.size(); a++) {
			if (calc.pdfCoordinateInArea(coords.get(a), width, height) == false) {
				coords.remove(a);
				a--;
			}
		}

		System.out.println(coords.size() + "");
		assertEquals(coords.size(), 8);

	}
}

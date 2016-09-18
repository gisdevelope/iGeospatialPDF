package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import draw.geo.DrawLineString;
import geo.LineString;
import geo.Point2D;
import resources.PdfCoordinate;
import resources.PdfCoordinateCalculator;

/**
 * Class to test TODO 
 * 
 * TODO : NOT A REAL JUNIT TEST
 * 
 * @author DaGri
 * @since 26.07.2016
 *
 */
public class DrawLinestringsCutTest {

	@Test
	public void test1() {

		// SETUP TEST ENVIRONMENT
		float width = 100;
		float height = 100;
		
		boolean success = true;

		ArrayList<Point2D> emptyList = new ArrayList<>();

		PdfCoordinate c1 = new PdfCoordinate(50, 10);
		PdfCoordinate c2 = new PdfCoordinate(110, 10);

		LineString ls = new LineString(emptyList);

		DrawLineString dls = new DrawLineString(ls);
		dls.getPdfCoords().add(c1);
		dls.getPdfCoords().add(c2);

		// CREATE THE PDFCOORDINATECALCULATOR
		PdfCoordinateCalculator calc = PdfCoordinateCalculator.getInstance();

		// CALCULATION
		ArrayList<DrawLineString> newLines = calc.calcFittingDrawLinestrings(dls, width, height);

		if (newLines.get(0).getPdfCoords().size() != 2) {

			// PRINT OUT THE SIZE OF THE
			System.out.println("THERE ARE " + newLines.get(0).getPdfCoords().size() + "PDF COORDINATES");

			// PRINT OUT
			this.print(newLines);

			// TEST FAILS : NOT ENOUGHT PDFCOORDINATES INSIDE THE DRAWLINESTRING
			fail();

		} else if (newLines.get(0).getPdfCoords().size() == 2) {

			// PRINT OUT THE SIZE OF THE
			System.out.println("THERE ARE " + newLines.get(0).getPdfCoords().size() + " PDF COORDINATES");

			// PRINT OUT
			this.print(newLines);
			
			if(newLines.get(0).getPdfCoords().get(0).getX() != 50.0){
				success = false;
			}
			if(newLines.get(0).getPdfCoords().get(0).getY() != 10.0){
				success = false;
			}
			if(newLines.get(0).getPdfCoords().get(1).getX() != 100.0){
				success = false;
			}
			if(newLines.get(0).getPdfCoords().get(1).getY() != 10.0){
				success = false;
			}

			// CECK
			assertEquals(success, true);
		}
	}

	private void print(ArrayList<DrawLineString> strings) {
		// PRINT OUT THE COORDINATES-TUPELS OF THE STORED PDFCOORDINATES IN
		// THE NEWLINES LIST
		for (int a = 0; a < strings.get(0).getPdfCoords().size(); a++) {
			System.out.println("Coord NR.: " + a + "; X= " + strings.get(0).getPdfCoords().get(a).getX() + " ; Y= "
					+ strings.get(0).getPdfCoords().get(a).getY());
		}
	}
}

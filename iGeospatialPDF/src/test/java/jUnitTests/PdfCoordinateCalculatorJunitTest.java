package jUnitTests;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;

import coordinateSystems.EPSG25832;
import draw.geo.DrawPoint;
import geo.BoundingBox;
import geo.Point2D;
import resources.PdfObjectsCalculator;

/**
 * Class to TODO
 * 
 * @author DaGri
 * @since 24.06.2016
 *
 */
public class PdfCoordinateCalculatorJunitTest {

	// ATTRIBUTES
	
	BoundingBox bbox = new BoundingBox(new Point2D(0, 0, new EPSG25832()), new Point2D(100, 100, new EPSG25832()), new EPSG25832());
	
	Point2D pointInside = new Point2D(50, 50, new EPSG25832());
	
	Point2D pointOutside = new Point2D(120, 120, new EPSG25832());
	
	DrawPoint drawPoint = new DrawPoint(new Point2D(50, 50, new EPSG25832()));
	
	PdfObjectsCalculator calc = new PdfObjectsCalculator();
	
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	// METHODS

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#pointInPolygon(geo.Point2D, geo.Polygon)}
	 * .
	 */
	@Test
	public void testPointInPolygon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#drawPointInDrawPolygon(draw.geo.DrawPoint, draw.geo.DrawPolygon)}
	 * .
	 */
	@Test
	public void testDrawPointInDrawPolygon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#pointInBoundingbox(geo.Point2D, geo.BoundingBox)}
	 * .
	 */
	@Test
	public void testPointInBoundingbox() {
		assertTrue(calc.pointInBoundingbox(pointInside, bbox));
	}
	
	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#pointInBoundingbox(geo.Point2D, geo.BoundingBox)}
	 * .
	 */
	@Test
	public void testPointOutOfBoundingbox() {
		assertFalse(calc.pointInBoundingbox(pointOutside, bbox));
	}
	
	@Test
	public void drawPointInPage(){
		assertTrue(calc.drawPointInArea(drawPoint, 100, 100));
	}
	
	@Test
	public void drawPointOutOfPage(){
		assertFalse(calc.drawPointInArea(drawPoint, 20, 20));
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#drawPointInBoundingbox(draw.geo.DrawPoint, geo.BoundingBox)}
	 * .
	 */
	@Test
	public void testDrawPointInBoundingbox() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#calcLineIntersection(geo.Point2D, geo.Point2D, geo.Point2D, geo.Point2D)}
	 * .
	 */
	@Test
	public void testCalcLineIntersection() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#cutPolygonAtBBox(geo.Polygon, geo.BoundingBox)}
	 * .
	 */
	@Test
	public void testCutPolygonAtBBox() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#cutPolygonAtBbox(draw.geo.DrawPolygon, geo.BoundingBox)}
	 * .
	 */
	@Test
	public void testCutPolygonAtBbox() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#cutLinestringAtBBox(geo.Polygon, geo.BoundingBox)}
	 * .
	 */
	@Test
	public void testCutLinestringAtBBox() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link resources.PdfObjectsCalculator#cutLineStringAtBbox(draw.geo.DrawPolygon, geo.BoundingBox)}
	 * .
	 */
	@Test
	public void testCutLineStringAtBbox() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link resources.PdfObjectsCalculator#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	// GETTERS AND SETTERS

	// OTHERS
}

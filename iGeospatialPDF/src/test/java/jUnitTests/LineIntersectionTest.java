package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import resources.PdfCoordinate;
import resources.PdfCoordinateCalculator;

/**
 * Class to test the calculation of the line-intersections.
 * 
 * @author DaGri
 * @since 28.07.2016
 *
 */
public class LineIntersectionTest {

	PdfCoordinateCalculator calc = PdfCoordinateCalculator.getInstance();

	// TEST 1 : NORMAL INTERSECTION
	@Test
	public void test1() {
		PdfCoordinate c1 = new PdfCoordinate(0f, 0f);
		PdfCoordinate c2 = new PdfCoordinate(40f, 40f);
		PdfCoordinate c3 = new PdfCoordinate(0f, 40f);
		PdfCoordinate c4 = new PdfCoordinate(40f, 0f);

		PdfCoordinate inter = calc.calcIntersection(c1, c2, c3, c4);

		System.out.println(inter.getX());
		System.out.println(inter.getY());

		assertEquals(inter.getX(), 20, 0.0);
		assertEquals(inter.getY(), 20, 0.0);
	}

	// TEST 2 : INTERSECTION WITH LINE 1 HORIZONTAL
	@Test
	public void test2() {
		PdfCoordinate c1 = new PdfCoordinate(0f, 20f);
		PdfCoordinate c2 = new PdfCoordinate(40f, 20f);
		PdfCoordinate c3 = new PdfCoordinate(0f, 0f);
		PdfCoordinate c4 = new PdfCoordinate(40f, 40f);

		PdfCoordinate inter = calc.calcIntersection(c1, c2, c3, c4);

		System.out.println(inter.getX());
		System.out.println(inter.getY());

		assertEquals(inter.getX(), 20, 0.0);
		assertEquals(inter.getY(), 20, 0.0);
	}

	// TEST 3 : INTERSECTION WITH LINE 2 HORIZONTAL
	@Test
	public void test3() {
		PdfCoordinate c1 = new PdfCoordinate(0f, 0f);
		PdfCoordinate c2 = new PdfCoordinate(40f, 40f);
		PdfCoordinate c3 = new PdfCoordinate(0f, 20f);
		PdfCoordinate c4 = new PdfCoordinate(40f, 20f);

		PdfCoordinate inter = calc.calcIntersection(c1, c2, c3, c4);

		System.out.println(inter.getX());
		System.out.println(inter.getY());

		assertEquals(inter.getX(), 20, 0.0);
		assertEquals(inter.getY(), 20, 0.0);
	}

	// TEST 4 : INTERSECTION WITH LINE 1 VERTICAL
	@Test
	public void test4() {
		PdfCoordinate c1 = new PdfCoordinate(20f, 0f);
		PdfCoordinate c2 = new PdfCoordinate(20f, 40f);
		PdfCoordinate c3 = new PdfCoordinate(0f, 0f);
		PdfCoordinate c4 = new PdfCoordinate(40f, 40f);

		PdfCoordinate inter = calc.calcIntersection(c1, c2, c3, c4);

		System.out.println(inter.getX());
		System.out.println(inter.getY());

		assertEquals(inter.getX(), 20, 0.0);
		assertEquals(inter.getY(), 20, 0.0);
	}

	// TEST 5 : INTERSECTION WITH LINE 2 VERTICAL
	@Test
	public void test5() {
		PdfCoordinate c1 = new PdfCoordinate(0f, 0f);
		PdfCoordinate c2 = new PdfCoordinate(40f, 40f);
		PdfCoordinate c3 = new PdfCoordinate(20f, 0f);
		PdfCoordinate c4 = new PdfCoordinate(20f, 40f);

		PdfCoordinate inter = calc.calcIntersection(c1, c2, c3, c4);

		System.out.println(inter.getX());
		System.out.println(inter.getY());

		assertEquals(inter.getX(), 20, 0.0);
		assertEquals(inter.getY(), 20, 0.0);
	}

	// TEST 6 : INTERSECTION WITH A HORIZONTAL AND A VERTICAL LINE
	@Test
	public void test6() {
		PdfCoordinate c1 = new PdfCoordinate(0f, 20f);
		PdfCoordinate c2 = new PdfCoordinate(40f, 20f);
		PdfCoordinate c3 = new PdfCoordinate(20f, 0f);
		PdfCoordinate c4 = new PdfCoordinate(20f, 40f);

		PdfCoordinate inter = calc.calcIntersection(c1, c2, c3, c4);

		System.out.println(inter.getX());
		System.out.println(inter.getY());

		assertEquals(inter.getX(), 20, 0.0);
		assertEquals(inter.getY(), 20, 0.0);
	}

	// TEST 7 : INTERSECTION WITH A HORIZONTAL AND A VERTICAL LINE
	@Test
	public void test7() {
		PdfCoordinate c1 = new PdfCoordinate(20f, 0f);
		PdfCoordinate c2 = new PdfCoordinate(20f, 40f);
		PdfCoordinate c3 = new PdfCoordinate(0f, 20f);
		PdfCoordinate c4 = new PdfCoordinate(40f, 20f);

		PdfCoordinate inter = calc.calcIntersection(c1, c2, c3, c4);

		System.out.println(inter.getX());
		System.out.println(inter.getY());

		assertEquals(inter.getX(), 20, 0.0);
		assertEquals(inter.getY(), 20, 0.0);
	}

}

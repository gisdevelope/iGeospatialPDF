package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import coordinateSystems.EPSG25832;
import geo.BoundingBox;
import geo.Point2D;

/**
 * Class to test the {@link BoundingBox} features.
 * 
 * @author DaGri
 * @since 11.06.2016
 *
 */
public class BoundingBoxJunitTest {

	// ATTRIBUTES

	static double distance = 100;

	private static EPSG25832 epsg = new EPSG25832();

	private static BoundingBox bbox = new BoundingBox(new Point2D(0, 0, epsg), new Point2D(100, 100, epsg), epsg);

	BoundingBox bBoxRight = bbox.getBBoxRight(distance);

	BoundingBox bBoxLeft = bbox.getBBoxLeft(distance);

	BoundingBox bBoxAbove = bbox.getBBoxAbove(distance);

	BoundingBox bBoxBelow = bbox.getBBoxBelow(distance);

	// CONSTRUCTORS

	// METHODS

	/**
	 * Test method for
	 * {@link geo.BoundingBox#getCornersByVersion(resources.ServerVersion)}.
	 */
	@Test
	public void testGetCornersByVersion() {
		fail("Not yet implemented");
	}

	// BOUNDINGBOX RIGHT TESTS

	@Test
	public void testGetBBoxRight_DL_N() {
		assertEquals(bBoxRight.getDownLeft().getNorthing(), bbox.getDownLeft().getNorthing(), 0.0);
	}

	@Test
	public void testGetBBoxRight_DL_E() {
		assertEquals(bBoxRight.getDownLeft().getEasting(), bbox.getDownRight().getEasting(), 0.0);
	}

	@Test
	public void testGetBBoxRight_UR_N() {
		assertEquals(bBoxRight.getUpRight().getNorthing(), bbox.getUpRight().getNorthing(), 0.0);
	}

	@Test
	public void testGetBBoxRight_UR_E() {
		assertEquals(bBoxRight.getUpRight().getEasting(), bbox.getUpRight().getEasting() + distance, 0.0);
	}

	@Test
	public void testGetBBoxRight_Width() {
		assertEquals(bBoxRight.getWidthGeo(), distance, 0.0);
	}

	@Test
	public void testGetBBoRight_Height() {
		assertEquals(bBoxRight.getHeightGeo(), bbox.getHeightGeo(), 0.0);
	}

	// BOUNDINGBOX LEFT TESTS

	@Test
	public void testGetBBoxLeft_DL_N() {
		assertEquals(bBoxLeft.getDownLeft().getNorthing(), bbox.getDownLeft().getNorthing(), 0.0);
	}

	@Test
	public void testGetBBoxLeft_DL_E() {
		assertEquals(bBoxLeft.getDownLeft().getEasting(), bbox.getDownLeft().getEasting() - distance, 0.0);
	}

	@Test
	public void testGetBBoxLeft_UR_N() {
		assertEquals(bBoxLeft.getUpRight().getNorthing(), bbox.getUpRight().getNorthing(), 0.0);
	}

	@Test
	public void testGetBBoxLeft_UR_E() {
		assertEquals(bBoxLeft.getUpRight().getEasting(), bbox.getUpLeft().getEasting(), 0.0);
	}

	@Test
	public void testGetBBoxLeft_Width() {
		assertEquals(bBoxBelow.getWidthGeo(), distance, 0.0);
	}

	@Test
	public void testGetBBoxLeft_Height() {
		assertEquals(bBoxLeft.getHeightGeo(), bbox.getHeightGeo(), 0.0);
	}

	// BOUNDINGBOX BELOW TESTS

	@Test
	public void testGetBBoxBelow_DL_N() {
		assertEquals(bBoxBelow.getDownLeft().getNorthing(), bbox.getDownLeft().getNorthing() - distance, 0.0);
	}

	@Test
	public void testGetBBoxBelow_DL_E() {
		assertEquals(bBoxBelow.getDownLeft().getEasting(), bbox.getDownLeft().getEasting(), 0.0);
	}

	@Test
	public void testGetBBoxBelow_UR_N() {
		assertEquals(bBoxBelow.getUpRight().getNorthing(), bbox.getDownRight().getNorthing(), 0.0);
	}

	@Test
	public void testGetBBoxBelow_UR_E() {
		assertEquals(bBoxBelow.getUpRight().getEasting(), bbox.getUpRight().getEasting(), 0.0);
	}

	@Test
	public void testGetBBoxBelow_Width() {
		assertEquals(bBoxBelow.getWidthGeo(), bbox.getWidthGeo(), 0.0);
	}

	@Test
	public void testGetBBoxBelow_Height() {
		assertEquals(bBoxBelow.getHeightGeo(), distance, 0.0);
	}

	// BOUNDINGBOX ABOVE TESTS

	@Test
	public void testGetBBoxAbove_DL_N() {
		assertEquals(bBoxAbove.getDownLeft().getNorthing(), bbox.getUpRight().getNorthing(), 0.0);
	}

	@Test
	public void testGetBBoxAbove_DL_E() {
		assertEquals(bBoxAbove.getDownLeft().getEasting(), bbox.getDownLeft().getEasting(), 0.0);
	}

	@Test
	public void testGetBBoxAbove_UR_N() {
		assertEquals(bBoxAbove.getUpRight().getNorthing(), bbox.getUpRight().getNorthing() + distance, 0.0);
	}

	@Test
	public void testGetBBoxAbove_UR_E() {
		assertEquals(bBoxAbove.getUpRight().getEasting(), bbox.getUpRight().getEasting(), 0.0);
	}

	@Test
	public void testGetBBoxAbove_Width() {
		assertEquals(bBoxAbove.getWidthGeo(), bbox.getWidthGeo(), 0.0);
	}

	@Test
	public void testGetBBoxAbove_Height() {
		assertEquals(bBoxAbove.getHeightGeo(), distance, 0.0);
	}

	// GETTERS AND SETTERS

	// OTHERS
}

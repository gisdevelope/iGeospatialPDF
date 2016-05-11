package geo;

/**
 * Class to specify a geographic area on the surface of the earth. A
 * {@link BoundingBox} is typically defined by the lower left corner and the
 * upper rigth corner.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class BoundingBox {

	// ATTRIBUTES

	/**
	 * The lower left corner of this {@link BoundingBox} as {@link Point2D}.
	 */
	private Point2D downLeft;

	/**
	 * The upper right corner of this {@link BoundingBox} as {@link Point2D}.
	 */
	private Point2D upRight;

	/**
	 * The upper left corner of this {@link BoundingBox} as {@link Point2D}.
	 */
	private Point2D upLeft;

	/**
	 * The lower right corner of this {@link BoundingBox} as {@link Point2D}.
	 */
	private Point2D downRight;

	/**
	 * The center of this {@link BoundingBox} as {@link Point2D}.
	 */
	private Point2D center;

	/**
	 * The geographical width of this {@link BoundingBox} as {@link Double} in
	 * meters.
	 */
	private double widthGeo;

	/**
	 * The geographical height of this {@link BoundingBox} as {@link Double} in
	 * meters.
	 */
	private double heightGeo;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link BoundingBox} using two {@link Point2D}s. The
	 * first {@link Point2D} is used to specifiy the lower left corner of the
	 * {@link BoundingBox}, the second one specifies the upper right corner.
	 * 
	 * @param downLeft
	 *            the lower left corner as {@link Point2D}
	 * @param upRight
	 *            the upper right corner as {@link Point2D}
	 */
	public BoundingBox(Point2D downLeft, Point2D upRight) {
		super();
		this.downLeft = downLeft;
		this.upRight = upRight;
		this.calcOthers();
	}

	// METHODS

	/**
	 * Calculates the other corners and the width and height of this
	 * {@link BoundingBox}.
	 */
	private void calcOthers() {
		this.setUpLeft(new Point2D(this.getUpRight().getLat(), this.getDownLeft().getLon(),
				this.getDownLeft().getCoordSystem()));
		this.setDownRight(new Point2D(this.getDownLeft().getLat(), this.getUpRight().getLon(),
				this.getDownLeft().getCoordSystem()));
		this.setCenter(new Point2D(
				this.getDownLeft().getLat() + (this.getDownLeft().getLat() - this.getUpRight().getLat()) / 2,
				this.getDownLeft().getLon() + (this.getDownLeft().getLon() - this.getUpRight().getLon()) / 2,
				this.getDownLeft().getCoordSystem()));
		this.setHeightGeo(new GeoCalculator().calcDistance(downLeft, upLeft));
		this.setWidthGeo(new GeoCalculator().calcDistance(downLeft, downRight));
	}

	/**
	 * Returns the parameters of the lower left and the upper right
	 * {@link Point2D} in the correct way for a given WMS or WFS request.
	 *
	 * @param version
	 *            the version to match
	 * @return
	 */
	public String getCornersByVersion(String version) {
		String erg = "";
		if (null == null) {
			return erg;
		} else if (null == null) {
			return erg;
		}
		return null;
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the lower left {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @return the downLeft
	 */
	public Point2D getDownLeft() {
		return downLeft;
	}

	/**
	 * Sets the lower left {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @param downLeft
	 *            the downLeft to set
	 */
	public void setDownLeft(Point2D downLeft) {
		this.downLeft = downLeft;
	}

	/**
	 * Returns the upper right {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @return the upRight
	 */
	public Point2D getUpRight() {
		return upRight;
	}

	/**
	 * Sets the upper right {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @param upRight
	 *            the upRight to set
	 */
	public void setUpRight(Point2D upRight) {
		this.upRight = upRight;
		this.calcOthers();
	}

	/**
	 * Returns the upper left {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @return the upLeft
	 */
	public Point2D getUpLeft() {
		return upLeft;
	}

	/**
	 * Sets the upper left {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @param upLeft
	 *            the upLeft to set
	 */
	private void setUpLeft(Point2D upLeft) {
		this.upLeft = upLeft;
		this.calcOthers();
	}

	/**
	 * Returns the lower right {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @return the downRight
	 */
	public Point2D getDownRight() {
		return downRight;
	}

	/**
	 * Sets the lower right {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @param downRight
	 *            the downRight to set
	 */
	private void setDownRight(Point2D downRight) {
		this.downRight = downRight;
	}

	/**
	 * Returns the center {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @return the center
	 */
	public Point2D getCenter() {
		return center;
	}

	/**
	 * Sets the center {@link Point2D} of this {@link BoundingBox}.
	 *
	 * @param center
	 *            the center to set
	 */
	private void setCenter(Point2D center) {
		this.center = center;
	}

	/**
	 * Returns the geographical width of this {@link BoundingBox} (in meters).
	 *
	 * @return the widthGeo
	 */
	public double getWidthGeo() {
		return widthGeo;
	}

	/**
	 * Sets the geographical width of this {@link BoundingBox} (in meters).
	 *
	 * @param widthGeo
	 *            the widthGeo to set
	 */
	private void setWidthGeo(double widthGeo) {
		this.widthGeo = widthGeo;
	}

	/**
	 * Returns the geographical height of this {@link BoundingBox} (in meters).
	 *
	 * @return the heightGeo
	 */
	public double getHeightGeo() {
		return heightGeo;
	}

	/**
	 * Sets the geographical height of this {@link BoundingBox} (in meters).
	 *
	 * @param heightGeo
	 *            the heightGeo to set
	 */
	private void setHeightGeo(double heightGeo) {
		this.heightGeo = heightGeo;
	}

	// OTHERS
}

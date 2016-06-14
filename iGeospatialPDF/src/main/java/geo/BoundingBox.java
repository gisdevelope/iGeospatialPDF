package geo;

import java.util.logging.Level;
import java.util.logging.Logger;

import coordinateSystems.CoordinateSystem;
import resources.ServerVersion;

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
	 * The {@link CoordinateSystem} of this {@link BoundingBox}.
	 */
	private CoordinateSystem system;

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

	/**
	 * The {@link Logger} to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

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
	public BoundingBox(Point2D downLeft, Point2D upRight, CoordinateSystem system) {
		super();

		LOG.setLevel(Level.SEVERE);

		this.downLeft = downLeft;
		LOG.info("LOWER LEFT POINT2D SET");

		this.upRight = upRight;
		LOG.info("UPPER RIGHT POINT2D SET");

		this.setSystem(system);
		LOG.info("COORDIANTE SYSTEM SET");

		LOG.info("CALCULATING OTHER VALUES...");
		this.calcOthers();
	}

	// METHODS

	/**
	 * Calculates the other corners and the width and height of this
	 * {@link BoundingBox}.
	 */
	private void calcOthers() {
		LOG.info("START: CALCULATION OTHER VALUES...");
		// CALCULATING THE UPPER LEFT POINT2D
		this.setUpLeft(new Point2D( // CREATE NEW POINT2D
				this.getUpRight().getNorthing(), // UPPER RIGHT NORTHING VALUE
				this.getDownLeft().getEasting(), // LOWER LEFT EASTING VALUE
				this.getDownLeft().getCoordSystem() // LOWER LEFT COORDINATE
													// SYSTEM
		));
		LOG.info("UPPER LEFT POINT2D SET");
		// CALCULATING THE LOWER RIGHT POINT2D
		this.setDownRight(new Point2D( // CREATE NEW POINT
				this.getDownLeft().getNorthing(), // LOWER LEFT NORTHING
				this.getUpRight().getEasting(), // UPPER RIGHT EASTING
				this.getDownLeft().getCoordSystem() // LOWER LEFT COORDINATE
													// SYSTEM
		));

		// CALCULATE THE GEOGRAPHICAL HEIGHT
		this.setHeightGeo(new GeoCalculator().calcDistance(downLeft, upLeft));
		LOG.info("CALCULATED HEIGHT");

		// CALCULATE THE GEOGRAPHICAL HEIGHT
		this.setWidthGeo(new GeoCalculator().calcDistance(downLeft, downRight));
		LOG.info("CALCULATED WIDTH");

		LOG.info("LOWER RIGHT POINT2D SET");
		this.setCenter(new Point2D( // NEW POINT
				// LOWER LEFT NORTHING + BOUNDINGBOXHEIGHT / 2
				this.getDownLeft().getNorthing() + this.getHeightGeo() / 2,
				// LOWER LEFT EASTING + BOUNDINGBOXWIDTH / 2
				this.getDownLeft().getEasting() + this.getWidthGeo() / 2,
				// LOWER LEFT COORDINATE SYSTEM
				this.getDownLeft().getCoordSystem()));
		LOG.info("CENTRAL POINT2D SET");
		LOG.info("FINISHED: ALL OTHER VALUES WERE CALCUALTED");
	}

	// TODO : UEBERARBEITEN DA SO NICHT IN ALLEN VERSIONEN RICHTIG
	/**
	 * Returns the parameters of the lower left and the upper right
	 * {@link Point2D} in the correct way for a given WMS or WFS request.
	 *
	 * @param version
	 *            the version to match
	 * @return
	 */
	public String getCornersByVersion(ServerVersion version) {
		// CREATE THE RESULT STRING
		String erg = "";

		// TODO : NEUE SERVER VERSIONEN BERUECKSICHTIGEN

		// WEB FEATURE SERVICES
		if (version.toString().equals(ServerVersion.WFS_V_0_9_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WFS_V_0_9_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WFS_V_0_9_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WFS_V_1_0_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WFS_V_1_0_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WFS_V_1_0_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WFS_V_1_1_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WFS_V_1_1_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WFS_V_1_1_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WFS_V_2_0_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WFS_V_2_0_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WFS_V_2_0_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WFS_V_2_0_2.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WFS_V_2_0_2");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WFS_V_2_0_2");
			return erg;
		}
		// WEB MAP SERVICE
		else if (version.toString().equals(ServerVersion.WMS_V_0_0_3.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WMS_V_0_0_3");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WMS_V_0_0_3");
			return erg;
		} else if (version.toString().equals(ServerVersion.WMS_V_0_1_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WMS_V_0_1_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WMS_V_0_1_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WMS_V_0_9_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WMS_V_0_9_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WMS_V_0_9_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WMS_V_1_0_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WMS_V_1_0_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WMS_V_1_0_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WMS_V_1_1_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WMS_V_1_1_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WMS_V_1_1_0");
			return erg;
		} else if (version.toString().equals(ServerVersion.WMS_V_1_3_0.toString())) {
			// PUT THE STRING TOGETHER : EASTRING, NORTHING, EASTING NORTHING
			LOG.info("DETECTED SERVER VERSION WMS_V_1_3_0");
			erg = "" + this.getDownLeft().getEasting() + "," + this.getDownLeft().getNorthing() + ","
					+ this.getUpRight().getEasting() + "," + this.getUpRight().getNorthing();
			// RETURN STRING
			LOG.info("RETURNING CORNER VALUES FOR SERVER VERSION WMS_V_1_3_0");
			return erg;
		} else {
			LOG.severe("SERVER VERSION NOT SUPPORTED YET!");
		}
		// SERVER VERSION NOT SUPPORTED
		LOG.severe("COULD NOT FIND SERVER VERSION " + version.toString() + ". NO BOUNDINGBOX CORNER ORDER RETURNED!");
		return null;
	}

	/**
	 * Returns a {@link BoundingBox} that lies adjacent to this
	 * {@link BoundingBox} on the right. The returned {@link BoundingBox} has
	 * the same height and the given width (in meters).
	 *
	 * @param width
	 *            the width of the new {@link BoundingBox}
	 * @return bbox the adjacent {@link BoundingBox}
	 */
	public BoundingBox getBBoxRight(double width) {
		LOG.info("CALCUALTING A NEW BOUNDINGBOX TO THE RIGHT...");
		// THE ACTUAL LOWEER RIGHT IS THE NEW LOWER LEFT
		Point2D dl = this.getDownRight();
		// THE NEW UPPER RIGHT IS THE ACUTAL UPPER RIGHT + WIDTH
		Point2D ur = new Point2D(
				// THE NORTHING IS THE SAME
				this.getUpRight().getNorthing(),
				// THE EASTING IS THE EASTING + WIDTH
				this.getUpRight().getEasting() + width,
				// THE SYSTEM IS THE SAME
				this.getSystem());
		LOG.info("FINISHED: CALCULATING BOUNDINGBOX TO THE RIGHT");
		// RETURN THE NEW BOUNDINGBOX
		return new BoundingBox(dl, ur, this.getSystem());
	}

	/**
	 * Returns a {@link BoundingBox} that lies adjacent to this
	 * {@link BoundingBox} on the left. The returned {@link BoundingBox} has the
	 * same height and the given width (in meters).
	 *
	 * @param width
	 *            the width of the new {@link BoundingBox}
	 * @return bbox the adjacent {@link BoundingBox}
	 */
	public BoundingBox getBBoxLeft(double width) {
		LOG.info("CALCUALTING A NEW BOUNDINGBOX TO THE LEFT...");
		// THE NEW LOWER LEFT IS THE ACTUAL LOWER LEFT - WIDTH
		Point2D dl = new Point2D(
				// THE NORTHING IS THE SAME
				this.getDownLeft().getNorthing(),
				// THE EASTING IS THE EASTING - WIDTH
				this.getDownLeft().getEasting() - width,
				// THE COORDIANTE SYSTEM IS THE SAME
				this.getSystem());
		// THE NEW UPPER RIGHT IS THE OLD UPPER LEFT
		Point2D ur = this.getUpLeft();
		LOG.info("FINISHED: CALCULATING BOUNDINGBOX TO THE LEFT");
		// RETURN THE NEW BOUNDINGBOX
		return new BoundingBox(dl, ur, this.getSystem());
	}

	/**
	 * Returns a {@link BoundingBox} that lies adjacent above this
	 * {@link BoundingBox}. The returned {@link BoundingBox} has the same width
	 * and the given height (in meters).
	 *
	 * @param height
	 *            the height of the new {@link BoundingBox}
	 * @return bbox the adjacent {@link BoundingBox}
	 */
	public BoundingBox getBBoxAbove(double height) {
		LOG.info("CALCUALTING A NEW BOUNDINGBOX ABOVE...");
		// THE NEW LOWER LEFT IS THE OPF UPPER LEFT
		Point2D dl = this.getUpLeft();
		// THE NEW UPPER RIGHT IS THE ACTUAL UPPER RIGHT + HEIGHT
		Point2D ur = new Point2D(
				// THE NEW LOWER LEFT IS THE NORTHING + HEIGHT
				this.getUpRight().getNorthing() + height,
				// THE EASTING IS THE SAME
				this.getUpRight().getEasting(),
				// THE SYSTEM IS THE SAME
				this.getSystem());
		LOG.info("FINISHED: CALCULATING BOUNDINGBOX ABOVE");
		// RETURN THE NEW BOUNDINGBOX
		return new BoundingBox(dl, ur, this.getSystem());
	}

	/**
	 * Returns a {@link BoundingBox} that lies adjacent below this
	 * {@link BoundingBox}. The returned {@link BoundingBox} has the same width
	 * and the given height (in meters).
	 *
	 * @param height
	 *            the height of the new {@link BoundingBox}
	 * @return bbox the adjacent {@link BoundingBox}
	 */
	public BoundingBox getBBoxBelow(double height) {
		LOG.info("CALCUALTING A NEW BOUNDINGBOX BELOW...");
		Point2D dl = new Point2D(
				// THE NEW LOWER LEFT IS THE NORTHING - HEIGHT
				this.getDownLeft().getNorthing() - height,
				// THE EASTING IS THE SAME
				this.getDownLeft().getEasting(),
				// THE SYSTEM IS THE SAME
				this.getSystem());
		// THE NEW UPPER RIGHT IS THE OLD LOWER RIGHT
		Point2D ur = this.getDownRight();
		LOG.info("FINISHED: CALCULATING BOUNDINGBOX BELOW");
		// RETURN THE NEW BOUNDINGBOX
		return new BoundingBox(dl, ur, this.getSystem());

	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link CoordinateSystem} of this {@link BoundingBox}.
	 *
	 * @return the system as {@link CoordinateSystem}
	 */
	public CoordinateSystem getSystem() {
		return system;
	}

	/**
	 * Sets the {@link CoordinateSystem} of this {@link BoundingBox}.
	 *
	 * @param system
	 *            the system to set
	 */
	public void setSystem(CoordinateSystem system) {
		this.system = system;
	}

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
		this.calcOthers();
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

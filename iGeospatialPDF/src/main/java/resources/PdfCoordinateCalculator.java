package resources;

import java.util.ArrayList;
import java.util.logging.Logger;

import draw.geo.DrawLineString;
import draw.geo.DrawPoint;

/**
 * Class to provide support at various {@link PdfCoordinate} calculations.
 * 
 * @author DaGri
 * @since 08.06.2016
 */
public class PdfCoordinateCalculator {

	// ATTRIBUTES

	/**
	 * The instance of this {@link PdfCoordinateCalculator}.
	 */
	private static PdfCoordinateCalculator instance;

	/**
	 * The {@link Logger} used to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Empty, private constuctor for a {@link PdfCoordinateCalculator}
	 * (Singleton pattern).
	 */
	private PdfCoordinateCalculator() {
	}

	// METHODS

	/**
	 * Returns an instance of this {@link PdfCoordinateCalculator}.
	 *
	 * @return a {@link PdfCoordinateCalculator}
	 */
	public static PdfCoordinateCalculator getInstance() {
		if (instance == null) {
			instance = new PdfCoordinateCalculator();
		}
		return instance;
	}

	/**
	 * Scales a {@link PdfCoordinate} by the given factor and returns it.s
	 *
	 * @param coord
	 *            the {@link PdfCoordinate} to scale
	 * @param factor
	 *            the factor to scale about
	 * @return the given {@link PdfCoordinate}
	 */
	public PdfCoordinate scale(PdfCoordinate coord, double factor) {
		coord.setX((float) (coord.getX() * factor));
		coord.setY((float) (coord.getY() * factor));
		return coord;
	}

	/**
	 * Turns an {@link PdfCoordinate} by the given value in radiant around the
	 * specified turning center xc, yc and returns it.
	 *
	 * @param coord
	 *            the {@link PdfCoordinate} to turn
	 * @param rad
	 *            the angle in radiant to turn about
	 * @param turnEasting
	 *            the x value of the point to turn around
	 * @param turnNorthing
	 *            the y value of the point to turn around
	 * @return coord the turned {@link PdfCoordinate}
	 */
	public PdfCoordinate turn(PdfCoordinate coord, double turnNorthing, double turnEasting, double rad) {
		double angle = rad + Math.atan2(coord.getY() - turnNorthing, coord.getX() - turnEasting);
		double distance = Math.sqrt((coord.getX() - turnEasting) * (coord.getX() - turnEasting)
				+ (coord.getY() - turnNorthing) * (coord.getY() - turnNorthing));
		coord.setX((float) (turnEasting + distance * Math.cos(angle)));
		coord.setY((float) (turnNorthing + distance * Math.sin(angle)));
		return coord;
	}

	/**
	 * Reduces the {@link PdfCoordinate} with the northing and easting value and
	 * then returns it.
	 *
	 * @param coord
	 *            the {@link PdfCoordinate} to reduce
	 * @param northing
	 *            the northing to redurce
	 * @param easting
	 *            the easting to reduce
	 * @return coord the reduced {@link PdfCoordinate}
	 */
	public PdfCoordinate reduce(PdfCoordinate coord, double northing, double easting) {
		// REDURCE THE X BY THE GIVEN EASTING
		coord.setX((float) (coord.getX() - easting));
		// REDURCE THE Y BY THE GIVEN NORTHING
		coord.setY((float) (coord.getY() - northing));
		return coord;
	}

	/**
	 * Calculates the distance between two {@link PdfCoordinate}s.
	 *
	 * @param c1
	 *            the first {@link PdfCoordinate}
	 * @param c2
	 *            the second {@link PdfCoordinate}
	 * @return the distance as {@link Double}
	 */
	public double calculateDistance(PdfCoordinate c1, PdfCoordinate c2) {
		return Math.sqrt(
				(c1.getX() - c2.getX()) * (c1.getX() - c2.getX()) + (c1.getY() - c2.getY()) * (c1.getY() - c2.getY()));
	}

	/**
	 * Checks if the northing and easting value of a {@link DrawPoint} are
	 * between 0 and the values width and height.
	 * 
	 * Deprecated since there is a method using minX, minY, maxX, maxY to define
	 * the area.
	 *
	 * @param coord
	 *            the {@link PdfCoordinateCalculator}
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return <code>true</code> if it is inside, <code>false</code> if not.
	 */
	public boolean pdfCoordinateInArea(PdfCoordinate coord, float width, float height) {
		// IF THE POINT IS IN X DIRECTION BETWEEN 0 AND THE IMAGE WIDTH
		if (coord.getX() >= 0 && coord.getX() <= width)
			// IF THE POINT IS IN Y DIRECTION BETWEEN 0 AND THE IMAGE HEIGHT
			if (coord.getY() >= 0 && coord.getY() <= height)
				// POINT IS ON THE IMAGE
				return true;
		// ELSE POINT IS NOT ON THE IMAGE
		return false;
	}

	/**
	 * Checks if the northing and easting value of a {@link DrawPoint} are
	 * between minX / minY and the values maxX / maxY.
	 *
	 * @param coord
	 *            the {@link PdfCoordinate} to check
	 * @param minX
	 *            the minimal X value as {@link Float}
	 * @param minY
	 *            the minimal Y value as {@link Float}
	 * @param maxX
	 *            the maximal X value as {@link Float}
	 * @param maxY
	 *            the maximal Y value as {@link Float}
	 * @return <code>true</code> if the {@link PdfCoordinate} is inside the
	 *         area; <code>false</code> if not.
	 */
	public boolean pdfCoordinateInArea(PdfCoordinate coord, float minX, float minY, float maxX, float maxY) {
		// IF THE POINT IS IN X DIRECTION BETWEEN 0 AND THE IMAGE WIDTH
		if (coord.getX() >= minX && coord.getX() <= maxX)
			// IF THE POINT IS IN Y DIRECTION BETWEEN 0 AND THE IMAGE HEIGHT
			if (coord.getY() >= minY && coord.getY() <= maxY)
				// POINT IS ON THE IMAGE
				return true;
		// ELSE POINT IS NOT ON THE IMAGE
		return false;
	}

	/**
	 * Checks if the minimal values are really smaller than the maximum values
	 * and exchanges them if not.
	 *
	 * @param minX
	 *            the minimal X value as {@link Float}
	 * @param minY
	 *            the minimal Y value as {@link Float}
	 * @param maxX
	 *            the maximal X value as {@link Float}
	 * @param maxY
	 *            the maximal Y value as {@link Float}
	 */
	public void checkMinMax(float minX, float minY, float maxX, float maxY) {
		if (minX > maxX) {
			float temp = maxX;
			maxX = minX;
			minX = temp;
		}
		if (minY > maxY) {
			float temp = maxY;
			maxY = minY;
			minY = temp;
		}
	}

	/**
	 * Calculates the intersections of the {@link ArrayList} of
	 * {@link PdfCoordinate}s with the borders defined by 0 / 0 and width /
	 * height. Orders the calcualted {@link PdfCoordinate}s at the correct index
	 * of the {@link ArrayList}, removes the {@link PdfCoordinate}s outside and
	 * then returns the extended and cleaned {@link ArrayList}.
	 *
	 * @param coords
	 *            the {@link ArrayList} of {@link PdfCoordinate}
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the correct ordered and cleaned {@link ArrayList}.
	 */
	public ArrayList<PdfCoordinate> calcFittingArrayList(ArrayList<PdfCoordinate> coords, float width, float height) {

		// FOR EVERY PDFCOORDIANTE
		for (int a = 0; a < coords.size() - 1; a++) {

			// EXCTRACT THE PDFCOORDINATES TO LOOK AT
			PdfCoordinate c1 = coords.get(a);
			PdfCoordinate c2 = coords.get(a + 1);

			// IF BOTH POINTS ARE INSIDE THE IMAGE
			if (this.pdfCoordinateInArea(c1, width, height) && this.pdfCoordinateInArea(c2, width, height)) {
				// NO INTERSECTION BETWEEN THESE TWO POINTS AND A BORDER
				// POSSIBLE
			}
			// ELSE ONE OR MORE ARE OUTSIDE
			else {
				// IF ONE OF THEM IS INSIDE AND THE OTHER IS OUTSIDE
				if (this.pdfCoordinateInArea(c1, width, height) == false && this.pdfCoordinateInArea(c2, width, height)
						|| this.pdfCoordinateInArea(c1, width, height)
								&& this.pdfCoordinateInArea(c2, width, height) == false) {
					// THERE CAN ONLY BE ONE INTERSECTION TO CALCULATE
					// CALCULATE ALL POSSIBLE INTERSECTIONS
					ArrayList<PdfCoordinate> intersections = this.calcIntersections(c1, c2, width, height);

					// REMOVE ALL PDFCOORDIANTES THAT ARE OUTSIDE THE IMAGE

					// TODO : NOT NEEDED THIS WAY BECAUSE THE INTERSECTION MUST
					// LIE IN THE AREA DEFINED BY THE TWO PDFCOORDIANTES
					// this.removeCoordinatesOutside(intersections, width,
					// height);
					this.removeCoordinatesOutside(intersections, c1.getX(), c1.getY(), c2.getX(), c2.getY());

					// CALCULATE CORRECT ORDER
					ArrayList<PdfCoordinate> ordered = this.calculateCoordinateOrder(c1, c2, intersections);

					// THERE SHOULD ONLY BE ONE COORDINATE
					if (ordered.size() > 1)
						LOG.severe("ONLY ONE PDFCOORDINATE SHOULD BE EXISTENT!");
					else {
						// ADD THE CALCUALTED PDFCOORDINATES TO THE SOLUTION
						for (int b = 0; b < ordered.size(); b++) {
							// ADD THE CALCUALTED COORDAINTE(S) RIGHT TO A
							coords.add(a + b + 1, ordered.get(b));
							// COUNT ON A
							a++;
						}
					}
				}
				// ELSE BOTH ARE OUTSIDE
				else if (this.pdfCoordinateInArea(c1, width, height) == false
						&& this.pdfCoordinateInArea(c2, width, height) == false) {
					// IF BOTH ARE RIGHT
					if (c1.getX() > width && c1.getX() > width) {
						// NOTHING DUE NO INTERSECTION POSSIBLE
					}
					// ELSE IF BOTH ARE ABOVE
					else if (c1.getY() > height && c2.getY() > height) {
						// NOTHING DUE NO INTERSECTION POSSIBLE
					}
					// ELSE IF BOTH ARE BELOW
					else if (c1.getY() < 0 && c2.getY() < 0) {
						// NOTHING DUE NO INTERSECTION POSSIBLE
					}
					// ELSE IF BOTH ARE LEFT
					else if (c1.getX() < 0 && c2.getX() < 0) {
						// NOTHING DUE NO INTERSECTION POSSIBLE
					}
					// ELSE
					else {
						// CALCULATE ALL POSSIBLE INTERSECTIONS
						ArrayList<PdfCoordinate> intersections = this.calcIntersections(c1, c2, width, height);

						// REMOVE ALL PDFCOORDIANTES THAT ARE OUTSIDE THE IMAGE

						// TODO : NOT NEEDED THIS WAY BECAUSE THE INTERSECTION
						// MUST
						// LIE IN THE AREA DEFINED BY THE TWO PDFCOORDIANTES
						// this.removeCoordinatesOutside(intersections, width,
						// height);
						this.removeCoordinatesOutside(intersections, c1.getX(), c1.getY(), c2.getX(), c2.getY());

						// CALCULATE CORRECT ORDER
						ArrayList<PdfCoordinate> ordered = this.calculateCoordinateOrder(c1, c2, intersections);
						// ADD THE CALCUALTED PDFCOORDINATES TO THE SOLUTION
						for (int b = 0; b < ordered.size(); b++) {
							// ADD THE CALCUALTED COORDAINTE(S) RIGHT TO A
							coords.add(a + b + 1, ordered.get(b));
							// COUNT ON A
							a++;
						}
					}
				}
			}
		}
		// REMOVE ALL PDFCOORDIANTES THAT ARE OUTSIDE THE IMAGE
		this.removeCoordinatesOutside(coords, width, height);

		// RETURN SOLUTION
		return coords;
	}

	/**
	 * Calculates multiple {@link DrawLineString}s that lie inside the area
	 * defined by the width and height. These {@link DrawLineString}s represent
	 * the original {@link DrawLineString}, except the parts that lie outside of
	 * the area.
	 *
	 * @param ls
	 *            the {@link DrawLineString} to split to fitting parts
	 * @param width
	 *            he width of the area to fit to
	 * @param height
	 *            the height of the area to fit to
	 * @return an {@link ArrayList} of {@link DrawLineString}s
	 */
	public ArrayList<DrawLineString> calcFittingDrawLinestrings(DrawLineString ls, float width, float height) {

		// TODO : PROBLEM: BEIM ANKLICKEN EINES TEIL-LINESTRINGS WUERDE NUR
		// DIESER FARBLICH HERVORGEHOBEN, ABER NICHT DIE ANDEREN TEILE, DIE
		// MOEGLICHERWEISE ZU SEHEN SIND WENN SIE VON DIESEM SEPARIERT IM BILD
		// LIEGEN

		// CREATE THE SOLUTION STRUCTURE
		ArrayList<DrawLineString> erg = new ArrayList<>();

		// CREATE A NEW DRAWLINESTRING WITH THE SAME LINESTRING AS REFERENCE
		DrawLineString newString = new DrawLineString(ls.getLineString());
		// REMOVE ALL CALCULATED PDF COORDIANTES FROM THE OBJECT
		newString.empty();

		// FOR EVERY PDFCOORDINATE OF THE LINESTRING
		for (int a = 0; a < ls.getPdfCoords().size() - 1; a++) {
			// IF THE PDFCOORDINATE A IS INSIDE
			if (this.pdfCoordinateInArea(ls.getPdfCoords().get(a), width, height)) {

				// ADD THE COORDINATE A
				newString.getPdfCoords().add(ls.getPdfCoords().get(a));

				// IF THE PDFCOORDINATE A+1 IS INSIDE
				if (this.pdfCoordinateInArea(ls.getPdfCoords().get(a + 1), width, height)) {
					// BOTH PDFCOORDINATES (A && A+1) ARE INSIDE THE AREA,
					// DEFINED BY THE CORNERS (0/0) AND (WIDTH/HEIGHT). SO THERE
					// ARE NO POSSIBLE INTERSECTIONS.

					// A+1 WILL BE ADDED IN THE NEXT RUN

				}
				// ELSE THE PDFCOORDINATE A+1 IS NOT INSIDE: THERE MUST BE ONE
				// INTERSECTION BECAUSE THE FIRST PDFCOORDINATE IS INSIDE, BUT
				// THE SECOND IS NOT
				else {

					// CALCUALTE THE INTERSECTION(S)
					ArrayList<PdfCoordinate> intersections = this.calcIntersections(ls.getPdfCoords().get(a),
							ls.getPdfCoords().get(a + 1), width, height);

					// ADD THE INTERSECTION
					if (intersections.size() != 1)
						LOG.severe("MORE THAN ONE INTERSECTION FOUND - THAT SOULD NOT BE!");
					// ELSE THERE IS ONLY ONE INTERSECTION LEFT - ALL OKAY
					else
						// ADD THE CALCULATED INTERSECTION
						newString.getPdfCoords().add(intersections.get(0));

					// ADD THE DRAWLINESTING TO THE SOLUTION
					erg.add(newString);

					// START A NEW DRAWLINESTING BECAUSE THE OLD ONE LEFT THE
					// AREA
					newString = new DrawLineString(ls.getLineString());

					// REMOVE ALL CALCULATED PDF COORDIANTES FROM THE NEW
					// DRAWLINESTRING
					newString.empty();
				}
			}
			// ELSE THE PDFCOORDINATE A IS NOT INSIDE
			else {
				// IF THE PDFCOORDINATE A+1 IS INSIDE THERE MUST BE A SINGLE
				// INTERSECTION BECAUSE THE FIRST PDFCOORDAINTE IS NOT INSIDE,
				// BUT THE SECOND ONE IS
				if (this.pdfCoordinateInArea(ls.getPdfCoords().get(a + 1), width, height)) {

					// CALCUALTE THE INTESECTION(S)
					ArrayList<PdfCoordinate> intersections = this.calcIntersections(ls.getPdfCoords().get(a),
							ls.getPdfCoords().get(a + 1), width, height);

					// CHECK FOR MORE THAN ONE INTERSECTION
					if (intersections.size() != 1)
						LOG.severe("MORE THAN ONE INTERSECTION FOUND - THAT SHOULD NOT BE!");
					// ELSE THERE IS ONLY ONE INTERSECTION LEFT TO ADD - ALL
					// OKAY
					else
						// ADD THE INTERSECTION
						newString.getPdfCoords().add(intersections.get(0));
				}
				// ELSE THE PDFCOORDINATE A+1 IS NOT INSIDE
				else {
					// THERE MAY BE INTERSECTIONS, IF BOTH PDFCOORDINATES (A &&
					// A+1) ARE OUTSIDE: IF THE LINE CROSSES THE AREA THERE MUST
					// BE 2 INTERSECTIONS. IF NOT, THERE SHOULD BE 0
					// INTERSECTIONS

					// CALCUALTE THE INTESECTION(S)
					ArrayList<PdfCoordinate> intersections = this.calcIntersections(ls.getPdfCoords().get(a),
							ls.getPdfCoords().get(a + 1), width, height);

					// CHECK FOR MORE THAN ONE INTERSECTION
					if (intersections.size() == 0 || intersections.size() == 2) {
						// ADD THE INTERSECTION
						for (int b = 0; b < intersections.size(); b++) {
							newString.getPdfCoords().add(intersections.get(b));
						}
					}
					// ELSE THERE IS ONLY ONE INTERSECTION LEFT TO ADD - ALL
					// OKAY
					else
						LOG.severe("OTHER COUNT OF INTERSECTIONS THAN 0 OR 2 - THAT SH OULD NOT BE!");

					// START A NEW DRAWLINESTING BECAUSE THE OLD ONE ENTERED AND
					// LEFT THE AREA
					newString = new DrawLineString(ls.getLineString());

					// REMOVE ALL CALCULATED PDF COORDIANTES FROM THE NEW
					// DRAWLINESTRING
					newString.empty();

				}
			}
			// HERE A WILL BE COUNTED UP
		}
		// RETURN THE SOLUTION
		return erg;
	}

	/**
	 * Calculates the correct order of the {@link PdfCoordinate}s inside the
	 * {@link ArrayList} between c1 and c2.
	 *
	 * @param c1
	 *            the first {@link PdfCoordinate}
	 * @param c2
	 *            the second {@link PdfCoordinate}
	 * @param intersections
	 *            the {@link ArrayList} of {@link PdfCoordinate}s
	 * @return the corrected ordered {@link ArrayList} of {@link PdfCoordinate}
	 */
	// TODO : ERROR HIER IRGENDWO?! DAHER SICHERHEITSHALBER MAL DEPRECATED
	// TODO : GEFIXT - ABER NOCH TESTEN
	private ArrayList<PdfCoordinate> calculateCoordinateOrder(PdfCoordinate c1, PdfCoordinate c2,
			ArrayList<PdfCoordinate> intersections) {

		// CREATE THE SOLUTION STRUCTURE
		ArrayList<PdfCoordinate> erg = new ArrayList<>();

		// CALCULATE THE DISTANCE BETWEEN THE TWO START POINTS
		double d = this.calculateDistance(c1, c2);

		// IF SIZE > 2 : MAXIMUM SIZE SHOULD BE 2 : ERROR
		if (intersections.size() > 2)
			LOG.severe("MORE THAN 2 INTERSECTIONS LEFT!");
		else if (intersections.size() == 0) {
			// NOTHING
		}
		// ELSE IF THERE IS ONLY ONE INTERSECTION LEFT : ORDER IT BETWEEN C1 AND
		// C2
		else if (intersections.size() == 1) {
			erg.add(intersections.get(0));
		}
		// ELSE
		else {
			if (this.calculateDistance(c1, intersections.get(0))
					+ this.calculateDistance(intersections.get(0), intersections.get(1))
					+ this.calculateDistance(intersections.get(1), c2) == d) {
				erg.add(intersections.get(0));
				erg.add(intersections.get(1));
			} else if (this.calculateDistance(c1, intersections.get(1))
					+ this.calculateDistance(intersections.get(1), intersections.get(0))
					+ this.calculateDistance(intersections.get(0), c2) == d) {
				erg.add(intersections.get(1));
				erg.add(intersections.get(0));
			} else {
				LOG.severe("COULD NOT ORDER THE PDFCOORDINATES BETWEEN C1 AND C2!");
			}
		}

		// RETURN THE FILLED ARRAYLIST
		return erg;
	}

	/**
	 * Removes the {@link PdfCoordinate}s from the {@link ArrayList} that are
	 * outside the defined area (X/Y) : 0 / 0, width / height
	 *
	 * @param intersections
	 *            the {@link ArrayList} of {@link PdfCoordinate}s
	 * @param width
	 *            the width of the area
	 * @param height
	 *            the height of the area
	 */
	@Deprecated
	private void removeCoordinatesOutside(ArrayList<PdfCoordinate> intersections, float width, float height) {
		for (int a = 0; a < intersections.size(); a++) {
			// IF IT IS OUTSIDE
			if (this.pdfCoordinateInArea(intersections.get(a), width, height) == false) {
				// REMOVE IT
				intersections.remove(a);
				// COUNT DOWN A ONE TIME
				a--;
			}
		}
	}

	/**
	 * Removes the {@link PdfCoordinate}s from the {@link ArrayList} that are
	 * outside the defined area (X/Y) : minX / minY, maxX / maxY.s
	 *
	 * @param intersections
	 *            the {@link ArrayList} of {@link PdfCoordinate}s
	 * @param minX
	 *            the minimal X value as {@link Float}
	 * @param minY
	 *            the minimal Y value as {@link Float}
	 * @param maxX
	 *            the maximal X value as {@link Float}
	 * @param maxY
	 *            the maximal Y value as {@link Float}
	 */
	private void removeCoordinatesOutside(ArrayList<PdfCoordinate> intersections, float minX, float minY, float maxX,
			float maxY) {
		// CHECK IF THE COORDINATES ARE IN THE CORRECT ORDER
		this.checkMinMax(minX, minY, maxX, maxY);

		for (int a = 0; a < intersections.size(); a++) {
			// IF IT IS OUTSIDE
			if (this.pdfCoordinateInArea(intersections.get(a), minX, minY, maxX, maxY) == false) {
				// REMOVE IT
				intersections.remove(a);
				// COUNT DOWN A ONE TIME
				a--;
			}
		}
	}

	/**
	 * Calculates all intersections of the line, defined by c1 and c2, with the
	 * area borders (X/Y) 0 / 0 and width / height. The calculates
	 * intersections, that lie outside the area will be sorted out!
	 *
	 * @param c1
	 *            the first {@link PdfCoordinate}
	 * @param c2
	 *            the second {@link PdfCoordinate}
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the filled {@link ArrayList} of {@link PdfCoordinate} with all
	 *         intersections
	 */
	private ArrayList<PdfCoordinate> calcIntersections(PdfCoordinate c1, PdfCoordinate c2, float width, float height) {

		// CREATE THE SOLUTION STRUCTURE
		ArrayList<PdfCoordinate> erg = new ArrayList<>();

		// CREATE THE CORNERS TO DEFINE THE BORDERS FROM
		PdfCoordinate ll = new PdfCoordinate(0f, 0f);
		PdfCoordinate lr = new PdfCoordinate(0f, width);
		PdfCoordinate ur = new PdfCoordinate(height, width);
		PdfCoordinate ul = new PdfCoordinate(height, 0f);

		// LEFT BORDER
		erg.add(this.calcIntersection(c1, c2, ll, ul));
		// LOWER BORDER
		erg.add(this.calcIntersection(c1, c2, ll, lr));
		// UPPER BORDER
		erg.add(this.calcIntersection(c1, c2, ul, ur));
		// RIGHT BORDER
		erg.add(this.calcIntersection(c1, c2, lr, ur));

		// SORT OUT THE INTERSECTIONS OUTSIDE THE AREA
		this.removeCoordinatesOutside(erg, c1.getX(), c1.getY(), c2.getX(), c2.getY());

		// RETURN THE SOLUTION
		return erg;
	}

	/**
	 * Calculates the intersection between the two lines defined by p1 <-> p2,
	 * and p3 <-> p4. Returns a {@link PdfCoordinate} with X and Y value =
	 * -999999999f.
	 *
	 * @param p1
	 *            the start {@link PdfCoordinate} of line 1
	 * @param p2
	 *            the end {@link PdfCoordinate} of line 1
	 * @param p3
	 *            the start {@link PdfCoordinate} of line 2
	 * @param p4
	 *            the end {@link PdfCoordinate} of line 2
	 * @return the intersection {@link PdfCoordinate} of the two lines
	 */
	public PdfCoordinate calcIntersection(PdfCoordinate p1, PdfCoordinate p2, PdfCoordinate p3, PdfCoordinate p4) {
		// DEFINE XS AND YS
		float xs = -999999999f, ys = -999999999f;
		// IF THE LINES ARE PARALLEL
		if (p1.getX() - p2.getX() == p3.getX() - p4.getX() && p1.getY() - p2.getY() == p3.getY() - p4.getY()) {
			// LINES ARE PARALLEL
			// RETURN A PDF COORDIANTE WITH -999999999 VALUES IN X AND Y
			// DIRECTION
			return new PdfCoordinate(xs, ys);
		} // ELSE THE LINES ARE NOT PARALLEL
		else {

			float a1 = (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
			float a2 = (p4.getX() - p3.getX()) / (p4.getY() - p3.getY());
			float b1 = p1.getX() - a1 * p1.getY();
			float b2 = p3.getX() - a2 * p3.getY();
			xs = (b2 - b1) / (a1 - a2);
			ys = a1 * xs + b1;

			// CASE 1 : P1 - P2 == HORIZONTAL
			if (p1.getY() - p2.getY() == 0.0f) {
				ys = p1.getY();
				xs = a2 * ys + b2;
			}

			// CASE 2 : P3 - P4 == HORIZONTAL
			else if (p3.getY() - p4.getY() == 0.0f) {
				ys = p3.getY();
				xs = a1 * ys + b1;
			}

			// CASE 3 : P1 - P2 == VERTICAL
			else if (p1.getX() == p2.getX()) {
				// VALUES ARE ALREADY CORRECT
				// xs = p1.getX();
				// ys = (xs - b1) / a1;
			}

			// CASE 4 : P3 - P4 == VERTICAL
			else if (p3.getX() == p4.getX()) {
				// VALUES ARE ALREADY CORRECT
				// xs = p3.getX();
				// ys = (xs - b2) / a2;
			}
		}
		// TODO : HIER PRUEFEN OB DIE ERRECHNETE KOORDINATE UEBERHAUPT MOEGLICH
		// IST
		// EDIT : TESTEN DER PLAUSIBILITAET NICHT SO TRIVIAL MOEGLICH
		return new PdfCoordinate(xs, ys);
	}

	// GETTERS AND SETTERS

	/**
	 * Sets the instance of this {@link PdfCoordinateCalculator}.
	 *
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(PdfCoordinateCalculator inst) {
		instance = inst;
	}

	// OTHERS
}

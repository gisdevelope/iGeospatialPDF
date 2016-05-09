package draw;

import java.util.ArrayList;

import draw.geo.DrawLineString;
import draw.geo.DrawPoint;
import draw.geo.DrawPolygon;

/**
 * Class to collect various {@link DrawElement}s.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class DrawCollection {

	// ATTRIBUTES

	/**
	 * The {@link ArrayList} used to store the {@link DrawPoint}s.
	 */
	private ArrayList<DrawPoint> points = new ArrayList<>();

	/**
	 * The {@link ArrayList} used to store the {@link DrawLineString}s.
	 */
	private ArrayList<DrawLineString> linestrings = new ArrayList<>();

	/**
	 * The {@link ArrayList} used to store the {@link DrawPolygon}s.
	 */
	private ArrayList<DrawPolygon> polygons = new ArrayList<>();

	// TODO : HIER DIE STYLES UNTERBRINGEN?

	// CONSTRUCTORS

	/**
	 * Empty constructor for a {@link DrawCollection}.
	 *
	 */
	public DrawCollection() {

	}

	// METHODS

	/**
	 * Adds a {@link DrawElement} to the {@link DrawCollection}.
	 *
	 * @param de
	 *            the {@link DrawElement} to add
	 */
	public void addDrawElement(DrawElement de) {
		if (de.getClass().equals(DrawPoint.class)) {
			this.getPoints().add((DrawPoint) de);
		} else if (de.getClass().equals(DrawLineString.class)) {
			this.getLinestrings().add((DrawLineString) de);
		} else if (de.getClass().equals(DrawPolygon.class)) {
			this.getPolygons().add((DrawPolygon) de);
		}
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link ArrayList} of {@link DrawPoint}s.
	 *
	 * @return the points
	 */
	public ArrayList<DrawPoint> getPoints() {
		return points;
	}

	/**
	 * Sets the {@link ArrayList} of {@link DrawPoint}s.
	 *
	 * @param points
	 *            the points to set
	 */
	public void setPoints(ArrayList<DrawPoint> points) {
		this.points = points;
	}

	/**
	 * Returns the {@link ArrayList} of {@link DrawLineString}s.
	 *
	 * @return the linestrings
	 */
	public ArrayList<DrawLineString> getLinestrings() {
		return linestrings;
	}

	/**
	 * Sets the {@link ArrayList} of {@link DrawLineString}s.
	 *
	 * @param linestrings
	 *            the linestrings to set
	 */
	public void setLinestrings(ArrayList<DrawLineString> linestrings) {
		this.linestrings = linestrings;
	}

	/**
	 * Returns the {@link ArrayList} of {@link DrawPolygons}s.
	 *
	 * @return the polygons
	 */
	public ArrayList<DrawPolygon> getPolygons() {
		return polygons;
	}

	/**
	 * Sets the {@link ArrayList} of {@link DrawPolygons}s.
	 *
	 * @param polygons
	 *            the polygons to set
	 */
	public void setPolygons(ArrayList<DrawPolygon> polygons) {
		this.polygons = polygons;
	}

	// OTHERS
}

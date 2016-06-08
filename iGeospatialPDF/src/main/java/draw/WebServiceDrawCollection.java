package draw;

import java.util.ArrayList;
import java.util.logging.Logger;

import draw.drawer.Drawer;
import draw.drawer.WebServiceDrawer;
import draw.geo.DrawLineString;
import draw.geo.DrawPoint;
import draw.geo.DrawPolygon;
import draw.style.Style;
import draw.style.WebServiceStyle;
import iText.WebServicePDF;

/**
 * Class to extend the {@link DrawCollection} with methods to contain the needed
 * {@link DrawElement}s to be displayed in a {@link WebServicePDF}.
 * 
 * @author DaGri
 * @since 01.06.2016
 *
 */
public class WebServiceDrawCollection extends DrawCollection {

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

	/**
	 * The {@link Logger} used to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Constructor a {@link WebServiceDrawCollection} using a {@link Style} s.
	 * The {@link Style} may be null: The standard values for displaying the
	 * {@link DrawElement}s will be used. Sets the {@link Drawer} of this
	 * {@link WebServiceDrawCollection} to a {@link WebServiceDrawer}.
	 * 
	 * @param s
	 */
	public WebServiceDrawCollection(WebServiceStyle s) {
		// SET A WEBSERVICEDRAWER
		this.setDrawer(new WebServiceDrawer());

		if (s != null) {
			// SET THE GIVEN STYLE
			this.setStyle(s);
			((WebServiceDrawer) this.getDrawer()).setStyle(s);
		} else {
			// USE A NEW STYLE
			this.setStyle(new WebServiceStyle());
			((WebServiceDrawer) this.getDrawer()).setStyle(new WebServiceStyle());
		}
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
		} else
			LOG.warning("ELEMENT TO ADD CAN NOT BE ADDED TO THIS WEB SERVICE DRAW COLLECTION");
	}

	// OVERWRITTEN GETTERS AND SETTERS

	/**
	 * Returns the {@link WebServiceDrawer} of this
	 * {@link WebServiceDrawCollection}.
	 * 
	 * Overwritten method from superclass.
	 */
	@Override
	public Drawer getDrawer() {
		return (WebServiceDrawer) (super.getDrawer());
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

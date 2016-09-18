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
import geo.LineString;
import iText.WebServicePDF;
import resources.PdfCoordinateCalculator;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawCollection#prepareData(double, double, double, double)
	 */
	@Override
	public void prepareData(double northingRed, double eastingRed, double angle, double factor) {
		// THE DRAW OBJECTS KNOW HOW TO REDUCE, TURN AND SCALE THEM. SO USE THIS
		// METHODS HERE.
		// REDURCE THE POLYGONS
		for (int a = 0; a < this.getPolygons().size(); a++) {
			this.getPolygons().get(a).reduce(northingRed, eastingRed);
		}

		// REDUCE THE LINESTRINGS
		for (int a = 0; a < this.getLinestrings().size(); a++) {
			this.getLinestrings().get(a).reduce(northingRed, eastingRed);
		}

		// REDUCE THE POINTS
		for (int a = 0; a < this.getPoints().size(); a++) {
			this.getPoints().get(a).reduce(northingRed, eastingRed);
		}

		// TURN THE POLYGONS
		for (int a = 0; a < this.getPolygons().size(); a++) {
			this.getPolygons().get(a).turn(angle);
		}

		// TURN THE LINESTRINGS
		for (int a = 0; a < this.getLinestrings().size(); a++) {
			this.getLinestrings().get(a).turn(angle);
		}

		// TURN THE POINTS
		for (int a = 0; a < this.getPoints().size(); a++) {
			this.getPoints().get(a).turn(angle);
		}

		// SCALE THE POLYGONS
		for (int a = 0; a < this.getPolygons().size(); a++) {
			this.getPolygons().get(a).scale(factor);
		}
		// SCALE THE LINESTRINGS
		for (int a = 0; a < this.getLinestrings().size(); a++) {
			this.getLinestrings().get(a).scale(factor);
		}
		// SCALE THE POINTS
		for (int a = 0; a < this.getPoints().size(); a++) {
			this.getPoints().get(a).scale(factor);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawCollection#addDrawElement(draw.DrawElement)
	 */
	@Override
	public boolean addDrawElement(DrawElement de) {
		// IF THE DRAW ELEMENT IS A DRAWPOINT
		if (de.getClass().equals(DrawPoint.class)) {
			// ADD IT TO THE DRAW POINTS
			this.getPoints().add((DrawPoint) de);
			// RETURN TRUE
			return true;
		}
		// ELSE IF IT IS A DRAWLINESTRING
		else if (de.getClass().equals(DrawLineString.class)) {
			// ADD IT TO THE DRAWLINESTRINGS
			this.getLinestrings().add((DrawLineString) de);
			// RETURN TRUE
			return true;
		}
		// ELSE IF IT IS A DRAWPOLYGON
		else if (de.getClass().equals(DrawPolygon.class)) {
			// ADD IT TO THE DRAWPOLYGON
			this.getPolygons().add((DrawPolygon) de);
			// RETURN TRUE
			return true;
		}
		// ELSE THROW A WARNING
		else
			LOG.warning("ELEMENT TO ADD CAN NOT BE ADDED TO THIS WEB SERVICE DRAW COLLECTION");
		// AND RETURN FALSE
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawCollection#sortOutFeatures(geo.BoundingBox)
	 */
	@Override
	public void sortOutFeatures(float width, float height) {
		// TODO : METHODEN WURDEN AUSGELAGERT IN DIE KLASSE
		// PDFCOORDINATECALCULATOR
		PdfCoordinateCalculator calc1 = PdfCoordinateCalculator.getInstance();
		// SORT OUT THE DRAWPOINTS OUTSIDE OF THE BOUNDINGBOX
		for (int a = 0; a < this.getPoints().size(); a++) {
			if (calc1.pdfCoordinateInArea(this.getPoints().get(a).getPdfCoord(), width, height) == false) {
				// REMOVE THE DRAWPOINT
				this.getPoints().remove(a);
				// COUNT DOWN ONE TIME DUE TO THE REMOVING OF THE DRAW POINT
				a--;
			}
		}

		// TODO TESTEN
		for (int a = 0; a < this.getLinestrings().size(); a++) {
			// TODO : VERALTET
			// this.getLinestrings().get(a).setPdfCoords(
			// calc1.calcFittingArrayList(this.getLinestrings().get(a).getPdfCoords(),
			// width, height));
			// TODO : NEU
			DrawLineString ls = this.getLinestrings().remove(a);
			ArrayList<DrawLineString> strings = calc1.calcFittingDrawLinestrings(ls, width, height);

			for (int b = 0; b < strings.size(); b++) {
				// REIMPLANT THE (SPLITTED) LINESTRINGS
				this.getLinestrings().add(a, strings.get(b));
				a++;
			}

		}
		// TODO DRAW POLYGONS ABSCHNEIDEN WENN SIE AUSSERHALB DER BOUNDINGBOX
		// LIEGEN
		for (int a = 0; a < this.getLinestrings().size(); a++) {
			// TODO : OB DAS KLAPPT?!
			this.getPolygons().get(a)
					.setPdfCoords(calc1.calcFittingArrayList(this.getPolygons().get(a).getPdfCoords(), width, height));
		}
		// TODO DIE ABSCHNEIDE-METHODEN AUSLAGERN IN EIGENE METHODEN
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

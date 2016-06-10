package mapContent;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.lowagie.text.pdf.PdfContentByte;

import draw.WebServiceDrawCollection;
import draw.drawer.WebServiceDrawer;
import draw.geo.DrawLineString;
import draw.geo.DrawPoint;
import draw.geo.DrawPolygon;
import draw.style.WebServiceStyle;
import geo.BoundingBox;
import geo.Geometry;
import geo.LineString;
import geo.Point2D;
import geo.Polygon;
import resources.ServerVersion;

/**
 * Class to represent a {@link WfsLayer} - a layer requested from a Web Feature
 * Service (WFS).
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class WfsLayer extends MapLayer {

	// ATTRIBUTES

	/**
	 * The link to the web map service (WMS) server including the layer(s) to
	 * request by this {@link WfsLayer}
	 */
	private String link;

	/**
	 * The version of the server to request as {@link ServerVersion} by this
	 * {@link WfsLayer}.
	 */
	private ServerVersion version;

	/**
	 * The layers to request as {@link ArrayList} of {@link String}s by this
	 * {@link WfsLayer}.
	 */
	private ArrayList<String> layers = new ArrayList<>();

	/**
	 * The {@link ArrayList} of {@link Point2D}s of this {@link WfsLayer}.
	 */
	private ArrayList<Point2D> points = new ArrayList<>();

	/**
	 * The {@link ArrayList} of {@link LineString}s of this {@link WfsLayer}.
	 */
	private ArrayList<LineString> lineStrings = new ArrayList<>();

	/**
	 * The {@link ArrayList} of {@link Polygon}s of this {@link WfsLayer}.
	 */
	private ArrayList<Polygon> polygons = new ArrayList<>();

	/**
	 * The {@link WebServiceDrawCollection} of this {@link WfsLayer}.
	 */
	private WebServiceDrawCollection collection = new WebServiceDrawCollection(getStyle());

	/**
	 * The {@link WebServiceStyle} to use when displaying the content of this
	 * {@link WfsLayer}.
	 */
	private WebServiceStyle style = new WebServiceStyle();

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link WfsLayer} using a {@link BoundingBox}.
	 * 
	 * @param bbox
	 */
	public WfsLayer(BoundingBox bbox) {
		super(bbox);
		LOG = Logger.getLogger(this.getClass().getCanonicalName());
	}

	// TODO : CONSTRUCTOR MIT WICHTIGEN ATTRIBUTEN

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.MapLayer#receive()
	 */
	@Override
	public void receive() {
		// TODO : HIER DIE DATEN VON DEM SERVER EMPFANGEN

		// CONVERTING THE INTERNAL POLYGONS TO DRAWPOLYGONS
		// THE PDF COORDINATES OF THE DRAW OBJECTS WILL BE CALCULATED DIRECTLY
		// IN THE CONSTRUCTOR
		for (int a = 0; a < this.getPolygons().size(); a++) {
			this.getCollection().addDrawElement(new DrawPolygon(this.getPolygons().get(a)));
		}
		// CONVERTING THE INTERNAL LINESTRINGS TO DRAWLINESTRINGS
		// THE PDF COORDINATES OF THE DRAW OBJECTS WILL BE CALCULATED DIRECTLY
		// IN THE CONSTRUCTOR
		for (int a = 0; a < this.getLineStrings().size(); a++) {
			this.getCollection().addDrawElement(new DrawLineString(this.getLineStrings().get(a)));
		}
		// CONVERT THE INTERNAL POINTS TO DRAWPOINTS
		// THE PDF COORDINATES OF THE DRAW OBJECTS WILL BE CALCULATED DIRECTLY
		// IN THE CONSTRUCTOR
		for (int a = 0; a < this.getPoints().size(); a++) {
			this.getCollection().addDrawElement(new DrawPoint(this.getPoints().get(a)));
		}
	}

	/**
	 * Adds a single isance of {@link Geometry}. This may be a {@link Point2D},
	 * a {@link LineString} or a {@link Polygon}. Returns true if the
	 * {@link Geometry} was added successfully.
	 *
	 * @param g
	 *            the {@link Geometry} to add
	 * @return true or false
	 */
	public boolean addGeometry(Geometry g) {
		if (g instanceof Point2D) {
			this.getPoints().add((Point2D) g);
			return true;
		} else if (g instanceof LineString) {
			this.getLineStrings().add((LineString) g);
			return true;
		} else if (g instanceof Polygon) {
			this.getPolygons().add((Polygon) g);
			return true;
		} else
			return false;
	}

	/**
	 * Draws only the {@link Polygon}s of the internal
	 * {@link WebServiceDrawCollection}.
	 *
	 * @param contByte
	 *            the {@link PdfContentByte} to use while drawing
	 */
	// TODO : HIER GGF AUCH DAS STRUCTURE ELEMENT ANGEBEN - DAMIT ENTFELLT DER
	// EXTRA AUFRUF
	public void drawPolygons(PdfContentByte contByte) {
		this.getCollection().getDrawer().setContByte(contByte);
		((WebServiceDrawer) this.getCollection().getDrawer()).drawPolygons(this.getCollection());
	}

	/**
	 * Draws only the {@link LineString}s of the internal
	 * {@link WebServiceDrawCollection}.
	 *
	 * @param contByte
	 *            the {@link PdfContentByte} to use while drawing
	 */
	public void drawLinestrings(PdfContentByte contByte) {
		this.getCollection().getDrawer().setContByte(contByte);
		((WebServiceDrawer) this.getCollection().getDrawer()).drawLineStrings(this.getCollection());
	}

	/**
	 * Draws only the {@link Point2D}s of the internal
	 * {@link WebServiceDrawCollection}.
	 *
	 * @param contByte
	 *            the {@link PdfContentByte} to use while drawing
	 */
	public void drawPoints(PdfContentByte contByte) {
		this.getCollection().getDrawer().setContByte(contByte);
		((WebServiceDrawer) this.getCollection().getDrawer()).drawPoints(this.getCollection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.MapLayer#prepareData(double, double, double, double,
	 * double, double)
	 */
	@Override
	public void prepareData(double northingRed, double eastingRed, double angle, double factor) {
		this.getCollection().prepareData(northingRed, eastingRed, angle, factor);
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the link of this {@link WfsLayer} as {@link String}.
	 *
	 * @return the link as {@link String}
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link of this {@link WfsLayer}.
	 *
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Returns the server version to request by this {@link WfsLayer} as
	 * {@link ServerVersion}.
	 *
	 * @return the version as {@link ServerVersion}
	 */
	public ServerVersion getVersion() {
		return version;
	}

	/**
	 * Sets the version to request by this {@link WfsLayer}.
	 *
	 * @param version
	 *            the version to set
	 */
	public void setVersion(ServerVersion version) {
		this.version = version;
	}

	/**
	 * Returns the layers of this {@link WfsLayer} as {@link ArrayList} of
	 * {@link String}s.
	 *
	 * @return the layers as {@link ArrayList} of {@link String}s
	 */
	public ArrayList<String> getLayers() {
		return layers;
	}

	/**
	 * Sets the layers of this {@link WfsLayer} as {@link ArrayList} of
	 * {@link String}s.
	 *
	 * @param layers
	 *            the layers to set
	 */
	public void setLayers(ArrayList<String> layers) {
		this.layers = layers;
	}

	/**
	 * Returns the {@link WebServiceDrawCollection} of this {@link WfsLayer}.
	 *
	 * @return the collection as {@link WebServiceDrawCollection}
	 */
	public WebServiceDrawCollection getCollection() {
		return collection;
	}

	/**
	 * Sets the {@link WebServiceDrawCollection} of this {@link WfsLayer}.
	 *
	 * @param collection
	 *            the collection to set
	 */
	public void setCollection(WebServiceDrawCollection collection) {
		this.collection = collection;
	}

	/**
	 * Returns the {@link ArrayList} of {@link Point2D}s of this
	 * {@link WfsLayer}.
	 *
	 * @return the points as {@link ArrayList} of {@link Point2D}s
	 */
	public ArrayList<Point2D> getPoints() {
		return points;
	}

	/**
	 * Sets the {@link ArrayList} of {@link Point2D}s of this {@link WfsLayer}.
	 *
	 * @param points
	 *            the points to set
	 */
	public void setPoints(ArrayList<Point2D> points) {
		this.points = points;
	}

	/**
	 * Returns the {@link ArrayList} of {@link LineString}s of this
	 * {@link WfsLayer}.
	 *
	 * @return the lineStrings as {@link ArrayList} of {@link LineString}s
	 */
	public ArrayList<LineString> getLineStrings() {
		return lineStrings;
	}

	/**
	 * Sets the {@link ArrayList} of {@link LineString}s this {@link WfsLayer}.
	 *
	 * @param lineStrings
	 *            the lineStrings to set
	 */
	public void setLineStrings(ArrayList<LineString> lineStrings) {
		this.lineStrings = lineStrings;
	}

	/**
	 * Returns the {@link ArrayList} of {@link Polygon}s of this
	 * {@link WfsLayer}.
	 *
	 * @return the polygons as {@link ArrayList} of {@link Polygon}s
	 */
	public ArrayList<Polygon> getPolygons() {
		return polygons;
	}

	/**
	 * Sets the {@link ArrayList} of {@link Polygon}s of this {@link WfsLayer}.
	 *
	 * @param polygons
	 *            the polygons to set
	 */
	public void setPolygons(ArrayList<Polygon> polygons) {
		this.polygons = polygons;
	}

	/**
	 * Returns the {@link WebServiceStyle} of this {@link WfsLayer}.
	 *
	 * @return the style as {@link WebServiceStyle}
	 */
	public WebServiceStyle getStyle() {
		return style;
	}

	/**
	 * Sets the {@link WebServiceStyle} of this {@link WfsLayer}
	 *
	 * @param style
	 *            the style to set
	 */
	public void setStyle(WebServiceStyle style) {
		this.style = style;
	}

	// OTHERS
}

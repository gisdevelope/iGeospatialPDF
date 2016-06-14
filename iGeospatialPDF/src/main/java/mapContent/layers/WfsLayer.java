package mapContent.layers;

import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.lowagie.text.pdf.PdfContentByte;

import draw.DrawElement;
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
import resources.XmlParser;

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

	/**
	 * The maximum requested features.
	 */
	private ArrayList<Integer> maxFeatures = new ArrayList<>();

	/**
	 * The angle to turn the {@link DrawElement}s about.
	 */
	private double angle = 0.0;

	/**
	 * The factor to scale the {@link DrawElement}s with.
	 */
	private double factor = 1.0;

	// CONSTRUCTORS

	public WfsLayer(String link, BoundingBox bbox, ServerVersion serverVersion, ArrayList<String> layers,
			ArrayList<Integer> maxFeatures, WebServiceStyle style) {
		super(bbox);
		// UPDATE THE PARENTAL LOGGER
		LOG = Logger.getLogger(this.getClass().getCanonicalName());

		// SET THE LINK TO THE SERVER
		this.setLink(link);

		// SET THE SERVER VERSION
		this.setVersion(serverVersion);

		// SET THE LAYERS
		this.setLayers(layers);

		// ARRAYLIST STUFF
		this.checkLayerNumber(layers, maxFeatures);

		// STYLE STUFF
		this.checkStyle(style);

		// ALL WORK SHOULD BE DONE
	}

	// METHODS

	/**
	 * Checks the given {@link WebServiceStyle}: If the given
	 * {@link WebServiceStyle} is null a standard {@link WebServiceStyle} will
	 * be used. If it is not null it will be used.
	 *
	 * @param s
	 *            the {@link WebServiceStyle} to check
	 */
	private void checkStyle(WebServiceStyle s) {
		// IF STYLE IS NOT NULL
		if (s != null) {
			// USE THE STYLE
			this.setStyle(s);
		} // ELSE DO NOTHING
		else {
			// USE STANDARD STYLE
			this.setStyle(new WebServiceStyle());
		}
	}

	/**
	 * Checks if the two {@link ArrayList}s have the same size and equals the
	 * maxFeatures {@link ArrayList} to the layers {@link ArrayList} if not.
	 * Then the locate variables will be set with this {@link ArrayList}s.
	 *
	 * @param layers
	 *            the {@link ArrayList} of {@link String}s containing the layers
	 * @param maxFeatures
	 *            the {@link ArrayList} of {@link Integer}s containing the max
	 *            features
	 */
	private void checkLayerNumber(ArrayList<String> layers, ArrayList<Integer> maxFeatures) {
		// IF THE SIZE OF LAYERS AND MAXFEATURES IS EQAL
		if (layers.size() == maxFeatures.size()) {
			// ALL OKAY
		}
		// ELSE THERE ARE MORE LAYERS THEN MAXFEATURES
		else if (layers.size() > maxFeatures.size()) {
			// EXTEND MAXFEATURES WITH THE MISSING MAXFEATURES
			for (int a = maxFeatures.size() - 1; a < layers.size(); a++) {
				maxFeatures.add(Integer.MAX_VALUE);
			}
		}
		// ELSE THERE ARE LESS LAYERS THEN MAXFEATURES
		else if (layers.size() < maxFeatures.size()) {
			// SHORTEN MAXFEATURES
			// CREATE TEMPORARY LIST
			ArrayList<Integer> temp = maxFeatures;
			// CLEAR MAXFEATURES
			maxFeatures.clear();
			// WRITE MAXFEATURES BACK, ONLY FOR THE LAYERS.SIZE
			for (int a = 0; a < layers.size(); a++) {
				maxFeatures.add(temp.get(a));
			}
		}
		// SET THE (CORRECTED) LISTS
		this.setLayers(layers);
		this.setMaxFeatures(maxFeatures);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.MapLayer#receive()
	 */
	@Override
	public void receive() {
		// DA UNTERSCHIEDLICHE ANZAHLEN VON MAXFEATURES PRO LAYER ANGEBBAR SIND
		// MUSS FUER JEDEN DIESER LAYER EINE EIGENE WFS ABFRAGE GESTARTET WERDEN
		// UM NUR DIE ENTSPRECHENDE ANZAHL ABZUFRAGEN
		for (int a = 0; a < this.getLayers().size(); a++) {
			URL temp = XmlParser.getInstance().buildServerUrl(getLink(), getLayers().get(a), getVersion(),
					this.getMaxFeatures().get(a), this.getBbox());
			if (temp != null) {
				this.downloadData(temp);
			}
		}

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

		// PREPARE THE DATA
		this.getCollection().prepareData(this.getBbox().getDownLeft().getNorthing(),
				this.getBbox().getDownLeft().getEasting(), this.getAngle(), this.getFactor());
	}

	/**
	 * Downloads the Data from the given {@link URL} and adds the created
	 * {@link Geometry}s to the internal {@link ArrayList}s.
	 *
	 * @param temp
	 *            the {@link URL} to download the data from.
	 */
	private void downloadData(URL temp) {
		XmlParser xmlParser = XmlParser.getInstance();
		xmlParser.downloadGeoFeatures(temp, this.getBbox().getSystem());

		for (int a = 0; a < xmlParser.getPolygons().size(); a++) {
			this.getPolygons().add(xmlParser.getPolygons().get(a));
		}

		for (int a = 0; a < xmlParser.getLineStrings().size(); a++) {
			this.getLineStrings().add(xmlParser.getLineStrings().get(a));
		}

		for (int a = 0; a < xmlParser.getPoints().size(); a++) {
			this.getPoints().add(xmlParser.getPoints().get(a));
		}
		xmlParser.clear();
	}

	/**
	 * Adds a single instance of {@link Geometry}. This may be a {@link Point2D}
	 * , a {@link LineString} or a {@link Polygon}. Returns true if the
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
	 * Adds a {@link ArrayList} of {@link Geometry}s. This list may contain
	 * {@link Point2D}s , {@link LineString}s or {@link Polygon}s.
	 *
	 * @param geoList
	 *            the {@link ArrayList} of {@link Geometry}s to add
	 */
	public void addGeometries(ArrayList<Geometry> geoList) {
		for (int a = 0; a < geoList.size(); a++) {
			this.addGeometry(geoList.get(a));
		}
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
		// SET THE CONTENT BYTE
		this.getCollection().getDrawer().setContByte(contByte);
		// SET THE STYLE
		((WebServiceDrawer) this.getCollection().getDrawer()).setStyle(this.getStyle());
		// DRAW THE POLYGONS
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
		// SET THE CONTENT BYTE
		this.getCollection().getDrawer().setContByte(contByte);
		// SET THE STYLE
		((WebServiceDrawer) this.getCollection().getDrawer()).setStyle(this.getStyle());
		// DRAW THE LINESTRINGS
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
		// SET THE CONTENT BYTE
		this.getCollection().getDrawer().setContByte(contByte);
		// SET THE STYLE
		((WebServiceDrawer) this.getCollection().getDrawer()).setStyle(this.getStyle());
		// DRAW THE POINTS
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

	/**
	 * Returns the maximum number of features to be requested by this
	 * {@link WfsLayer}.
	 *
	 * @return the maxFeatures as {@link Double}
	 */
	public ArrayList<Integer> getMaxFeatures() {
		return maxFeatures;
	}

	/**
	 * Sets the maximum number of features to be requested by this
	 * {@link WfsLayer}.
	 *
	 * @param maxFeatures
	 *            the maxFeatures to set
	 */
	public void setMaxFeatures(ArrayList<Integer> maxFeatures) {
		this.maxFeatures = maxFeatures;
	}

	/**
	 * Returns the angle of this {@link WfsLayer} as {@link Double}.
	 *
	 * @return the angle as {@link Double}
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Sets the angle of this {@link WfsLayer}.
	 *
	 * @param angle
	 *            the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Returns the factor of this {@link WfsLayer} as {@link Double}.
	 *
	 * @return the factor as {@link Double}
	 */
	public double getFactor() {
		return factor;
	}

	/**
	 * Sets the factor of this {@link WfsLayer}.
	 *
	 * @param factor
	 *            the factor to set
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}

	// OTHERS
}

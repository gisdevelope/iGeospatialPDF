package resources;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import coordinateSystems.CoordinateSystem;
import geo.BoundingBox;
import geo.Geometry;
import geo.LineString;
import geo.Point2D;
import geo.Polygon;

/**
 * Class to parse a XML source into different objects.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class XmlParser {

	// ATTRIBUTES

	/**
	 * The instance of this {@link XmlParser}.
	 */
	private static XmlParser instance = null;

	/**
	 * The {@link ArrayList} of {@link Polygon}s of this {@link XmlParser}.
	 */
	ArrayList<Polygon> polygons = new ArrayList<>();

	/**
	 * The {@link ArrayList} of {@link LineString} of this {@link XmlParser}.
	 */
	ArrayList<LineString> lineStrings = new ArrayList<>();

	/**
	 * The {@link ArrayList} of {@link Point2D}s of this {@link XmlParser}.
	 */
	ArrayList<Point2D> points = new ArrayList<>();

	/**
	 * The {@link Logger} to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Empty constructor for a {@link XmlParser}.
	 */
	private XmlParser() {
	}

	// METHODS

	/**
	 * Fills the internal {@link ArrayList} of {@link Geometry}s by accessing a
	 * web feature service.
	 *
	 * @param serverLink
	 *            the link to the server as {@link String}
	 * @param layers
	 *            the layers to request as {@link String}
	 * @param version
	 *            the version to access as {@link ServerVersion}
	 * @param maxFeatures
	 *            the maximum count of features
	 * @param bbox
	 *            the {@link BoundingBox} to request features inside
	 */
	public void getGeoObjects(String serverLink, String layers, ServerVersion version, int maxFeatures,
			BoundingBox bbox) {
		URL url = this.buildServerUrl(serverLink, layers, version, maxFeatures, bbox);
		if (url == null) {
			return;
		}
		this.downloadGeoFeatures(url, bbox.getSystem());
	}

	/**
	 * Builds the {@link URL} that can be used to access a web feature server.
	 * The {@link URL} will be build using the link to the server as
	 * {@link String}, a {@link String} containing the layers, the
	 * {@link ServerVersion}, the maximum features to request as {@link Integer}
	 * and the {@link BoundingBox} to request.
	 * 
	 * @param serverLink
	 *            the link to the server as {@link String}
	 * @param layers
	 *            the layers to request as {@link String}
	 * @param version
	 *            the version to access as {@link ServerVersion}
	 * @param maxFeatures
	 *            the maximum features to access
	 * @param bbox
	 *            the {@link BoundingBox} to request
	 * @return the coputated {@link URL}
	 */
	public URL buildServerUrl(String serverLink, String layers, ServerVersion version, int maxFeatures,
			BoundingBox bbox) {
		String bboxCorners = bbox.getCornersByVersion(version);
		String versionString = null;
		if (version.toString().equals(ServerVersion.WFS_V_0_9_0.toString())) {
			versionString = "0.9.0";
		} else if (version.toString().equals(ServerVersion.WFS_V_1_0_0.toString())) {
			versionString = "1.0.0";
		} else if (version.toString().equals(ServerVersion.WFS_V_1_1_0.toString())) {
			versionString = "1.1.0";
		} else if (version.toString().equals(ServerVersion.WFS_V_2_0_0.toString())) {
			versionString = "2.0.0";
		} else if (version.toString().equals(ServerVersion.WFS_V_2_0_2.toString())) {
			versionString = "2.0.2";
		} else {
			LOG.severe("VERSION IS NOT SUPPORTED!");
		}
		if (versionString != null) {
			String calcLink = serverLink + "SERVICE=WFS" + "&REQUEST=getfeature" + "&VERSION=" + versionString
					+ "&MAXFEATURES=" + maxFeatures + "&TYPENAME=" + layers + "&BBOX=" + bboxCorners;
			System.out.println(calcLink);

			URL url = this.getUrl(calcLink);

			return url;
		} else
			return null;
	}

	/**
	 * Returns the from the {@link String} converted {@link URL} or null if it
	 * did not work.
	 *
	 * @param buildedServerLink
	 *            the {@link String} to parse
	 * @return url the converted {@link URL}
	 */
	private URL getUrl(String buildedServerLink) {
		try {
			URL url = new URL(buildedServerLink);
			return url;
		} catch (MalformedURLException e) {
			LOG.severe("ERROR CREATING THE WFS URL! CAN NOT RECEIVE THE WFS DATA!");
			LOG.severe(buildedServerLink);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Downloads the XML with the given {@link URL} and creates the
	 * {@link Point2D}s, {@link LineString}s and {@link Polygon}s with the given
	 * {@link CoordinateSystem}.
	 *
	 * @param url
	 *            the {@link URL} to download from
	 * @param system
	 *            the {@link CoordinateSystem}
	 */
	public void downloadGeoFeatures(URL url, CoordinateSystem system) {
		String xml = this.downloadXml(url);
		this.parseWfsXml(xml, system);
	}

	/**
	 * Downloads the XML-data from the given {@link URL}.
	 *
	 * @param url
	 *            the URL to download the features from
	 * @return the downloaded XML as {@link String}
	 */
	private String downloadXml(URL url) {
		try {
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);

			in.close();

			return response.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Creates the {@link Point2D}s, the {@link LineString}s and the
	 * {@link Polygon}s from the XML given as {@link String} with the given
	 * {@link CoordinateSystem}.
	 *
	 * @param xml
	 *            the XML to parse as {@link String}
	 * @param system
	 *            the {@link CoordinateSystem}
	 */
	private void parseWfsXml(String xml, CoordinateSystem system) {
		this.createPoint2Ds(xml, system);
		this.createLineStrings(xml, system);
		this.createPolygons(xml, system);
	}

	/**
	 * Creates {@link Point2D}s from the as {@link String} given XML with the
	 * given {@link CoordinateSystem}.
	 *
	 * @param xml
	 *            the XML as {@link String}
	 * @param system
	 *            the {@link CoordinateSystem}
	 */
	public void createPoint2Ds(String xml, CoordinateSystem system) {
		Document doc;
		try {
			InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			doc = new SAXBuilder().build(stream);
			Element elem = doc.getRootElement();
			ArrayList<Element> ergElem0 = this.findTaginXml(elem, "Point");
			for (int a = 0; a < ergElem0.size(); a++) {
				// TODO : WENN KEINE GML DANN IST DAS NICHT COORDINATES SONDERN
				// POS; ODER?
				ArrayList<Element> ergElem1 = this.findTaginXml(ergElem0.get(a), "coordinates");
				for (int b = 0; b < ergElem1.size(); b++) {
					String elementText = ergElem1.get(b).getText();
					// SPLIT THE STRING AT A ","
					String[] textParts = elementText.split(",");
					// CHECK IF THE LENGTH OF THE ARRAY IS == 2 (X AND Y)
					if (textParts.length != 2) {
						// IF NOT : TRY TO SPLIT IT AT AN BLANK SPACE
						textParts = elementText.split(" ");
					}
					// IF THE STRING IS OKAY IN LENGTH
					if (textParts.length == 2) {
						// TRY
						try {
							// CREATE POINT
							Point2D p1 = new Point2D(Double.parseDouble(textParts[1]), Double.parseDouble(textParts[0]),
									system);
							// ADD POINT
							this.getPoints().add(p1);
						} catch (NumberFormatException e) {
							// CATCH EXCEPTION : POINT WILL NOT BE CREATED
							LOG.severe("COULD NOT PARSE DOUBLE VALUE FROM THE SPLITTED STRING");
						}
					}
				}
			}
		} catch (JDOMException | IOException e) {
			LOG.severe("COULD NOT PARSE POINT2DS FROM THE DOWNLOADED XML");
			e.printStackTrace();
		}
		System.out.println("POINTS2D CREATED!");
	};

	public void createLineStrings(String xml, CoordinateSystem system) {
		// TODO : CREATE LINESTRINGS
	};

	/**
	 * Creates {@link LineString}s from the given XML as {@link String} with the
	 * given {@link CoordinateSystem}.
	 *
	 * @param xml
	 *            as {@link String}
	 * @param system
	 *            as {@link CoordinateSystem}
	 */
	public void createPolygons(String xml, CoordinateSystem system) {
		// TODO : CREATE POLYGONS
	};

	/**
	 * Finds {@link Element}s below a root {@link Element} with the given tag as
	 * {@link String} and returns all found {@link Element}s in an
	 * {@link ArrayList}.
	 *
	 * @param rootElement
	 *            the root {@link Element} of the XML
	 * @param tag
	 *            the tag to find as {@link String}
	 * @return {@link ArrayList} of {@link Element}s with the given tag
	 */
	public ArrayList<Element> findTaginXml(Element rootElement, String tag) {
		ArrayList<Element> erg = new ArrayList<>();
		try {
			if (rootElement == null)
				LOG.severe("ROOT ELEMENT IS NULL. CAN NOT FIND TAGS.");
			List<Element> children = rootElement.getChildren();
			Iterator<Element> iterator = children.iterator();
			while (iterator.hasNext()) {
				Element childElement = (Element) iterator.next();
				if (childElement.getName().equals(tag)) {
					erg.add(childElement);
				}
				try {
					ArrayList<Element> foundElement = findTaginXml(childElement, tag);
					if (foundElement != null) {
						for (int a = 0; a < foundElement.size(); a++) {
							erg.add(foundElement.get(a));
						}
					}
				} catch (IllegalArgumentException e) {
					// CATCH EXCEPTION
				}
			}
			return erg;
		} catch (IllegalStateException e) {
			// CATCH EXCEPTION
			return erg;
		}
	}

	/**
	 * Returns an instance of this {@link XmlParser}.
	 *
	 * @return instance as an instance of a {@link XmlParser}
	 */
	public static XmlParser getInstance() {
		if (instance == null) {
			instance = new XmlParser();
		}
		return instance;
	}

	/**
	 * Clears the internal {@link ArrayList}s of {@link Point2D},
	 * {@link LineString}s and {@link Polygon}s.
	 */
	public void clear() {
		this.getPolygons().clear();
		this.getLineStrings().clear();
		this.getPoints().clear();
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link ArrayList} of {@link Polygon}s of this
	 * {@link XmlParser}.
	 *
	 * @return the polygons as {@link ArrayList} of {@link LineString}s
	 */
	public ArrayList<Polygon> getPolygons() {
		return polygons;
	}

	/**
	 * Sets the {@link ArrayList} of {@link Polygon}s of this {@link XmlParser}.
	 *
	 * @param polygons
	 *            the polygons to set
	 */
	public void setPolygons(ArrayList<Polygon> polygons) {
		this.polygons = polygons;
	}

	/**
	 * Returns the {@link ArrayList} of {@link LineString}s of this
	 * {@link XmlParser}.
	 *
	 * @return the lineStrings as {@link ArrayList} of {@link LineString}s
	 */
	public ArrayList<LineString> getLineStrings() {
		return lineStrings;
	}

	/**
	 * Sets the {@link ArrayList} of {@link LineString}s of this
	 * {@link XmlParser}.
	 *
	 * @param lineStrings
	 *            the lineStrings to set
	 */
	public void setLineStrings(ArrayList<LineString> lineStrings) {
		this.lineStrings = lineStrings;
	}

	/**
	 * Returns the the {@link Point2D}s of this {@link XmlParser} as
	 * {@link ArrayList}.
	 *
	 * @return the points as {@link ArrayList} of {@link Point2D}s.
	 */
	public ArrayList<Point2D> getPoints() {
		return points;
	}

	/**
	 * Sets the {@link ArrayList} of {@link Point2D}s of this {@link XmlParser}.
	 *
	 * @param points
	 *            the points to set
	 */
	public void setPoints(ArrayList<Point2D> points) {
		this.points = points;
	}

	// OTHERS
}

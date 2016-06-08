package draw.drawer;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.lowagie.text.pdf.PdfArray;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfStructureElement;

import draw.DrawCollection;
import draw.DrawElement;
import draw.WebServiceDrawCollection;
import draw.geo.DrawLineString;
import draw.geo.DrawPoint;
import draw.geo.DrawPolygon;
import draw.style.WebServiceStyle;
import iText.GeospatialPDF;
import resources.PointAppearance;

/**
 * Class to draw the {@link DrawElement}s that are used in an
 * {@link GeospatialPDF}.
 * 
 * @author DaGri
 * @since 09.05.2016
 */
public class WebServiceDrawer extends Drawer {

	// ATTRIBUTES

	/**
	 * The {@link WebServiceStyle} to be used when drawing the
	 * {@link DrawElement}.
	 */
	private WebServiceStyle style = new WebServiceStyle();

	/**
	 * The {@link Logger} used to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Empty constructor for a {@link WebServiceDrawer}.
	 */
	public WebServiceDrawer() {
		super();
	}

	/**
	 * Constructor for a {@link WebServiceDrawer} using a {@link PdfContentByte}
	 * and a {@link PdfStructureElement} to draw tagged {@link DrawElement}s
	 * directly into the structure of a PDF file.
	 * 
	 * @param contByte
	 *            the {@link PdfContentByte} to use
	 * @param top
	 *            the {@link PdfStructureElement} to use
	 */
	public WebServiceDrawer(PdfContentByte contByte, PdfStructureElement top) {
		super(contByte, top);
		LOG.info("CONTENTBYTE AND TOP SET");
	}
	// METHODS IMPLEMENTED FROM SUPERCLASS

	/**
	 * Draws all web service {@link DrawElement}s of the given
	 * {@link WebServiceDrawCollection}.
	 *
	 * @param dc
	 *            the {@link WebServiceDrawCollection}
	 */
	@Override
	public void drawAll(DrawCollection dc) {
		LOG.info("DRAWING ALL...");
		if (dc instanceof WebServiceDrawCollection) {
			this.setStyle((WebServiceStyle) dc.getStyle());
			LOG.info("DRAWING POLYGONS...");
			this.drawPolygons(((WebServiceDrawCollection) dc).getPolygons());
			LOG.info("FINISHED: DRAWING POLYGONS");
			LOG.info("DRAWING LINESTINGS...");
			this.drawLineStrings(((WebServiceDrawCollection) dc).getLinestrings());
			LOG.info("FINISHED: DRAWING LINESTRINGS");
			LOG.info("DRAWING POINTS...");
			this.drawPoints(((WebServiceDrawCollection) dc).getPoints());
			LOG.info("FINISHED: DRAWING POINTS");
			LOG.info("FINISHED: DRAWING ALL");
		} else {
			LOG.severe("DRAWCOLLECTION IS NOT A WEBSERVICE DRAW COLLECTION");
		}
	}

	// METHODS

	/**
	 * Draws all {@link DrawPoint}s of the {@link WebServiceDrawCollection} dc:
	 * Hands the {@link ArrayList} of {@link DrawPoint}s through.
	 *
	 * @param dc
	 *            the {@link WebServiceDrawCollection} to draw the
	 *            {@link DrawPoint}s from
	 */
	public void drawPoints(WebServiceDrawCollection dc) {
		// HAND THE DRAWPOINT ARRAYLIST THROUGH
		this.drawPoints(dc.getPoints());
	}

	/**
	 * Draws all {@link DrawLineString}s of the {@link WebServiceDrawCollection}
	 * dc: Hands the {@link ArrayList} of {@link DrawLineString}s through.
	 *
	 * @param dc
	 *            the {@link WebServiceDrawCollection} to draw the
	 *            {@link DrawLineString}s from
	 */
	public void drawLineStrings(WebServiceDrawCollection dc) {
		// HAND THE DRAWLINESTING ARRAYLIST THROUGH
		this.drawLineStrings(dc.getLinestrings());
	}

	/**
	 * Draws all {@link DrawPolygon}s of the {@link WebServiceDrawCollection}
	 * dc: Hands the {@link ArrayList} of {@link DrawPolygon}s through.
	 *
	 * @param dc
	 *            the {@link WebServiceDrawCollection} to draw the
	 *            {@link DrawPolygon}s from
	 */
	public void drawPolygons(WebServiceDrawCollection dc) {
		// HAND THE DRAWPOLYGON ARRAYLIST THROUGH
		this.drawPolygons(dc.getPolygons());
	}

	/**
	 * Draws an {@link ArrayList} of {@link DrawPoint} into the
	 * {@link GeospatialPDF}.
	 *
	 * @param points
	 *            the {@link ArrayList} of {@link DrawPoint}s to draw
	 */
	private void drawPoints(ArrayList<DrawPoint> points) {
		// FOR EVERY DRAWPOINT
		for (int a = 0; a < points.size(); a++) {
			// DRAW THE DRAWPOINT
			this.drawPoint2D(points.get(a));
		}
	}

	/**
	 * Draws a {@link DrawLineString} into the {@link GeospatialPDF}.
	 *
	 * @param lineStrings
	 *            the {@link ArrayList} of {@link DrawLineString}s to draw
	 */
	private void drawLineStrings(ArrayList<DrawLineString> lineStrings) {
		// FOR EVERY DRAWLINESTING
		for (int a = 0; a < lineStrings.size(); a++) {
			// DRAW THE DRAWLINESTING
			this.drawLineString(lineStrings.get(a));
		}
	}

	/**
	 * Draws an {@link ArrayList} of {@link DrawPolygon}s into a
	 * {@link GeospatialPDF}.
	 *
	 * @param polygons
	 *            the {@link ArrayList} of {@link DrawPolygon}s to draw
	 */
	private void drawPolygons(ArrayList<DrawPolygon> polygons) {
		for (int a = 0; a < polygons.size(); a++) {
			this.drawPolygon(polygons.get(a));
		}
	}

	// DRAWING METHODS

	/**
	 * Draws a {@link DrawPoint} into the {@link GeospatialPDF}.
	 *
	 * @param p
	 *            the {@link DrawPoint} to draw
	 */
	private void drawPoint2D(DrawPoint p) {
		// CATCH IF THERE IS NO POLYGON
		if (p == null) {
			LOG.warning("CATCHED: DRAWPOINT IS NULL");
			return;
		}

		LOG.info("DRAWING DRAWPOINT...");
		LOG.info("CREATING DICTIONARYS...");

		this.getContByte().saveState();

		PdfDictionary properties = new PdfDictionary();

		properties.put(PdfName.O, PdfName.USERPROPERTIES);

		PdfStructureElement e = new PdfStructureElement(this.getTop(), new PdfName("Point2D"));

		PdfArray array = new PdfArray();

		PdfDictionary dic1 = new PdfDictionary();

		dic1.put(PdfName.N, new PdfName("Northing"));
		dic1.put(PdfName.V, new PdfNumber(p.getPoint().getNorthing()));

		PdfDictionary dic2 = new PdfDictionary();

		dic2.put(PdfName.N, new PdfName("Easting"));
		dic2.put(PdfName.V, new PdfNumber(p.getPoint().getEasting()));

		PdfDictionary dic3 = new PdfDictionary();

		dic3.put(PdfName.N, new PdfName("EPSG"));
		dic3.put(PdfName.V, new PdfNumber(p.getPoint().getCoordSystem().getEPSG()));

		array.add(dic1);
		array.add(dic2);
		array.add(dic3);

		properties.put(PdfName.P, array);

		e.put(PdfName.A, properties);

		LOG.info("FINISHED: CREATING DICTIONARYS");
		LOG.info("STARTING MARKED CONTENT...");
		this.getContByte().beginMarkedContentSequence(e);

		// CATCH IF THE STYLE IS NULL
		if (this.getStyle() == null) {
			this.setStyle(new WebServiceStyle());
			LOG.warning("STYLE WAS NULL");
		}

		// SET THE LINE WIDTH ACCORDING TO THE POINT STYLE
		this.getContByte().setLineWidth(this.getStyle().getPoint2dLineweight());
		// SET THE FILL COLOR ACCORDING TO THE POINT STYLE
		this.getContByte().setColorFill(this.getStyle().getPoint2dFillColor());
		// SET THE STROKE COLOR ACCORDING TO THE POINT STYLE
		this.getContByte().setColorStroke(this.getStyle().getPoint2dBorderColor());

		// IF THE STYLE CONTAINS A ICON TO BE DISPLAYED FOR THE DRAWPOINT
		if (this.getStyle().getPointIcon() != null) {
			LOG.info("POINT SHALL BE DISPLAYED WITH AN ICON");
			LOG.info("DRAWING ICON...");
			// TODO : ICON MALEN
			LOG.info("FINISHED: DRAWING ICON");
		} else if (this.getStyle().getPointAppearance() != null) {

			if (this.getStyle().getPointAppearance() == PointAppearance.CIRCLE) {
				LOG.info("POINT SHALL BE AN CIRCLE. DRAWING...");
				// TODO : IST DAS ZENTRIERT, ODER VON DER POSITION AUS GEMALT
				// NACH LINKS OBEN?
				this.getContByte().circle(p.getPdfCoord().getX(), p.getPdfCoord().getY(),
						this.getStyle().getPointRadius());
				this.getContByte().closePath();
				if (this.getStyle().isPoint2dFilled() == true) {
					LOG.info("POINT SHALL BE FILLED");
					this.getContByte().fill();
				}
			} else if (this.getStyle().getPointAppearance() == PointAppearance.CROSS) {
				LOG.info("POINT SHALL BE AN CROSS. DRAWING...");

				this.getContByte().moveTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY());
				this.getContByte().lineTo(p.getPdfCoord().getX() + this.getStyle().getPointRadius(),
						p.getPdfCoord().getY());
				this.getContByte().moveTo(p.getPdfCoord().getX(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX(),
						p.getPdfCoord().getY() + this.getStyle().getPointRadius());

			} else if (this.getStyle().getPointAppearance() == PointAppearance.SQUARE) {
				LOG.info("POINT SHALL BE AN SQUARE. DRAWING...");

				this.getContByte().moveTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() + this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() + this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() + this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() + this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());

				this.getContByte().closePath();
				if (this.getStyle().isPoint2dFilled() == true) {
					LOG.info("POINT SHALL BE FILLED");
					this.getContByte().fill();
				}
			} else if (this.getStyle().getPointAppearance() == PointAppearance.STAR) {
				LOG.info("POINT SHALL BE AN STAR. DRAWING...");

				// TODO : STERN MALEN
				this.getContByte().closePath();
				if (this.getStyle().isPoint2dFilled() == true) {
					LOG.info("POINT SHALL BE FILLED");
					this.getContByte().fill();
				}
			} else if (this.getStyle().getPointAppearance() == PointAppearance.TRIANGLE) {
				LOG.info("POINT SHALL BE AN TRIANGLE. DRAWING...");

				this.getContByte().moveTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX(),
						p.getPdfCoord().getY() + this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() + this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());

				this.getContByte().closePath();
				if (this.getStyle().isPoint2dFilled() == true) {
					LOG.info("POINT SHALL BE FILLED");
					this.getContByte().fill();
				}
			} else if (this.getStyle().getPointAppearance() == PointAppearance.X) {
				LOG.info("POINT SHALL BE AN X. DRAWING...");

				this.getContByte().moveTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() + this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() + this.getStyle().getPointRadius());
				this.getContByte().moveTo(p.getPdfCoord().getX() - this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() + this.getStyle().getPointRadius());
				this.getContByte().lineTo(p.getPdfCoord().getX() + this.getStyle().getPointRadius(),
						p.getPdfCoord().getY() - this.getStyle().getPointRadius());
			}
		} else {
			LOG.info("NO ICON OR POINTAPPEARANCE GIVEN. DRAWING STANDARD POINT");
			this.getContByte().circle(p.getPdfCoord().getX(), p.getPdfCoord().getY(), this.getStyle().getPointRadius());
		}
		this.getContByte().stroke();

		LOG.info("ENDING MARKED CONTENT");
		this.getContByte().endMarkedContentSequence();

		this.getContByte().restoreState();

		LOG.info("FINISHED: DRAWING DRAWPOINT");

	}

	/**
	 * Draws a {@link DrawLineString} into the {@link GeospatialPDF}.
	 *
	 * @param ls
	 *            the {@link DrawLineString} to draw
	 */
	private void drawLineString(DrawLineString ls) {
		// CATCH IF THERE IS NO POLYGON
		if (ls == null)
			return;
		LOG.info("DRAWING LINESTRING...");
		LOG.info("CREATING DICTIONARYS...");

		this.getContByte().saveState();

		PdfDictionary properties = new PdfDictionary();

		properties.put(PdfName.O, PdfName.USERPROPERTIES);

		PdfStructureElement e = new PdfStructureElement(this.getTop(), new PdfName("LineString"));

		PdfArray array = new PdfArray();

		PdfDictionary dic1 = new PdfDictionary();

		dic1.put(PdfName.N, new PdfName("Basepoint count"));
		dic1.put(PdfName.V, new PdfNumber(ls.getPdfCoords().size()));

		PdfDictionary dic2 = new PdfDictionary();

		dic2.put(PdfName.N, new PdfName("Geo Length"));
		dic2.put(PdfName.V, new PdfNumber(ls.getLineString().getGeoLength()));

		array.add(dic1);
		array.add(dic2);

		properties.put(PdfName.P, array);

		e.put(PdfName.A, properties);

		LOG.info("FINISHED: CREATING DICTIONARYS");
		LOG.info("STARTING MARKED CONTENT...");
		this.getContByte().beginMarkedContentSequence(e);

		if (this.getStyle() == null) {
			this.setStyle(new WebServiceStyle());
		}

		// SET THE LINE WIDTH ACCORDING TO THE LINE STYLE
		this.getContByte().setLineWidth(this.getStyle().getLineStringLineweight());
		// SET THE LINE COLOR ACCORDING TO THE LINE STYLE
		this.getContByte().setColorStroke(this.getStyle().getLineStringColor());

		if (ls.getPdfCoords().size() > 0) {
			// MOVE TO THE FIRST POSITION
			this.getContByte().moveTo(ls.getPdfCoords().get(0).getX(), ls.getPdfCoords().get(0).getY());
			// ITERATE OVER THE CONTAINED PDF COORDIANTES
			for (int a = 0; a < ls.getPdfCoords().size(); a++) {
				this.getContByte().lineTo(ls.getPdfCoords().get(a).getX(), ls.getPdfCoords().get(a).getY());
			}
		}

		this.getContByte().stroke();

		LOG.info("ENDING MARKED CONTENT");
		this.getContByte().endMarkedContentSequence();

		this.getContByte().restoreState();
		LOG.info("FINISHED: DRAWING DRAWLINESTING");
	}

	/**
	 * Draws a {@link DrawPolygon} into the {@link GeospatialPDF}.
	 *
	 * @param p
	 *            the {@link DrawPoint} to draw
	 */
	private void drawPolygon(DrawPolygon p) {
		// CATCH IF THERE IS NO POLYGON
		if (p == null)
			return;
		LOG.info("DRAWING POLYGON");
		LOG.info("CREATING DICTIONARYS...");

		this.getContByte().saveState();

		PdfDictionary properties = new PdfDictionary();

		properties.put(PdfName.O, PdfName.USERPROPERTIES);

		PdfStructureElement e = new PdfStructureElement(this.getTop(), new PdfName("Polygon"));

		PdfArray array = new PdfArray();

		PdfDictionary dic1 = new PdfDictionary();

		dic1.put(PdfName.N, new PdfName("Basepoint count"));
		dic1.put(PdfName.V, new PdfNumber(p.getPdfCoords().size()));

		PdfDictionary dic2 = new PdfDictionary();

		dic2.put(PdfName.N, new PdfName("Geo Length"));
		dic2.put(PdfName.V, new PdfNumber(p.getPolygon().getGeoLength()));

		array.add(dic1);
		array.add(dic2);

		properties.put(PdfName.P, array);

		e.put(PdfName.A, properties);
		LOG.info("FINISHED: CREATING DICTIONARYS");
		LOG.info("STARTING MARKED CONTENT...");

		this.getContByte().beginMarkedContentSequence(e);

		if (this.getStyle() == null) {
			this.setStyle(new WebServiceStyle());
		}
		// SET THE POLYGON LINE WIDTH ACCORDING TO THE STYLE
		this.getContByte().setLineWidth(this.getStyle().getPolygonLineweight());
		// SET THE POLYGON FILL COLOR ACCORDING TO THE STYLE
		this.getContByte().setColorFill(this.getStyle().getPolygonFillColor());
		// SET THE POLYGON STROKE COLOR ACCORDING TO THE STYLE
		this.getContByte().setColorStroke(this.getStyle().getPolygonBorderColor());

		if (p.getPdfCoords().size() > 0) {
			// MOVE TO THE FIRST POSITION
			this.getContByte().moveTo(p.getPdfCoords().get(0).getX(), p.getPdfCoords().get(0).getY());
			// ITERATE OVER THE CONTAINED PDF COORDIANTES
			for (int a = 0; a < p.getPdfCoords().size(); a++) {
				this.getContByte().lineTo(p.getPdfCoords().get(a).getX(), p.getPdfCoords().get(a).getY());
			}
			// MAKE SURE THE POLYGON WILL BE CLOSED
			this.getContByte().lineTo(p.getPdfCoords().get(0).getX(), p.getPdfCoords().get(0).getY());
		}

		this.getContByte().closePath();

		// IF THE POLYGONS SHALL BE FILLED
		if (this.getStyle().isPolygonFilled() == true) {
			LOG.info("FILLING POLYGON");
			this.getContByte().fill();
		}

		this.getContByte().stroke();

		LOG.info("ENDING MARKED CONTENT");
		this.getContByte().endMarkedContentSequence();

		this.getContByte().restoreState();
		LOG.info("FINISHED: DRAWING DRAWPOLYGON");
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link WebServiceStyle} of this {@link WebServiceDrawer}.
	 *
	 * @return the style as {@link WebServiceStyle}
	 */
	public WebServiceStyle getStyle() {
		return style;
	}

	/**
	 * Sets the {@link WebServiceStyle}.
	 *
	 * @param style
	 *            the {@link WebServiceStyle} to set
	 */
	public void setStyle(WebServiceStyle style) {
		this.style = style;
	}

	// OTHERS
}

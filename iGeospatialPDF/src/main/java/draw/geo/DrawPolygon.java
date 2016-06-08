package draw.geo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import draw.DrawElement;
import geo.Polygon;
import resources.PdfCoordinate;

/**
 * Class to represent a PDF-printable {@link Polygon}. This class extends the
 * {@link DrawElement} and owns a {@link Polygon} that provides the geographical
 * component.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class DrawPolygon extends DrawElement {

	// ATTRIBUTES

	/**
	 * The {@link Polygon} contained in this {@link DrawPolygon}, that provides
	 * the geographic source data for the drawing.
	 */
	private Polygon polygon;

	/**
	 * The {@link ArrayList} of {@link PdfCoordinate}, used to store the
	 * coordinates of the base points of this {@link DrawPolygon} inside the PDF
	 * file.
	 */
	private ArrayList<PdfCoordinate> pdfCoords = new ArrayList<>();

	/**
	 * The {@link Logger} to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link DrawPolygon} using a {@link Polygon} that shall
	 * be drawn if this {@link DrawPolygon} is drawn.
	 * 
	 * @param polygon
	 *            the {@link Polygon} to display
	 */
	public DrawPolygon(Polygon polygon) {
		super();
		// SET THE LOGGING LEVEL
		LOG.setLevel(Level.SEVERE);
		// SET THE INTERNAL POLYGON
		this.setPolygon(polygon);
		LOG.info("POLYGON SET");
		// CALCULATE THE PDF COORDINATES FROM THE POLYGON POINT2DS
		LOG.info("CALUCLATING THE PDF COORDINATES...");
		this.convertToPdfSystem();
		LOG.info("FINISHED: CALUCLATING THE PDF COORDINATES");
	}

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#reduce(double, double)
	 */
	@Override
	public void reduce(double northing, double easting) {
		// FOR ALL STORED PDFCOORDIANTES
		for (int a = 0; a < this.getPdfCoords().size(); a++) {
			// REDUCE THE X BY THE GIVEN EASTING
			this.getPdfCoords().get(a).setX((float) (this.getPdfCoords().get(a).getX() - easting));
			// REDUCE THE Y BY THE GIVEN NORTHING
			this.getPdfCoords().get(a).setY((float) (this.getPdfCoords().get(a).getY() - northing));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#turn(double)
	 */
	@Override
	public void turn(double angle) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#scale(double)
	 */
	@Override
	public void scale(double factor) {
		// FOR ALL STORED PDFCOORDIANTES
		for (int a = 0; a < this.getPdfCoords().size(); a++) {
			// SCALE THE X WITH THE GIVEN FACTOR
			this.getPdfCoords().get(a).setX((float) (this.getPdfCoords().get(a).getX() * factor));
			// SCALE THE Y WITH THE GIVEN FACTOR
			this.getPdfCoords().get(a).setY((float) (this.getPdfCoords().get(a).getY() * factor));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#convertToPdfSystem()
	 */
	@Override
	public void convertToPdfSystem() {
		// FOR EVERY POINT2D OF THE POLYGON
		for (int a = 0; a < this.getPolygon().getPoints().size(); a++) {
			// ADD A NEW PDFCOORDIANTE
			this.getPdfCoords().add(
					// NEW PDFCOORDIANTE
					new PdfCoordinate(
							// GET THE EASTING OF THE ACTUAL POINT FOR THE X
							// VALUE
							(float) (this.getPolygon().getPoints().get(a).getEasting()),
							// GET THE NORTHING OF THE ACTUAL POINT FOR THE Y
							// VALUE
							(float) (this.getPolygon().getPoints().get(a).getNorthing())));
		}
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link Polygon} of this {@link DrawPolygon}.
	 *
	 * @return the polygon as {@link Polygon}
	 */
	public Polygon getPolygon() {
		return polygon;
	}

	/**
	 * Sets the {@link Polygon} of this {@link DrawPolygon}.
	 *
	 * @param polygon
	 *            the polygon to set
	 */
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	/**
	 * Returns the {@link ArrayList} of {@link PdfCoordinate}s of this
	 * {@link DrawPolygon}.
	 *
	 * @return the pdfCoords as {@link ArrayList} of {@link PdfCoordinate}s
	 */
	public ArrayList<PdfCoordinate> getPdfCoords() {
		return pdfCoords;
	}

	/**
	 * Sets the {@link ArrayList} of {@link PdfCoordinate}s of this
	 * {@link DrawPolygon}.
	 *
	 * @param pdfCoords
	 *            the pdfCoords to set
	 */
	public void setPdfCoords(ArrayList<PdfCoordinate> pdfCoords) {
		this.pdfCoords = pdfCoords;
	}

	// OTHERS
}

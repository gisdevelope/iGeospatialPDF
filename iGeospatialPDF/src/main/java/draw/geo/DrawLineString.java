package draw.geo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import draw.DrawElement;
import geo.LineString;
import geo.Polygon;
import resources.PdfCoordinate;
import resources.PdfCoordinateCalculator;

/**
 * Class to represent a PDF-printable {@link LineString}. This class extends the
 * {@link DrawElement} and owns a {@link LineString} that provides the
 * geographical component.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class DrawLineString extends DrawElement {

	// ATTRIBUTES

	/**
	 * The {@link LineString} contained in this {@link DrawLineString}, that
	 * provides the geographic source data for the drawing.
	 */
	private LineString lineString;

	/**
	 * The {@link ArrayList} of {@link PdfCoordinate}, used to store the
	 * coordinates of the base points of this {@link DrawLineString} inside the
	 * PDF file.
	 */
	private ArrayList<PdfCoordinate> pdfCoords = new ArrayList<>();

	/**
	 * The {@link Logger} to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link DrawLineString} using a {@link LineString} that
	 * shall be drawn if this {@link DrawLineString} is drawn.
	 * 
	 * @param lineString
	 *            the {@link DrawLineString} to display
	 */
	public DrawLineString(LineString lineString) {
		super();
		// SET THE LOGGING LEVEL
		LOG.setLevel(Level.SEVERE);
		this.lineString = lineString;
		LOG.info("LINESTRING SET");
		LOG.info("CALUCLATING THE PDF COORDINATE...");
		this.convertToPdfSystem();
		LOG.info("FINISHED: CALUCLATING THE PDF COORDINATE");
	}

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#reduce(double, double)
	 */
	@Override
	public void reduce(double northing, double easting) {
		// FOR ALL STORED PDFCOORDINATES
		for (int a = 0; a < this.getPdfCoords().size(); a++) {
			PdfCoordinateCalculator.getInstance().reduce(this.getPdfCoords().get(a), northing, easting);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#turn(double)
	 */
	@Override
	public void turn(double angle) {
		// FOR ALL STORED PDFCOORDINATES
		for (int a = 0; a < this.getPdfCoords().size(); a++) {
			PdfCoordinateCalculator.getInstance().turn(this.getPdfCoords().get(a), 0, 0, angle);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#scale(double)
	 */
	@Override
	public void scale(double factor) {
		// FOR ALL STORED PDFCOORDINATES
		for (int a = 0; a < this.getPdfCoords().size(); a++) {
			PdfCoordinateCalculator.getInstance().scale(this.getPdfCoords().get(a), factor);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#convertToPdfSystem()
	 */
	@Override
	public void convertToPdfSystem() {
		for (int a = 0; a < this.getLineString().getPoints().size(); a++) {
			this.getPdfCoords().add(new PdfCoordinate((float) (this.getLineString().getPoints().get(a).getNorthing()),
					(float) (this.getLineString().getPoints().get(a).getEasting())));
		}
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link LineString} of this {@link DrawLineString}.
	 *
	 * @return the lineString as {@link LineString}
	 */
	public LineString getLineString() {
		return lineString;
	}

	/**
	 * Sets the {@link Polygon} of this {@link DrawLineString}.
	 *
	 * @param lineString
	 *            the lineString to set
	 */
	public void setLineString(LineString lineString) {
		this.lineString = lineString;
	}

	/**
	 * Returns the {@link ArrayList} of {@link PdfCoordinate}s of this
	 * {@link DrawLineString}.
	 *
	 * @return the pdfCoords as {@link ArrayList} of {@link PdfCoordinate}s
	 */
	public ArrayList<PdfCoordinate> getPdfCoords() {
		return pdfCoords;
	}

	/**
	 * Sets the {@link ArrayList} of {@link PdfCoordinate}s of this
	 * {@link DrawLineString}.
	 *
	 * @param pdfCoords
	 *            the pdfCoords to set
	 */
	public void setPdfCoords(ArrayList<PdfCoordinate> pdfCoords) {
		this.pdfCoords = pdfCoords;
	}

	// OTHERS
}

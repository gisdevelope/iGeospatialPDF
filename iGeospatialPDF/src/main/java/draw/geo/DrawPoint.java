package draw.geo;

import java.util.logging.Level;
import java.util.logging.Logger;

import draw.DrawElement;
import geo.Point2D;
import resources.PdfCoordinate;
import resources.PdfCoordinateCalculator;

/**
 * Class to represent a PDF-printable {@link Point2D}. This class extends the
 * {@link DrawElement} and owns a {@link Point2D} that provides the geographical
 * component.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class DrawPoint extends DrawElement {

	// ATTRIBUTES

	/**
	 * The {@link Point2D} of this {@link DrawElement}.
	 */
	private Point2D point;

	/**
	 * The {@link PdfCoordinate} to store the position inside the PDF document.
	 */
	private PdfCoordinate pdfCoord;

	/**
	 * The {@link Logger} to log events.
	 */
	Logger LOG = Logger.getLogger(this.getClass().getCanonicalName());

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link DrawPoint} using a {@link Point2D} for the
	 * geographic component.
	 * 
	 * @param point
	 *            the {@link Point2D} to set
	 */
	public DrawPoint(Point2D point) {
		super();
		// SET THE LOGGING LEVEL
		LOG.setLevel(Level.SEVERE);
		// SET THE INTERNAL POINT
		this.point = point;
		LOG.info("POINT SET");
		// CALCULATE THE PDF COORDINATES FROM THE POLYGON POINT2DS
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
		PdfCoordinateCalculator.getInstance().redurce(this.getPdfCoord(), northing, easting);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#turn(double)
	 */
	@Override
	public void turn(double angle) {
		PdfCoordinateCalculator.getInstance().turn(this.getPdfCoord(), 0, 0, angle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#scale(double)
	 */
	@Override
	public void scale(double factor) {
		PdfCoordinateCalculator.getInstance().scale(this.getPdfCoord(), factor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#convertToPdfSystem()
	 */
	@Override
	public void convertToPdfSystem() {
		// SET THE PDFCOORDINATE
		this.setPdfCoord(
				// NEW PDFCOORDINATE
				new PdfCoordinate(
						// USE THE EASTING
						(float) this.point.getEasting(),
						// USE THE NORTHING
						(float) this.point.getNorthing()));
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link Point2D} of this {@link DrawPoint}.
	 *
	 * @return the point as {@link DrawPoint}
	 */
	public Point2D getPoint() {
		return point;
	}

	/**
	 * Sets the {@link Point2D} of this {@link DrawPoint}.
	 *
	 * @param point
	 *            the point to set
	 */
	public void setPoint(Point2D point) {
		this.point = point;
	}

	/**
	 * Returns the {@link PdfCoordinate} of this {@link DrawPoint}.
	 *
	 * @return the pdfCoord as {@link PdfCoordinate}
	 */
	public PdfCoordinate getPdfCoord() {
		return pdfCoord;
	}

	/**
	 * Sets the {@link PdfCoordinate} of this {@link DrawPoint}.
	 *
	 * @param pdfCoord
	 *            the pdfCoord to set
	 */
	public void setPdfCoord(PdfCoordinate pdfCoord) {
		this.pdfCoord = pdfCoord;
	}

	// OTHERS

}

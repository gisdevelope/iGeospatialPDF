package draw.geo;

import java.util.ArrayList;

import draw.DrawElement;
import geo.Polygon;
import resources.PDFCoordinate;

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
	 * The {@link ArrayList} of {@link PDFCoordinate}, used to store the
	 * coordinates of the base points of this {@link DrawPolygon} inside the PDF
	 * file.
	 */
	private ArrayList<PDFCoordinate> pdfCoords;

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
		this.setPolygon(polygon);

	}
	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#reduce(double, double)
	 */
	@Override
	public void reduce(double northing, double easting) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}
	
	/* (non-Javadoc)
	 * @see draw.DrawElement#convertToPdfSystem()
	 */
	@Override
	public void convertToPdfSystem() {
		// TODO Auto-generated method stub
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
	 * Returns the {@link ArrayList} of {@link PDFCoordinate}s of this
	 * {@link DrawPolygon}.
	 *
	 * @return the pdfCoords as {@link ArrayList} of {@link PDFCoordinate}s
	 */
	public ArrayList<PDFCoordinate> getPdfCoords() {
		return pdfCoords;
	}

	/**
	 * Sets the {@link ArrayList} of {@link PDFCoordinate}s of this
	 * {@link DrawPolygon}.
	 *
	 * @param pdfCoords
	 *            the pdfCoords to set
	 */
	public void setPdfCoords(ArrayList<PDFCoordinate> pdfCoords) {
		this.pdfCoords = pdfCoords;
	}

	// OTHERS
}

package draw.geo;

import java.util.ArrayList;

import draw.DrawElement;
import geo.LineString;
import geo.Polygon;
import resources.PDFCoordinate;

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
	 * The {@link ArrayList} of {@link PDFCoordinate}, used to store the
	 * coordinates of the base points of this {@link DrawLineString} inside the
	 * PDF file.
	 */
	private ArrayList<PDFCoordinate> pdfCoords;

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
		this.lineString = lineString;
	}

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#reduce(double, double)
	 */
	@Override
	public void reduce(double northing, double easting) {
		for (int a = 0; a < this.getPdfCoords().size(); a++) {
			PDFCoordinate temp = this.getPdfCoords().get(a);
			temp.setX((float) (temp.getX() - northing));
			temp.setY((float) (temp.getY() - easting));
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
		for (int a = 0; a < this.getPdfCoords().size(); a++) {
			PDFCoordinate temp = this.getPdfCoords().get(a);
			temp.setX((float) (temp.getX() * factor));
			temp.setY((float) (temp.getY() * factor));
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
			this.getPdfCoords().add(new PDFCoordinate((float) (this.getLineString().getPoints().get(a).getNorthing()),
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
	 * Returns the {@link ArrayList} of {@link PDFCoordinate}s of this
	 * {@link DrawLineString}.
	 *
	 * @return the pdfCoords as {@link ArrayList} of {@link PDFCoordinate}s
	 */
	public ArrayList<PDFCoordinate> getPdfCoords() {
		return pdfCoords;
	}

	/**
	 * Sets the {@link ArrayList} of {@link PDFCoordinate}s of this
	 * {@link DrawLineString}.
	 *
	 * @param pdfCoords
	 *            the pdfCoords to set
	 */
	public void setPdfCoords(ArrayList<PDFCoordinate> pdfCoords) {
		this.pdfCoords = pdfCoords;
	}

	// OTHERS
}

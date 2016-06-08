package resources;

import com.lowagie.text.Document;

/**
 * Class that represents a coordinate inside a PDF-{@link Document}.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class PdfCoordinate {

	// ATTRIBUTES

	/**
	 * The X-coordinate of this {@link PdfCoordinate} inside a PDF-
	 * {@link Document}.
	 */
	private float x;

	/**
	 * The Y-coordinate of this {@link PdfCoordinate} inside a PDF -
	 * {@link Document}.
	 */
	private float y;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link PdfCoordinate} using a float value for the X and
	 * the Y component.
	 * 
	 * @param x
	 *            the X value to set
	 * @param y
	 *            the Y value to set
	 */
	public PdfCoordinate(float x, float y) {
		super();
		this.setX(x);
		this.setY(y);
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the X value of this {@link PdfCoordinate}.
	 *
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the X value of this {@link PdfCoordinate}.
	 *
	 * @param x
	 *            the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Returns the Y value of this {@link PdfCoordinate}.
	 *
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the Y value of this {@link PdfCoordinate}.
	 *
	 * @param y
	 *            the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	// OTHERS
}

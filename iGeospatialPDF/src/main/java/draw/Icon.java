package draw;

import java.awt.image.BufferedImage;

import com.lowagie.text.Image;

import draw.geo.DrawPoint;

/**
 * Class to represent and manage a {@link Icon} that can be used instead of a
 * {@link DrawPoint} signature.
 * 
 * @author DaGri
 * @since 09.05.2016
 */
public class Icon {

	// ATTRIBUTES

	/**
	 * The {@link BufferedImage} that can be displayed as an {@link Icon}.
	 */
	private BufferedImage image;

	/**
	 * The scaling factor for the {@link BufferedImage}.
	 */
	private double scaleFactor = 1.0;

	/**
	 * The boolean that indicates if the {@link Icon} shall be displayed
	 * centered over the {@link DrawPoint} or not.
	 */
	private boolean centered = false;

	// CONSTRUCTORS

	/**
	 * Constructor for an {@link Icon} that uses a {@link BufferedImage} as
	 * image, a {@link Double} as scaling factor and a {@link Boolean} that
	 * indicates if the {@link Icon} shall be displayed centered over the
	 * {@link DrawPoint}. TODO : BUFFEREDIMAGE ALS UEBERGABE? BESSER INTERN
	 * UEBER PFAD MACHEN UND ABFANGEN WENN ES NICHT KLAPPT?
	 * 
	 * @param image
	 *            the {@link BufferedImage} to set
	 * @param scaleFactor
	 *            the scaling factor to set as {@link Double}
	 * @param centered
	 *            the positioning indicator as {@link Double}
	 */
	public Icon(BufferedImage image, double scaleFactor, boolean centered) {
		super();
		this.image = image;
		this.scaleFactor = scaleFactor;
		this.centered = centered;
	}

	// METHODS

	/**
	 * Returns the {@link BufferedImage} of this {@link Icon} converted to an
	 * iText {@link Image}.
	 *
	 * @return
	 */
	public Image getItextImage() {
		return null;
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link BufferedImage} of this {@link Icon}.
	 *
	 * @return the image as {@link BufferedImage}
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Sets the {@link BufferedImage} of this {@link Icon}.
	 *
	 * @param image
	 *            the {@link BufferedImage} to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Returns the scaling factor of this {@link Icon} as {@link Double}.
	 *
	 * @return the scaleFactor
	 */
	public double getScaleFactor() {
		return scaleFactor;
	}

	/**
	 * Sets the scaling factor of this {@link Icon}.
	 *
	 * @param scaleFactor
	 *            the scaleFactor to set as {@link Double}.
	 */
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	/**
	 * Returns the {@link Boolean} that indicates if the {@link Icon} shall be
	 * displayed centered over the {@link DrawPoint}.
	 *
	 * @return the centered as {@link Boolean}
	 */
	public boolean isCentered() {
		return centered;
	}

	/**
	 * Sets the {@link Boolean} that indicates if the {@link Icon} shall be
	 * displayed centered above the {@link DrawPoint}.
	 *
	 * @param centered
	 *            the centered to set
	 */
	public void setCentered(boolean centered) {
		this.centered = centered;
	}

	// OTHERS
}

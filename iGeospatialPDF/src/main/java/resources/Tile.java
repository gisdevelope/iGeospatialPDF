package resources;

import java.awt.image.BufferedImage;

import geo.BoundingBox;
import mapContent.Map;

/**
 * Class to store a {@link BufferedImage} that contains a part of a {@link Map}
 * image.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class Tile {

	// ATTRIBUTES

	/**
	 * The {@link BufferedImage} of this {@link Tile}.
	 */
	private BufferedImage tileImage;

	/**
	 * The {@link BoundingBox} of this {@link Tile}.
	 */
	private BoundingBox bbox;

	/**
	 * The width of the {@link BufferedImage} of this {@link Tile}.
	 */
	private int imageWidth;

	/**
	 * The height of the {@link BufferedImage} of this {@link Tile}.
	 */
	private int imageHeight;

	// CONSTRUCTORS

	/**
	 * Empty constructor for a {@link Tile}.
	 */
	public Tile() {
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link BufferedImage} of this {@link Tile}.
	 *
	 * @return the tileImage as {@link BufferedImage}
	 */
	public BufferedImage getTileImage() {
		return tileImage;
	}

	/**
	 * Sets the {@link BufferedImage} of this {@link Tile}.
	 *
	 * @param tileImage
	 *            the tileImage to set
	 */
	public void setTileImage(BufferedImage tileImage) {
		this.tileImage = tileImage;
	}

	/**
	 * Returns the {@link BoundingBox} of this {@link Tile}.
	 *
	 * @return the bbox as {@link BoundingBox}
	 */
	public BoundingBox getBbox() {
		return bbox;
	}

	/**
	 * Sets the {@link BoundingBox} of this {@link Tile}
	 *
	 * @param bbox
	 *            the bbox to set
	 */
	public void setBbox(BoundingBox bbox) {
		this.bbox = bbox;
	}

	/**
	 * Returns the width of the {@link BufferedImage} of this {@link Tile} as
	 * {@link Integer}.
	 *
	 * @return the imageWidth as {@link Integer}
	 */
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * Sets the width of the {@link BufferedImage} of this {@link Tile}.
	 *
	 * @param imageWidth
	 *            the imageWidth to set
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * Returns the height of the {@link BufferedImage} of this {@link Tile} as
	 * {@link Integer}.
	 *
	 * @return the imageHeight as {@link Integer}
	 */
	public int getImageHeight() {
		return imageHeight;
	}

	/**
	 * Sets the height of the {@link BufferedImage} of this {@link Tile}.
	 *
	 * @param imageHeight
	 *            the imageHeight to set
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	// OTHERS
}

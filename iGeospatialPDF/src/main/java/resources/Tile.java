package resources;

import java.awt.image.BufferedImage;

import geo.BoundingBox;

/**
 * Class to TODO
 * 
 * @author DaGri
 * @since 03.05.2016
 *
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
	 * The geographical width of this {@link Tile} as {@link Double}.
	 */
	private double widthGeo;

	/**
	 * The geographical height of this {@link Tile} as {@link Double}
	 */
	private double heightGeo;

	/**
	 * The image width of this {@link Tile} as {@link Integer}.
	 */
	private int widthImg;

	/**
	 * The image height of this {@link Tile} as {@link Integer}.
	 */
	private int heightImg;
	
	// CONSTRUCTORS

	// METHODS
	
	//TODO:RECEIVE-Methode
	//TODO:SINNVOLLE KONSTRUKTOREN
	//TODO:STRING SOLLTE GESPEICHERT WERDEN

	// GETTERS AND SETTERS

	/**
	 * Returns the TODO
	 *
	 * @return the tileImage
	 */
	public BufferedImage getTileImage() {
		return tileImage;
	}

	/**
	 * Sets the TODO
	 *
	 * @param tileImage the tileImage to set
	 */
	public void setTileImage(BufferedImage tileImage) {
		this.tileImage = tileImage;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the bbox
	 */
	public BoundingBox getBbox() {
		return bbox;
	}

	/**
	 * Sets the TODO
	 *
	 * @param bbox the bbox to set
	 */
	public void setBbox(BoundingBox bbox) {
		this.bbox = bbox;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the widthGeo
	 */
	public double getWidthGeo() {
		return widthGeo;
	}

	/**
	 * Sets the TODO
	 *
	 * @param widthGeo the widthGeo to set
	 */
	public void setWidthGeo(double widthGeo) {
		this.widthGeo = widthGeo;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the heightGeo
	 */
	public double getHeightGeo() {
		return heightGeo;
	}

	/**
	 * Sets the TODO
	 *
	 * @param heightGeo the heightGeo to set
	 */
	public void setHeightGeo(double heightGeo) {
		this.heightGeo = heightGeo;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the widthImg
	 */
	public int getWidthImg() {
		return widthImg;
	}

	/**
	 * Sets the TODO
	 *
	 * @param widthImg the widthImg to set
	 */
	public void setWidthImg(int widthImg) {
		this.widthImg = widthImg;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the heightImg
	 */
	public int getHeightImg() {
		return heightImg;
	}

	/**
	 * Sets the TODO
	 *
	 * @param heightImg the heightImg to set
	 */
	public void setHeightImg(int heightImg) {
		this.heightImg = heightImg;
	}



	// OTHERS
}

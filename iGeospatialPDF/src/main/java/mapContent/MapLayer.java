package mapContent;

import geo.BoundingBox;

/**
 * Abstract parental class for all children of a {@link MapLayer}.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public abstract class MapLayer {

	// ATTRIBUTES

	/**
	 * The {@link BoundingBox} of this {@link MapLayer} that defines the covered
	 * area of the surface of the earth.
	 */
	private BoundingBox bbox;

	// CONSTRUCTORS

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link BoundingBox} of this {@link MapLayer}.
	 *
	 * @return the bbox as {@link BoundingBox}
	 */
	public BoundingBox getBbox() {
		return bbox;
	}

	/**
	 * Sets the {@link BoundingBox} of this {@link MapLayer}.
	 *
	 * @param bbox
	 *            the bbox to set
	 */
	public void setBbox(BoundingBox bbox) {
		this.bbox = bbox;
	}

	// OTHERS
}

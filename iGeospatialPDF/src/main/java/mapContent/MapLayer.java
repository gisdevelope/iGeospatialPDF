package mapContent;

import java.util.logging.Logger;

import draw.drawer.Drawer;
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

	/**
	 * The {@link Drawer} of this {@link MapLayer}. Shall be a explicit type of
	 * {@link Drawer} for each kind of PDF.
	 */
	private Drawer drawer;
	
	/**
	 * The {@link Logger} used to log.
	 */
	Logger LOG;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link MapLayer} using a {@link BoundingBox}.
	 * 
	 * @param bbox
	 *            the {@link BoundingBox} to use
	 */
	public MapLayer(BoundingBox bbox) {
		this.setBbox(bbox);
	}

	// METHODS

	/**
	 * Method to receive the data of a {@link MapLayer}.
	 */
	public abstract void receive();

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

	/**
	 * Returns the {@link Drawer} of this {@link MapLayer}.
	 *
	 * @return the drawer as {@link Drawer}
	 */
	public Drawer getDrawer() {
		return drawer;
	}

	/**
	 * Sets the {@link Drawer} of this {@link MapLayer}.
	 *
	 * @param drawer
	 *            the drawer to set
	 */
	public void setDrawer(Drawer drawer) {
		this.drawer = drawer;
	}

	// OTHERS
}

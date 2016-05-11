package mapContent;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import geo.BoundingBox;

/**
 * Class to represent a completed {@link Map} with multiple {@link MapLayer}s.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class Map {

	// ATTRIBUTES

	/**
	 * The {@link ArrayList} containing the {@link MapLayer}s of this
	 * {@link Map}.
	 */
	private ArrayList<MapLayer> layers = new ArrayList<>();

	/**
	 * The {@link BoundingBox} that covers all {@link BoundingBox}es of the
	 * {@link MapLayer}s.
	 */
	private BoundingBox overAllBBox;

	/**
	 * The {@link BufferedImage} of this {@link Map} used as background of the
	 * stuff to display.
	 */
	private BufferedImage image;

	// CONSTRUCTORS

	// METHODS

	/**
	 * Adds a {@link MapLayer} to the internal {@link ArrayList} of
	 * {@link MapLayer}s.
	 *
	 * @param layer
	 *            the {@link MapLayer} to add
	 */
	public void addLayer(MapLayer layer) {
		this.getLayers().add(layer);
		this.calcOverAllBBox();
	}

	/**
	 * Counts the in this {@link Map} contained {@link MapLayer}s.
	 *
	 * @return count the count of {@link MapLayer}s
	 */
	public int layerCount() {
		return this.getLayers().size();
	}

	/**
	 * Calculates the {@link BoundingBox} that covers all {@link BoundingBox}es
	 * of the {@link MapLayer}s contained in this {@link Map}.
	 */
	private void calcOverAllBBox() {
		// WENN EIN MAPLAYER VORHANDEN IST
		if (this.getLayers().size() >= 1) {
			// DANN NIMM DIE BOUNDINGBOX DIESES LAYERS ALS REFFERENZ
			this.setOverAllBBox(this.getLayers().get(0).getBbox());
		}
		// FUER JEDEN MAPLAYER DER VORHANDEN IST
		for (int a = 0; a < this.getLayers().size(); a++) {
			// UNTERE LINKE ECKE WEITER LINKS ODER WEITER UNTEN?
			if (this.getLayers().get(a).getBbox().getDownLeft().getLon() < this.getOverAllBBox().getDownLeft().getLon()
					|| this.getLayers().get(a).getBbox().getDownLeft().getLat() < this.getOverAllBBox().getDownLeft()
							.getLat()) {
				// BOUNDINGBOX AUF DEN NEUEN LINKEN UNTEREN PUNKT ERWEITERN
				this.getOverAllBBox().setDownLeft(this.getLayers().get(a).getBbox().getDownLeft());
			}
			// OBERE RECHTE ECKE WEITER RECHTS ODER WEITER OBEN?
			if (this.getLayers().get(a).getBbox().getUpRight().getLon() > this.getOverAllBBox().getUpRight().getLon()
					|| this.getLayers().get(a).getBbox().getUpRight().getLat() > this.getOverAllBBox().getUpRight()
							.getLat()) {
				// BOUNDINGBOX AUF DEN NEUEN RECHTEN OBEREN PUNKT ERWEITERN
				this.getOverAllBBox().setUpRight(this.getLayers().get(a).getBbox().getUpRight());
			}
		}
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link ArrayList} of {@link MapLayer}s, contained in this
	 * {@link Map}.
	 *
	 * @return the layers the {@link ArrayList} of {@link MapLayer}
	 */
	public ArrayList<MapLayer> getLayers() {
		return layers;
	}

	/**
	 * Sets the {@link ArrayList} of {@link MapLayer}s.
	 *
	 * @param layers
	 *            the layers to set
	 */
	public void setLayers(ArrayList<MapLayer> layers) {
		this.layers = layers;
	}

	/**
	 * Returns the over-all- {@link BoundingBox}, that covers all
	 * {@link BoundingBox}es of the contained {@link MapLayer}s.
	 *
	 * @return the overAllBBox the over-all {@link BoundingBox}
	 */
	public BoundingBox getOverAllBBox() {
		return overAllBBox;
	}

	/**
	 * Sets the over-all- {@link BoundingBox}.
	 *
	 * @param overAllBBox
	 *            the overAllBBox to set as {@link BoundingBox}
	 */
	public void setOverAllBBox(BoundingBox bbBox) {
		this.overAllBBox = bbBox;
	}

	/**
	 * Returns the {@link BufferedImage} of this {@link Map} that will be used
	 * as background for the map content.
	 *
	 * @return the image as {@link BufferedImage}
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Sets the {@link BufferedImage} of this {@link Map} that will be used as
	 * background of the map image.
	 *
	 * @param image
	 *            the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	// OTHERS
}

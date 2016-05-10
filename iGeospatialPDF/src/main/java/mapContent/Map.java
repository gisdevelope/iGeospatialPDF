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
	
	DIE MAP BEKOMMT LAYER HINZUGEFUEGT DIE UNTERSCHIEDLICHER ART SEIN KOENNEN
	DIESE ART IST DER MAP ABER VOELLIG EGAL DA SIE DEN MIST NUR IN DIE INTERNE 
	DATENSTRUKTUR PACKT UND FUER DIE EINBINDUNG IN DIE PDF DATEI BEREIT HAELT
	

	// ATTRIBUTES

	/**
	 * TODO
	 */
	private ArrayList<MapLayer> layers = new ArrayList<>();

	/**
	 * TODO
	 */
	private BoundingBox bbBox;

	/**
	 * TODO
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
	}

	/**
	 * TODO
	 *
	 * @return
	 */
	public int layerCount() {
		return this.getLayers().size();
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the TODO
	 *
	 * @return the layers
	 */
	public ArrayList<MapLayer> getLayers() {
		return layers;
	}

	/**
	 * Sets the TODO
	 *
	 * @param layers
	 *            the layers to set
	 */
	public void setLayers(ArrayList<MapLayer> layers) {
		this.layers = layers;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the bbBox
	 */
	public BoundingBox getBbBox() {
		return bbBox;
	}

	/**
	 * Sets the TODO
	 *
	 * @param bbBox
	 *            the bbBox to set
	 */
	public void setBbBox(BoundingBox bbBox) {
		this.bbBox = bbBox;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Sets the TODO
	 *
	 * @param image
	 *            the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	// OTHERS
}

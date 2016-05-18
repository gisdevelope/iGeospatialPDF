package mapContent;

import geo.BoundingBox;

/**
 * Class to represent a {@link WfsLayer} - a layer requested from a Web Feature
 * Service (WFS).
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public class WfsLayer extends MapLayer {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link WfsLayer} using a {@link BoundingBox}.
	 * 
	 * @param bbox
	 */
	public WfsLayer(BoundingBox bbox) {
		super(bbox);
		// TODO Auto-generated constructor stub
	}

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.MapLayer#receive()
	 */
	@Override
	public void receive() {
		// TODO Auto-generated method stub

	}

	// GETTERS AND SETTERS

	// OTHERS
}

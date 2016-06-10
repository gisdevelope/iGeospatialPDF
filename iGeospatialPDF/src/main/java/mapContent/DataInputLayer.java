package mapContent;

import java.util.logging.Logger;

import geo.BoundingBox;

/**
 * Class to represent a {@link DataInputLayer} - a layer that contains other
 * objects. TODO : UPDATE DEFINITION
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class DataInputLayer extends MapLayer {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link DataInputLayer} using a {@link BoundingBox}.
	 * 
	 * @param bbox
	 */
	public DataInputLayer(BoundingBox bbox) {
		super(bbox);
		LOG = Logger.getLogger(this.getClass().getCanonicalName());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.MapLayer#prepareData(double, double, double, double,
	 * double, double)
	 */
	@Override
	public void prepareData(double northingRed, double eastingRed, double angle, double factor) {
		// TODO Auto-generated method stub

	}

	// GETTERS AND SETTERS

	// OTHERS
}

package iText;

import java.util.ArrayList;

import com.lowagie.text.Rectangle;

import mapContent.MapLayer;
import network.NetworkElement;

/**
 * Class to create a {@link NetworkPDF} that can be used to display various
 * kinds of {@link NetworkElement}s.
 * 
 * @author DaGri
 * @since 06.05.2016
 *
 */
public class NetworkPDF extends GeospatialPDF {

	/**
	 * Constructor for a {@link NetworkPDF}.
	 * 
	 * @param pageSize
	 *            the page size to set as {@link Rectangle}
	 */
	public NetworkPDF(Rectangle pageSize) {
		super(pageSize);
	}

	/* (non-Javadoc)
	 * @see iText.GeospatialPDF#createPDF(java.util.ArrayList, com.lowagie.text.Rectangle)
	 */
	@Override
	public void createPDF(ArrayList<MapLayer> layers, Rectangle pageSize) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see iText.GeospatialPDF#createPDF(com.lowagie.text.Rectangle)
	 */
	@Override
	public void createPDF(Rectangle pageSize) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see iText.GeospatialPDF#createPDF()
	 */
	@Override
	public void createPDF() {
		// TODO Auto-generated method stub
		
	}

	// ATTRIBUTES

	// CONSTRUCTORS

	// METHODS

	// GETTERS AND SETTERS

	// OTHERS
}

package iText;

import com.lowagie.text.Rectangle;

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

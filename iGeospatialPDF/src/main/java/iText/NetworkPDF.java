package iText;

import geo.BoundingBox;
import network.NetworkElement;
import resources.PdfPageSize;

/**
 * Class to create a {@link NetworkPDF} that can be used to display various
 * kinds of {@link NetworkElement}s.
 * 
 * @author DaGri
 * @since 06.05.2016
 *
 */
public class NetworkPDF extends GeospatialPDF {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link NetworkPDF} using a {@link PdfPageSize} to set the size of the page.
	 * 
	 * @param pageSize
	 */
	public NetworkPDF(PdfPageSize pageSize, BoundingBox masterBbox) {
		super(pageSize, masterBbox);
		// TODO Auto-generated constructor stub
	}

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see iText.GeospatialPDF#createPDF()
	 */
	@Override
	public void createPDF() {
		// TODO Auto-generated method stub

	}

	// GETTERS AND SETTERS

	// OTHERS
}

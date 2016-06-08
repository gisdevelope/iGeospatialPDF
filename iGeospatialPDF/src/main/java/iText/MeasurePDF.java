package iText;

import resources.PdfPageSize;

/**
 * Class to create a {@link MeasurePDF} that can be used to display various
 * kinds of measurement elements.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class MeasurePDF extends GeospatialPDF {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link MeasurePDF} using a {@link PdfPageSize} to set
	 * the size of the page.
	 * 
	 * @param pageSize
	 *            the {@link PdfPageSize} to use
	 */
	public MeasurePDF(PdfPageSize pageSize) {
		super(pageSize);
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

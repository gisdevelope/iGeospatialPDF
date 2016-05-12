package iText;

import com.lowagie.text.Rectangle;

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
	 * Constructor for a {@link MeasurePDF}.
	 * 
	 * @param pageSize
	 */
	public MeasurePDF(Rectangle pageSize) {
		super(pageSize);
	}

	/* (non-Javadoc)
	 * @see iText.GeospatialPDF#createPDF()
	 */
	@Override
	public void createPDF() {
		// TODO Auto-generated method stub
		
	}

	// METHODS

	// GETTERS AND SETTERS

	// OTHERS
}

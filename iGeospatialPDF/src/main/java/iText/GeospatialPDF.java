package iText;

import com.lowagie.text.Rectangle;

/**
 * Abstract parental class for all geospatial PDFs. This class provides
 * necessary attributes and methods for the creating and working with these
 * PDFs.
 * 
 * @author DaGri
 * @since 04.05.2016
 */
public abstract class GeospatialPDF {

	// ATTRIBUTES

	/**
	 * The page size of the PDF-page to create as {@link Rectangle}.
	 */
	private Rectangle pageSize;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link GeospatialPDF} using a {@link Rectangle} input
	 * to define the size of the page to create.
	 * 
	 * @param pageSize
	 *            the size of the map page
	 */
	public GeospatialPDF(Rectangle pageSize) {
		this.setPageSize(pageSize);
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the map page size as {@link Rectangle}.
	 *
	 * @return the pageSize
	 */
	public Rectangle getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the map page size.
	 *
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Rectangle pageSize) {
		this.pageSize = pageSize;
	}

	// OTHERS
}

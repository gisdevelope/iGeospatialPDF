package iText;

import com.lowagie.text.Rectangle;

import draw.DrawElement;
import draw.drawer.Drawer;

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

	/**
	 * The {@link Drawer} (parental-class) that is used to draw the
	 * {@link DrawElement}s.
	 */
	private Drawer drawer;

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

	/**
	 * Create a PDF file in the file system.
	 */
	// TODO : HIER FEHLEN NOCH UEBRGABEPARAMETER
	abstract public void createPDF();

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

	/**
	 * Returns the {@link Drawer} of this {@link GeospatialPDF}.
	 *
	 * @return the drawer as {@link Drawer}
	 */
	public Drawer getDrawer() {
		return drawer;
	}

	/**
	 * Sets the {@link Drawer} of this {@link GeospatialPDF}.
	 *
	 * @param drawer
	 *            the drawer to set
	 */
	public void setDrawer(Drawer drawer) {
		this.drawer = drawer;
	}

	// OTHERS
}

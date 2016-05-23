package iText;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.lowagie.text.Rectangle;

import draw.DrawElement;
import draw.drawer.Drawer;
import mapContent.MapLayer;

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

	/**
	 * The internal {@link ArrayList} of {@link MapLayer}s that contains the
	 * layers to display in this {@link GeospatialPDF}.
	 */
	private ArrayList<MapLayer> layers = new ArrayList<>();

	/**
	 * The name of the {@link GeospatialPDF} in the file system.
	 */
	private String pdfName = "output/" + System.currentTimeMillis() + ".pdf";

	/**
	 * The {@link Logger} used to log.
	 */
	Logger LOG;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link GeospatialPDF} using a {@link Rectangle} input
	 * to define the size of the page to create.
	 * 
	 * @param pageSize
	 *            the size of the map page
	 */
	public GeospatialPDF(Rectangle pageSize) {
		// CONVERT THE PIXEL AT 72 PDI INTO AN INCH VALUE AND SAVE IT INTO THE
		// RECTANGLE ATTRIBUTE
		int xInch = (int) (pageSize.getWidth() / 72);
		int yInch = (int) (pageSize.getHeight() / 72);
		this.setPageSize(new Rectangle(xInch, yInch));
	}

	/**
	 * Constructor for a {@link GeospatialPDF} using a {@link Rectangle} input
	 * to define the size of the page to create.
	 * 
	 * @param pageSize
	 *            the size of the map page
	 */
	public GeospatialPDF(Rectangle pageSize, String pdfName) {
		// CONVERT THE PIXEL AT 72 PDI INTO AN INCH VALUE AND SAVE IT INTO THE
		// RECTANGLE ATTRIBUTE
		int xInch = (int) (pageSize.getWidth() / 72);
		int yInch = (int) (pageSize.getHeight() / 72);
		this.setPageSize(new Rectangle(xInch, yInch));
		this.setPdfName(pdfName);
	}

	/**
	 * Constructor for a {@link GeospatialPDF} using two {@link Integer} values
	 * for the page length of the PDF document in millimeters.
	 * 
	 * @param xInMm
	 *            the X directed page length in millimeters.
	 * @param yInMm
	 *            the Y directed page length in millimeters.
	 */
	public GeospatialPDF(int xInMm, int yInMm) {
		// CONVERT THE PIXEL AT 72 PDI INTO AN INCH VALUE AND SAVE IT INTO THE
		// RECTANGLE ATTRIBUTE
		int xInch = (int) (xInMm / 25.4);
		int yInch = (int) (yInMm / 25.4);
		this.setPageSize(new Rectangle(xInch, yInch));
	}

	// METHODS

	/**
	 * Creates the {@link GeospatialPDF} document in the file system and fills
	 * it with the contained {@link MapLayer}.
	 */
	public abstract void createPDF();

	/**
	 * TODO
	 *
	 * @param pageSize
	 */
	@Deprecated
	public abstract void createPDF(Rectangle pageSize);

	/**
	 * TODO
	 *
	 * @param layers
	 * @param pageSize
	 */
	// TODO : HIER FEHLEN NOCH UEBRGABEPARAMETER - ODER EBEN NICHT, WEIL DIESE
	// SCHON ZUVOR HINZGEFUEGT WURDEN UEBER DIE ENTSPRECHENDEN ADD METHODEN
	@Deprecated
	public abstract void createPDF(ArrayList<MapLayer> layers, Rectangle pageSize);

	/**
	 * Adds a {@link MapLayer} into the internal {@link ArrayList} of
	 * {@link MapLayer}s.
	 *
	 * @param layer
	 *            the {@link MapLayer} to add
	 */
	public void addLayer(MapLayer layer) {
		this.getLayers().add(layer);
	}

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

	/**
	 * Returns the {@link ArrayList} of {@link MapLayer}s.
	 *
	 * @return the layers as {@link ArrayList} of {@link MapLayer}s
	 */
	public ArrayList<MapLayer> getLayers() {
		return layers;
	}

	/**
	 * Sets the {@link ArrayList} of {@link MapLayer}s.
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
	 * @return the pdfName
	 */
	public String getPdfName() {
		return pdfName;
	}

	/**
	 * Sets the TODO
	 *
	 * @param pdfName
	 *            the pdfName to set
	 */
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	// OTHERS
}

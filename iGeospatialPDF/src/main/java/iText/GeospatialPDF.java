package iText;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

import draw.DrawElement;
import draw.drawer.Drawer;
import geo.BoundingBox;
import mapContent.MapLayer;
import resources.PdfPageSize;

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
	 * The page size of the PDF-page to create as {@link PdfPageSize}.
	 */
	private PdfPageSize pageSize;

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
	 * The "over all" {@link BoundingBox} of the PDF-document.
	 */
	private BoundingBox bbox;

	/**
	 * The width of the page to crate in inch as {@link Float}.
	 */
	private float pageWidth = 0;

	/**
	 * The height of the page to create in inch ad {@link Float}.
	 */
	private float pageHeight = 0;

	/**
	 * The {@link Document} of this {@link GeospatialPDF} to be filled with
	 * content.
	 */
	private Document doc;

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
	public GeospatialPDF(PdfPageSize pageSize) {
		this.setPageSize(pageSize);
		this.calcDocumentSize(pageSize);
	}

	// METHODS

	/**
	 * Creates the {@link GeospatialPDF} document in the file system and fills
	 * it with the contained {@link MapLayer}.
	 */
	public abstract void createPDF();

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

	/**
	 * Calculates the width and height of the page in inches depending on the
	 * given {@link PdfPageSize}.
	 *
	 * @param pageSize
	 *            the {@link PdfPageSize}
	 */
	private void calcDocumentSize(PdfPageSize pageSize) {
		// SAVE THE PIXEL VALUES (AT 72 DPI) IN THE VARIABLES
		if (pageSize == PdfPageSize.DinA0) {
			this.setPageWidth(33.1102f);
			this.setPageHeight(46.81102f);
			this.setDoc(new Document(PageSize.A0));
		} else if (pageSize == PdfPageSize.DinA0r) {
			this.setPageWidth(46.81102f);
			this.setPageHeight(33.1102f);
			this.setDoc(new Document(PageSize.A0.rotate()));
		} else if (pageSize == PdfPageSize.DinA1) {
			this.setPageWidth(23.3858f);
			this.setPageHeight(33.1102f);
			this.setDoc(new Document(PageSize.A1));
		} else if (pageSize == PdfPageSize.DinA1r) {
			this.setPageWidth(33.1102f);
			this.setPageHeight(23.3858f);
			this.setDoc(new Document(PageSize.A1.rotate()));
		} else if (pageSize == PdfPageSize.DinA2) {
			this.setPageWidth(16.5354f);
			this.setPageHeight(23.3858f);
			this.setDoc(new Document(PageSize.A2));
		} else if (pageSize == PdfPageSize.DinA2r) {
			this.setPageWidth(23.3858f);
			this.setPageHeight(16.5354f);
			this.setDoc(new Document(PageSize.A2.rotate()));
		} else if (pageSize == PdfPageSize.DinA3) {
			this.setPageWidth(11.6929f);
			this.setPageHeight(16.5354f);
			this.setDoc(new Document(PageSize.A3));
		} else if (pageSize == PdfPageSize.DinA3r) {
			this.setPageWidth(16.5354f);
			this.setPageHeight(11.6929f);
			this.setDoc(new Document(PageSize.A3.rotate()));
		} else if (pageSize == PdfPageSize.DinA4) {
			this.setPageWidth(8.26772f);
			this.setPageHeight(11.6929f);
			this.setDoc(new Document(PageSize.A4));
		} else if (pageSize == PdfPageSize.DinA4r) {
			this.setPageWidth(11.6929f);
			this.setPageHeight(8.26772f);
			this.setDoc(new Document(PageSize.A4.rotate()));
		} else {
			LOG.severe("PAGE SIZE NOT SUPPORTED!");
			this.setDoc(new Document(PageSize.A0));
		}
	}

	// GETTERS AND SETTERS

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
	 * Returns the name of this {@link GeospatialPDF} as {@link String}.
	 *
	 * @return the pdfName as {@link String}
	 */
	public String getPdfName() {
		return pdfName;
	}

	/**
	 * Sets the name of this {@link GeospatialPDF}.
	 *
	 * @param pdfName
	 *            the pdfName to set
	 */
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	/**
	 * Returns the {@link BoundingBox} of this {@link GeospatialPDF} as
	 * {@link BoundingBox}.
	 *
	 * @return the bbox as {@link BoundingBox}
	 */
	public BoundingBox getBbox() {
		return bbox;
	}

	/**
	 * Sets the {@link BoundingBox} of this {@link GeospatialPDF}.
	 *
	 * @param bbox
	 *            the bbox to set
	 */
	public void setBbox(BoundingBox bbox) {
		this.bbox = bbox;
	}

	/**
	 * Returns the page width of this {@link GeospatialPDF} in inches as
	 * {@link Float}.
	 *
	 * @return the pageWidth as {@link Float}
	 */
	public float getPageWidth() {
		return pageWidth;
	}

	/**
	 * Sets the page width of this {@link GeospatialPDF} as {@link Float}.
	 *
	 * @param pageWidth
	 *            the pageWidth to set
	 */
	public void setPageWidth(float pageWidth) {
		this.pageWidth = pageWidth;
	}

	/**
	 * Returns the page height of this {@link GeospatialPDF} in inches as
	 * {@link Float}.
	 *
	 * @return the pageHeight
	 */
	public float getPageHeight() {
		return pageHeight;
	}

	/**
	 * Sets the page height of this PDF as {@link Float}.
	 *
	 * @param pageHeight
	 *            the pageHeight to set
	 */
	public void setPageHeight(float pageHeight) {
		this.pageHeight = pageHeight;
	}

	/**
	 * Returns the {@link PdfPageSize} of this PDF as {@link PdfPageSize}.
	 *
	 * @return the pageSize as {@link PdfPageSize}
	 */
	public PdfPageSize getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the {@link PdfPageSize} of this {@link GeospatialPDF}.
	 *
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(PdfPageSize pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Returns the {@link Document} of this {@link GeospatialPDF} as
	 * {@link Document}.
	 *
	 * @return the doc as {@link Document}
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * Sets the {@link Document} of this {@link GeospatialPDF}.
	 *
	 * @param doc
	 *            the doc to set
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}

	// OTHERS
}

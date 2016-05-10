package draw.drawer;

import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfStructureElement;

import draw.DrawCollection;
import draw.DrawElement;
import iText.GeospatialPDF;

/**
 * Abstract class that shall be used as parental class for all kinds of PDFs.
 * This class dictates methods that must be implemented in all child classes.
 * 
 * @author DaGri
 * @since 09.05.2016
 */
public abstract class Drawer {

	// ATTRIBUTES

	/**
	 * The {@link PdfContentByte} used to draw to the {@link DrawElement}s.
	 */
	private PdfContentByte contByte;

	/**
	 * The {@link PdfStructureElement} used to add the tagging information
	 * below.
	 */
	private PdfStructureElement top;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link Drawer} that is using a {@link PdfContentByte}
	 * and a {@link PdfStructureElement} to draw tagged content into a
	 * {@link GeospatialPDF}.
	 * 
	 * @param contByte
	 *            the {@link PdfContentByte} to use
	 * @param top
	 *            the {@link PdfStructureElement}
	 */
	public Drawer(PdfContentByte contByte, PdfStructureElement top) {
		this.setContByte(contByte);
		this.setTop(top);
	}

	// METHODS

	public abstract void drawAll(DrawCollection dc);

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link PdfContentByte} of this {@link Drawer}.
	 *
	 * @return the contByte
	 */
	public PdfContentByte getContByte() {
		return contByte;
	}

	/**
	 * Sets the {@link PdfContentByte} of this {@link Drawer}.
	 *
	 * @param contByte
	 *            the contByte to set
	 */
	public void setContByte(PdfContentByte contByte) {
		this.contByte = contByte;
	}

	/**
	 * Returns the top {@link PdfStructureElement} of this {@link Drawer}.
	 *
	 * @return the top
	 */
	public PdfStructureElement getTop() {
		return top;
	}

	/**
	 * Sets the top {@link PdfStructureElement} of this {@link Drawer}.
	 *
	 * @param top
	 *            the top to set
	 */
	public void setTop(PdfStructureElement top) {
		this.top = top;
	}

	// OTHERS
}

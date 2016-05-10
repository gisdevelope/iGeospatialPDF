package draw.drawer;

import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfStructureElement;

import draw.DrawCollection;
import draw.DrawElement;
import iText.NetworkPDF;

/**
 * Class to draw the {@link DrawElement}s that are used in an {@link NetworkPDF}
 * .
 * 
 * @author DaGri
 * @since 09.05.2016
 *
 */
public class NetworkDrawer extends Drawer {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link NetworkDrawer} using a {@link PdfContentByte}
	 * and a {@link PdfStructureElement} to draw tagged {@link DrawElement}s
	 * directly into the structure of a PDF file.
	 * 
	 * @param contByte
	 *            the {@link PdfContentByte} to use
	 * @param top
	 *            the {@link PdfStructureElement} to use
	 */
	public NetworkDrawer(PdfContentByte contByte, PdfStructureElement top) {
		super(contByte, top);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.drawer.Drawer#drawAll(draw.DrawCollection)
	 */
	@Override
	public void drawAll(DrawCollection dc) {
		// TODO Auto-generated method stub
	}

	// METHODS

	// GETTERS AND SETTERS

	// OTHERS
}

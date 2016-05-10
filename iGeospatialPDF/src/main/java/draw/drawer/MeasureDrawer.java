package draw.drawer;

import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfStructureElement;

import draw.DrawCollection;
import draw.DrawElement;

/**
 * Children class that extends the {@link Drawer} - class to allow it to draw
 * tagged measure - {@link DrawElement}s directly into a PDF file.
 * 
 * @author DaGri
 * @since 09.05.2016
 *
 */
public class MeasureDrawer extends Drawer {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link MeasureDrawer} using a {@link PdfContentByte}
	 * and a {@link PdfStructureElement} to draw directly into a PDF file.
	 * 
	 * @param contByte
	 *            the {@link PdfContentByte} to use
	 * @param top
	 *            the {@link PdfStructureElement} to use
	 */
	public MeasureDrawer(PdfContentByte contByte, PdfStructureElement top) {
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

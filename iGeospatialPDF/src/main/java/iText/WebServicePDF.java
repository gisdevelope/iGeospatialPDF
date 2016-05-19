package iText;

import java.util.ArrayList;

import com.lowagie.text.Rectangle;

import mapContent.MapLayer;

/**
 * Class to create a PDF document that is filled by Web Map Services and Web
 * Feature Services.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class WebServicePDF extends GeospatialPDF {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link WebServicePDF} using a {@link Rectangle} to
	 * define the size of the page.
	 * 
	 * @param pageSize
	 *            the size of the page as {@link Rectangle}
	 */
	public WebServicePDF(Rectangle pageSize) {
		super(pageSize);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see iText.GeospatialPDF#createPDF(java.util.ArrayList, com.lowagie.text.Rectangle)
	 */
	@Override
	public void createPDF(ArrayList<MapLayer> layers, Rectangle pageSize) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see iText.GeospatialPDF#createPDF(com.lowagie.text.Rectangle)
	 */
	@Override
	public void createPDF(Rectangle pageSize) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see iText.GeospatialPDF#createPDF()
	 */
	@Override
	public void createPDF() {
		for(int a=0;a<this.getLayers().size();a++){
			this.getLayers().get(a).receive();
		}
		
		// PDF DOKUMENT ERSTELLEN
		// VORBEREITEN DES SCHREIBENS IN DAS DOKUMENT
		// DOKUMENT DIREKT MIT GETAGGTEM INHALT ERSTELLEN
	}

	// METHODS

	// GETTERS AND SETTERS

	// OTHERS
}

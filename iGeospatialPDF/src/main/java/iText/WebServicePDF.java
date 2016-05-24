package iText;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfLayer;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfStructureElement;
import com.lowagie.text.pdf.PdfStructureTreeRoot;
import com.lowagie.text.pdf.PdfWriter;

import mapContent.DataInputLayer;
import mapContent.WfsLayer;
import mapContent.WmsLayer;
import resources.PdfPageSize;

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
	public WebServicePDF(PdfPageSize pageSize) {
		super(pageSize);
		// INSTANCITAE LOGGER
		this.LOG = Logger.getLogger(this.getClass().getCanonicalName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iText.GeospatialPDF#createPDF()
	 */
	@Override
	public void createPDF() {
		try {
			// PREPARE TO WRITE IN THE PDF
			PdfWriter writer = PdfWriter.getInstance(this.getDoc(),
					new FileOutputStream("output/" + System.currentTimeMillis() + ".pdf"));
			writer.setTagged();

			// OPEN THE DOCUMENT
			this.getDoc().open();

			// CREATE THE STRUCTURE TREE ROOT
			PdfStructureTreeRoot tree = writer.getStructureTreeRoot();
			// CREATE THE TOP ELEMENT OF THE TREE
			PdfStructureElement top = new PdfStructureElement(tree, new PdfName("Data"));

			// CREATE NEW PAGE WITH A SIZE THAT FITS BEST TO THE MAP LAYOUT
			// doc.newPage();

			// PREPARE TO DRAW DIRECTLY TO THE PDF
			PdfContentByte contByte = writer.getDirectContent();

			for (int a = 0; a < this.getLayers().size(); a++) {

				this.getLayers().get(a).receive();

				if (this.getLayers().get(a) instanceof WmsLayer) {
					contByte.beginLayer(
							new PdfLayer(((WmsLayer) (this.getLayers().get(a))).getLayers().toString() + "", writer));
					BufferedImage buff = ((WmsLayer) (this.getLayers().get(a))).getMapImage();

					Image img = Image.getInstance(buff, null);
					img.setAbsolutePosition(0, 0);

					// BILD SKALIEREN
					this.scaleImage(img);

					contByte.addImage(img);
					contByte.endLayer();
				} else if (this.getLayers().get(a) instanceof WfsLayer) {

				} else if (this.getLayers().get(a) instanceof DataInputLayer) {

				}

				this.getDoc().close();
			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scales the {@link Image} to fit the {@link PageSize} of this
	 * {@link WebServicePDF}.
	 *
	 * @param img
	 */
	private void scaleImage(Image img) {
		// CALCULATE THE PAGE SIZE TO FIT THE IMAGE TO
		float pageDotsWidth = this.getPageWidth() * 72;
		float pageDotsHeight = this.getPageHeight() * 72;

		// CALCULATE THE FACTOR IN X DIRECTION TO FIT THE PAGE SIZE

		if (img.getWidth() > pageDotsWidth) {
			float factor = pageDotsWidth / img.getWidth();

			img.scaleAbsolute(img.getWidth() * factor, img.getHeight() * factor);
		}
		if (img.getHeight() > pageDotsHeight) {
			float factor = pageDotsHeight / img.getHeight();
			img.scaleAbsolute(img.getWidth() * factor, img.getHeight() * factor);
		}

		// img.scaleToFit(this.getPageWidth() * 72, this.getPageHeight() * 72);
		LOG.info("SCALED THE IMAGE OF THE LAYER TO WIDTH = " + img.getWidth() + ", HEIGHT = " + img.getHeight());
	}

	// METHODS

	// GETTERS AND SETTERS

	// OTHERS
}

package iText;

import java.awt.image.BufferedImage;
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

		LOG.info("TRYING TO CREATE PDF...");

		try {
			// SET THE WRITER TAGGED BEFORE OPENING THE DOCUMENT
			this.getWriter().setTagged();

			// OPEN THE DOCUMENT
			LOG.info("OPENING THE DOCUMENT");
			this.getDoc().open();

			// PREPARE TO DRAW DIRECTLY TO THE PDF
			PdfContentByte contByte = getWriter().getDirectContent();

			// THE STRUCTURE TREE ROOT
			PdfStructureTreeRoot tree = this.getWriter().getStructureTreeRoot();

			// EXTRACT THE STRUCTURE ELEMENT TOP
			PdfStructureElement top = new PdfStructureElement(tree, new PdfName("WFS-Data"));

			for (int a = 0; a < this.getLayers().size(); a++) {

				// RECEIVE THE DATA FOR THE LAYER, WHATEVER LAYER IT IS

				LOG.info("RECEIVING DATA FOR THE LAYER " + a);

				this.getLayers().get(a).receive();

				// IF THE LAYER IS A WMS LAYER
				if (this.getLayers().get(a) instanceof WmsLayer) {

					LOG.info("THIS LAYER IS A WMS-LAYER");
					LOG.info("BEGINING THE PDF-LAYER OF THE WMS-LAYER");

					// START THE LAYER WITH THE NAME OF THE MAP LAYERS
					contByte.beginLayer(new PdfLayer(((WmsLayer) (this.getLayers().get(a))).getLayers().toString() + "",
							getWriter()));

					// CREATE BUFFERED IMAGE OF THE MAP IMAGE
					BufferedImage buff = ((WmsLayer) (this.getLayers().get(a))).getMapImage();

					// CREATE ITEXT IMAGE
					Image img = Image.getInstance(buff, null);

					// SET THE ABSOLUTE POSITION OF THE IMAGE (NEEDED)
					img.setAbsolutePosition(0, 0);

					// SCALE THE IMAGE
					this.scaleImage(img);

					// ADD THE GEOREFFERECING INFORMATION TO THE IMAGE
					this.addGeoreferencing(img, ((WmsLayer) (this.getLayers().get(a))).getBbox());

					// ADD THE IMAGE TO THE PAGE
					contByte.addImage(img);

					LOG.info("ENDING THE PDF-LAYER OF THE WMS-LAYER");

					// END THE LAYER
					contByte.endLayer();
				}

				// ELSE IF THE LAYER IS A WFS LAYER
				else if (this.getLayers().get(a) instanceof WfsLayer) {

					LOG.info("THIS LAYER IS A WFS-LAYER");

					// TODO : DAS MUSS SPAETER UEBERGEBEN WERDEN!
					double angle = 0;
					double factor = 0;

					// TODO WO IST DIE STYLE UEBERGABE?
					((WfsLayer) this.getLayers().get(a)).getCollection().getDrawer().setContByte(contByte);
					((WfsLayer) this.getLayers().get(a)).getCollection().getDrawer().setTop(top);
					// ((WebServiceDrawer) ((WfsLayer)
					// this.getLayers().get(a)).getCollection().getDrawer())
					// .setStyle(((WfsLayer)
					// this.getLayers().get(a)).getStyle());
					
					// TODO : PREPARE DATA AUFRUFEN?!

					// ((WfsLayer)
					// this.getLayers().get(a)).prepareData(this.getBbox().getDownLeft().getNorthing(),
					// this.getBbox().getDownLeft().getEasting(), angle,
					// factor);

					contByte.beginLayer(
							new PdfLayer("Polygons: " + ((WfsLayer) this.getLayers().get(a)).getLink(), getWriter()));

					((WfsLayer) this.getLayers().get(a)).drawPolygons(contByte);

					contByte.endLayer();

					contByte.beginLayer(
							new PdfLayer("Polylines " + ((WfsLayer) this.getLayers().get(a)).getLink(), getWriter()));

					((WfsLayer) this.getLayers().get(a)).drawLinestrings(contByte);

					contByte.endLayer();

					contByte.beginLayer(
							new PdfLayer("Point2Ds " + ((WfsLayer) this.getLayers().get(a)).getLink(), getWriter()));

					((WfsLayer) this.getLayers().get(a)).drawPoints(contByte);

					contByte.endLayer();

				}
				// ELSE IF THE LAYER IS A DATAINPUT LAYER
				else if (this.getLayers().get(a) instanceof DataInputLayer) {

					LOG.info("THIS LAYER IS A DATAINPUT-LAYER");
					// TODO : FILL
				}
				// ELSE THE LAYER KIND IS NOT SUPPORTED IN A WEB SERVICE PDF
				else {
					LOG.severe("LAYER NOT SUPPORTED IN A WEBSERVICE-PDF");
				}

				LOG.info("CLOSING THE DOCUMENT");

				// CLOSE THE DOCUMENT TO STOP EDITING AND GIVE IT FREE IN THE
				// FILE SYSTEM
				this.getDoc().close();

			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			LOG.severe("ERROR WHILE CREATING PDF DOCUMENT!");
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

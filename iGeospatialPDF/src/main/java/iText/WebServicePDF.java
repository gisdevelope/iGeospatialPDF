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

import mapContent.layers.ReferencedLayer;
import mapContent.layers.WfsLayer;
import mapContent.layers.WmsLayer;
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

			// TODO : DA DIE DPI ZAHL NICHT DER DES EIGENTLICHEN KARTENBILDES
			// ENTSPRICHT MUSS DIESES KLEINE KARTENBILD MIT EINER ANDEREN
			// METHODE SKALIERT WERDEN, UM GENAU SO GROSS ZU WERDEN WIE DIE
			// SEITE...

			ReferencedLayer refLayer = new ReferencedLayer(this.getMasterBbox(), this.getPageWidth(),
					this.getPageHeight());
			this.getLayers().add(0, refLayer);

			// THE STRUCTURE TREE ROOT
			PdfStructureTreeRoot tree = this.getWriter().getStructureTreeRoot();

			// EXTRACT THE STRUCTURE ELEMENT TOP
			PdfStructureElement top = new PdfStructureElement(tree, new PdfName("WFS-Data"));

			// TODO : WMS LAYER IN DEN HINTERGRUND EINFUEGEN, DER DIE
			// GEOREFFERENZIERUNG ENTHAELT

			for (int a = 0; a < this.getLayers().size(); a++) {

				// RECEIVE THE DATA FOR THE LAYER, WHATEVER LAYER IT IS

				LOG.info("RECEIVING DATA FOR THE LAYER " + a);

				this.getLayers().get(a).receive();

				// IF THE LAYER IS A REFERENCED LAYER (FIRST LAYER WILL ALWAYS
				// BE)
				if (this.getLayers().get(a) instanceof ReferencedLayer) {
					LOG.info("THIS LAYER IS A REFERENCED-LAYER");
					LOG.info("THIS LAYER DOES NOT HAVE A PDF-LAYER");

					// CREATE BUFFERED IMAGE OF THE MAP IMAGE
					BufferedImage buff = ((ReferencedLayer) (this.getLayers().get(a))).getMapImage();

					// CREATE ITEXT IMAGE
					Image img = Image.getInstance(buff, null);

					// SET THE ABSOLUTE POSITION OF THE IMAGE (NEEDED)
					img.setAbsolutePosition(0, 0);

					// SCALE THE IMAGE
					this.scaleReferencedImage(img);

					// ADD THE GEOREFFERECING INFORMATION TO THE IMAGE
					this.addGeoreferencing(img, ((ReferencedLayer) (this.getLayers().get(a))).getBbox());

					// ADD THE IMAGE TO THE PAGE
					contByte.addImage(img);
				}
				// ELSE IF THE LAYER IS A WMS LAYER
				else if (this.getLayers().get(a) instanceof WmsLayer) {

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
					// this.addGeoreferencing(img, ((WmsLayer)
					// (this.getLayers().get(a))).getBbox());

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
					double angle = 0.0;
					double factor = 1.0;

					((WfsLayer) this.getLayers().get(a)).getCollection().getDrawer().setContByte(contByte);
					((WfsLayer) this.getLayers().get(a)).getCollection().getDrawer().setTop(top);
					((WfsLayer) this.getLayers().get(a)).setAngle(angle);
					((WfsLayer) this.getLayers().get(a)).setFactor(factor);

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
				// ELSE THE LAYER KIND IS NOT SUPPORTED IN A WEB SERVICE PDF
				else {
					LOG.severe("LAYER NOT SUPPORTED IN A WEBSERVICE-PDF");
				}

				LOG.info("CLOSING THE DOCUMENT");

			}
			// CLOSE THE DOCUMENT TO STOP EDITING AND GIVE IT FREE IN THE
			// FILE SYSTEM
			this.getDoc().close();
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

	/**
	 * TODO
	 *
	 * @param img
	 */
	private void scaleReferencedImage(Image img) {
		// CALCULATE THE PAGE SIZE TO FIT THE IMAGE TO
		float pageDotsWidth = this.getPageWidth() * 72;
		float pageDotsHeight = this.getPageHeight() * 72;

		// TODO : BILD AN DIE SEITENGROESSE ANPASSEN

		// MASTER BOUNDINGBOX HOCHKANT
		if (this.getMasterBbox().getWidthGeo() < this.getMasterBbox().getHeightGeo()) {
			// SEITE HOCHKANT
			if (this.getPageWidth() < this.getPageHeight()) {
				// CALCUALTE THE FACTOR
				float factor = pageDotsWidth / img.getPlainWidth();
				// SCALE THE IMAGE
				img.scaleAbsolute(img.getPlainWidth() * factor, img.getPlainHeight() * factor);
				// IMAGE STILL NOT HEIGHER AS THE PAGE
				if (img.getPlainHeight() < pageDotsHeight) {
					// OKAY
				}
				// ELSE THE IMAGE IS HIGHER THAN THE PAGE
				else {
					// CALCUALTE THE FACTOR
					factor = pageDotsHeight / img.getPlainHeight();
					// SCALE THE IMAGE
					img.scaleAbsolute(img.getPlainWidth() * factor, img.getPlainHeight() * factor);
				}
			}
			// SEITE QUER
			else if (this.getPageWidth() > this.getPageHeight()) {
				// CALCUALTE THE FACTOR
				float factor = pageDotsHeight / img.getPlainHeight();
				// SCALE THE IMAGE
				img.scaleAbsolute(img.getPlainWidth() * factor, img.getHeight() * factor);
				// IMAGE STILL NOT AS LARGE AS THE PAGE
				if (img.getPlainWidth() < pageDotsWidth) {
					// OKAY
				}
				// ELSE THE IMAGE IS LARGER THANT THE PAGE
				else {
					factor = pageDotsWidth / img.getPlainWidth();
					// SCALE THE IMAGE
					img.scaleAbsolute(img.getPlainWidth() * factor, img.getPlainHeight() * factor);
				}
			}
		}
		// MASTER BOUNDINGBOX QUER
		else if (this.getMasterBbox().getWidthGeo() > this.getMasterBbox().getHeightGeo()) {
			// SEITE HOCHKANT
			if (this.getPageWidth() < this.getPageHeight()) {
				// CALCUALTE THE FACTOR
				float factor = pageDotsWidth / img.getPlainWidth();
				// SCALE THE IMAGE
				img.scaleAbsolute(img.getPlainWidth() * factor, img.getPlainHeight() * factor);
				// IMAGE STILL NOT HEIGHER AS THE PAGE
				if (img.getPlainHeight() < pageDotsHeight) {
					// OKAY
				}
				// ELSE THE IMAGE IS HIGHER THAN THE PAGE
				else {
					// CALCUALTE THE FACTOR
					factor = pageDotsHeight / img.getPlainHeight();
					// SCALE THE IMAGE
					img.scaleAbsolute(img.getPlainWidth() * factor, img.getPlainHeight() * factor);
				}
			}
			// SEITE QUER
			else if (this.getPageWidth() > this.getPageHeight()) {
				// CALCUALTE THE FACTOR
				float factor = pageDotsHeight / img.getPlainHeight();
				// SCALE THE IMAGE
				img.scaleAbsolute(img.getPlainWidth() * factor, img.getPlainHeight() * factor);
				// IMAGE STILL NOT AS LARGE AS THE PAGE
				if (img.getPlainWidth() < pageDotsWidth) {
					// OKAY
				}
				// ELSE THE IMAGE IS LARGER THANT THE PAGE
				else {
					factor = pageDotsWidth / img.getPlainWidth();
					// SCALE THE IMAGE
					img.scaleAbsolute(img.getWidth() * factor, img.getHeight() * factor);
				}
			}
		}
		// MASTER BOUNDINGBOX QUADRATISCH
		else if (this.getMasterBbox().getWidthGeo() == this.getMasterBbox().getHeightGeo()) {
			// SEITE HOCHKANT
			if (this.getPageWidth() < this.getPageHeight()) {

			}
			// SEITE QUER
			else if (this.getPageWidth() > this.getPageHeight()) {

			}
		}
	}

	// METHODS

	// GETTERS AND SETTERS

	// OTHERS
}

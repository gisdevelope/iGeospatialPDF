package mapContent.layers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import geo.BoundingBox;
import iText.GeospatialPDF;
import resources.Tile;
import resources.TileArray;

/**
 * Child class of {@link MapLayer}. This class will contain an
 * {@link BufferedImage}, used as background for each {@link GeospatialPDF},
 * which is geo-referenced.
 * 
 * @author DaGri
 * @since 12.06.2016
 *
 */
public class ReferencedLayer extends MapLayer {

	// ATTRIBUTES

	/**
	 * The {@link BufferedImage} of this {@link WmsLayer}, that stores the map
	 * image to display in the PDF file.
	 */
	private BufferedImage mapImage;

	/**
	 * The dots per inch (DPI) value for the map image of this {@link WmsLayer}.
	 */
	private int dpi = 10;

	/**
	 * The width of the {@link BufferedImage} of this {@link WmsLayer}.
	 */
	private int imageWidth;

	/**
	 * The height of the {@link BufferedImage} of this {@link WmsLayer}.
	 */
	private int imageHeight;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link ReferencedLayer} using a {@link BoundingBox},
	 * the size of the page in height and width as {@link Float}.
	 * 
	 * @param masterBbox
	 *            the master {@link BoundingBox}
	 * @param pageWidth
	 *            the width of the PDF page
	 * @param pageHeight
	 *            the height of the PDF page
	 */
	public ReferencedLayer(BoundingBox masterBbox, float pageWidth, float pageHeight) {
		super(masterBbox);

		// CREATE THE IMAGE WITH A PAGE FITTING WIDTH AND HEIGHT THAT WILL BE
		// REFERENCED IN THE RECEIVE
		this.setImageWidth((int) masterBbox.getWidthGeo());
		this.setImageHeight((int) masterBbox.getHeightGeo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.layers.MapLayer#receive()
	 */
	@Override
	public void receive() {
		// CALCULATE THE COMPLETE IMAGE
		this.calcImage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.layers.MapLayer#prepareData(double, double, double,
	 * double)
	 */
	@Override
	public void prepareData(double northingRed, double eastingRed, double angle, double factor) {
		// TODO Auto-generated method stub
	}

	// METHODS

	/**
	 * Calculates the combined image from the images {@link Tile}s of the
	 * {@link TileArray}.
	 */
	private void calcImage() {
		// NOW THE COMBINED WIDTH AND HEIGHT OF THE IMAGE TO CREATE IS STORED IN
		// THE VARIABLES
		// CREATE IMAGE TO PUT THE TILE IMAGES INSIDE
		BufferedImage ergImg = new BufferedImage(this.getImageWidth(), this.getImageHeight(),
				BufferedImage.TYPE_INT_ARGB);

		// CREATE AN IMAGE IN THE NEEDED SIZE

		// CREATE THE GRAPHICS FOR THIS IMAGE
		Graphics2D graphics = ergImg.createGraphics();

		// SET THE PAINT COLOR
		// graphics.setPaint(new Color(255F / 2, 255F, 185F, 185F));
		// TODO : HINTERHER DIE FARBE ANPASSEN
		graphics.setPaint(Color.PINK);

		// DRAW A RECTANGLE OVER THE FULL IMAGE SIZE
		graphics.fillRect(0, 0, ergImg.getWidth(), ergImg.getHeight());

		// FLUSH DRAWING
		ergImg.flush();

		// TRYING TO SAVE HEAP SPACE
		ergImg.flush();
		ergImg.getGraphics().dispose();

		this.setMapImage(ergImg);
	}

	/**
	 * Saves an image to the file system.
	 * 
	 * @param buff
	 *            the {@link BufferedImage} to save to the file system
	 */
	@SuppressWarnings("unused")
	private void putOutImage(BufferedImage buff) {
		try {
			File outputfile = new File("output/" + System.currentTimeMillis() + ".png");
			ImageIO.write(buff, "png", outputfile);
		} catch (IOException e) {
			LOG.severe("COULD NOT PUT OUT IMAGE TO THE FILE SYSTEM!");
		}

	}

	// GETTERS AND SETTERS

	/**
	 * Returns the map image as {@link BufferedImage}.
	 *
	 * @return the mapImage as {@link BufferedImage}
	 */
	public BufferedImage getMapImage() {
		return mapImage;
	}

	/**
	 * Sets the map image.
	 *
	 * @param mapImage
	 *            the mapImage to set
	 */
	public void setMapImage(BufferedImage mapImage) {
		this.mapImage = mapImage;
	}

	/**
	 * Returns the DPI value as {@link Integer}
	 *
	 * @return the dpi as {@link Integer}
	 */
	public int getDpi() {
		return dpi;
	}

	/**
	 * Sets the DPI value.
	 *
	 * @param dpi
	 *            the dpi to set
	 */
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

	/**
	 * Returns the image width as {@link Integer}.
	 *
	 * @return the imageWidth as {@link Integer}
	 */
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * Sets the image width.
	 *
	 * @param imageWidth
	 *            the imageWidth to set
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * Returns the image height as {@link Integer}.
	 *
	 * @return the imageHeight as {@link Integer}
	 */
	public int getImageHeight() {
		return imageHeight;
	}

	/**
	 * Sets the image height.
	 *
	 * @param imageHeight
	 *            the imageHeight to set
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	// OTHERS
}

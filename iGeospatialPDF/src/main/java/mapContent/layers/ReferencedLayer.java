package mapContent.layers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import geo.BoundingBox;
import resources.Tile;
import resources.TileArray;

/**
 * Class to TODO
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
	 * Constructor for TODO
	 * 
	 * @param masterBbox
	 * @param pageWidth
	 * @param pageHeight
	 */
	public ReferencedLayer(BoundingBox masterBbox, float pageWidth, float pageHeight) {
		super(masterBbox);
		// TODO Auto-generated constructor stub
		
		// BILD ERSTELLEN, DAS DEM VERHAELTNIS DER BOUNDINGBOX ENTSPRICHT
		this.setImageWidth((int)masterBbox.getWidthGeo());
		this.setImageHeight((int)masterBbox.getHeightGeo());

		// if (this.getBbox().getWidthGeo() >= this.getBbox().getHeightGeo()) {
		// // SET THE DESIRED WIDTH OF THE IMAGE
		// this.setImageWidth((int) (pageWidth * dpi));
		// // SET THE DESIRED HEIGHT BY THE RELATION OF THE BOUNDGINGBOX WIDTH
		// // AND HEIGHT
		// double relation = this.getBbox().getHeightGeo() /
		// this.getBbox().getWidthGeo();
		// this.setImageHeight((int) (this.getImageWidth() * relation));
		// } else
		// // ELSE THE BOUNDINGBOX HEIGHT IS LARGER THAN THE WIDTH
		// {
		// // SET THE DESIRED HEIGHT OF THE IMAGE
		// this.setImageHeight((int) (pageHeight * dpi));
		// // SET THE DESIRED HEIGHT BY THE RELATION OF THE BOUNDGINGBOX WIDTH
		// // AND HEIGHT
		// double relation = this.getBbox().getWidthGeo() /
		// this.getBbox().getHeightGeo();
		// this.setImageWidth((int) (this.getImageHeight() * relation));
		// }
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
	 * Returns the TODO
	 *
	 * @return the mapImage
	 */
	public BufferedImage getMapImage() {
		return mapImage;
	}

	/**
	 * Sets the TODO
	 *
	 * @param mapImage
	 *            the mapImage to set
	 */
	public void setMapImage(BufferedImage mapImage) {
		this.mapImage = mapImage;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the dpi
	 */
	public int getDpi() {
		return dpi;
	}

	/**
	 * Sets the TODO
	 *
	 * @param dpi
	 *            the dpi to set
	 */
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the imageWidth
	 */
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * Sets the TODO
	 *
	 * @param imageWidth
	 *            the imageWidth to set
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the imageHeight
	 */
	public int getImageHeight() {
		return imageHeight;
	}

	/**
	 * Sets the TODO
	 *
	 * @param imageHeight
	 *            the imageHeight to set
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	// OTHERS
}
